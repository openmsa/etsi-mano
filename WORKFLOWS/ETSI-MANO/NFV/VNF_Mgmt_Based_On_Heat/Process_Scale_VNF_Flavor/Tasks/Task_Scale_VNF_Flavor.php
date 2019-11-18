<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';


function list_args()
{
}

$device_id = substr($context['deviceid'], 3);

if (empty($context['flavor_scale'])) {
	$ret = prepare_json_response(FAILED, "Scale Flavor variable is empty.", $context, true);
	echo $ret;
}

$index = 0;
foreach ($context['servers'] as $server) {

	$server_id = $server['instance_id'];

	$response = _nova_server_action ($device_id, $server_id, "Resize", $context['flavor_scale']);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

	$response = wait_for_server_status($device_id, $server_id, VERIFY_RESIZE, $context);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

	$response = _nova_server_action ($device_id, $server_id, "Confirm Resize");
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

	$response = wait_for_server_status($device_id, $server_id, ACTIVE, $context);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

}
if (!empty($context['flavor_scale_name'])) {
	$context['flavor'] = $context['flavor_scale_name'];
}

$ret = prepare_json_response(ENDED, "Servers flavor scaled Successfully", $context, true);
echo $ret;

?>
