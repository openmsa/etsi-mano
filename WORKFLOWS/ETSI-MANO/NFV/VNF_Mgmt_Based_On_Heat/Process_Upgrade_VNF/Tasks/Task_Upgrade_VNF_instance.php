<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

function list_args()
{
	Create_var_def('current_image', 'String');
	Create_var_def('upgrade_image_id', 'OBMFRef');
}

if (empty($context['upgrade_image_id'])) {
	$ret = prepare_json_response(FAILED, "Upgrade image is mandatory.", $context, true);
	echo $ret;
}

$device_id = substr($context['deviceid'], 3);

$context['current_image'] = $context['simulator_image_id'];

foreach ($context['servers'] as $key => $server) {
	$server_id = $server['instance_id'];
	$server_status = $context['instance_status_pre_rebuild'];

	$response = _nova_server_action($device_id, $server_id, "Rebuild", $context['upgrade_image_id']);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit();
	}

	$response = wait_for_server_status($device_id, $server_id, $server_status, $context);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit();
	}
}

$ret = prepare_json_response(ENDED, "VNF instance was rebuilt Successfully.", $context, true);
echo $ret;

