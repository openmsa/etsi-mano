<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';


function list_args()
{
}

$device_id = substr($context['deviceid'], 3);

$index = 0;
foreach ($context['servers'] as $server) {

	$server_id = $server['instance_id'];

	$response = _nova_server_action ($device_id, $server_id, "Stop");
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

	$response = wait_for_server_status($device_id, $server_id, SHUTOFF, $context);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

}

$ret = prepare_json_response(ENDED, "Servers Stopped Successfully.", $context, true);
echo $ret;

?>
