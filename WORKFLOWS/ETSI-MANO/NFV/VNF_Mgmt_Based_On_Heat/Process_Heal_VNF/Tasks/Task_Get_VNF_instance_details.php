<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{
}

$device_id = substr($context['deviceid'], 3);

foreach ($context['servers'] as $key => $server) {

	$server_id = $server['instance_id'];
	$nova_endpoint = $context['endpoints'][NOVA][PUBLIC_URL];

	$response = _nova_get_server_details ($nova_endpoint, $context['token_id'], $server_id);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}
	$response_body = json_decode($response['wo_newparams']['response_body'], true);
	logToFile(debug_dump($response_body, "STATUS_SERVER_ ============>:\n"));
	$context['instance_status_pre_rebuild'] = $response_body['server']['status'];
	logToFile(debug_dump($context, "CONTEXT ============>:\n"));
}

$ret = prepare_json_response(ENDED, "VNF instance details was got Successfully.", $context, true);
echo $ret;

?>
