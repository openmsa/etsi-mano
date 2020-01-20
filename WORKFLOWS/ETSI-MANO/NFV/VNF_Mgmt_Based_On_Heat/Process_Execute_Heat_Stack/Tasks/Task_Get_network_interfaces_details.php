<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args() {
}

foreach ($context['servers'] as $server) {
	$response = _nova_get_server_details ($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server['instance_id']);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}
	$server_details = json_decode($response['wo_newparams']['response_body'], true);
	$server_addresses_details = json_encode($server_details['server']['addresses']);
	$context['server_addresses_details'] = base64_encode($server_addresses_details) ;
}

$response = prepare_json_response(ENDED, "Network interfaces details retrieve from.", $context, true);
echo $response;

?>
