<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';


function list_args()
{
}

$device_id = substr($context['deviceid'], 3);

//if (isset($context['user_data']) && !empty($context['user_data'])) {
//	$user_data = base64_encode($context['user_data']);
//}

foreach ($context['servers'] as $key => $server) {

	$server_id = $server['instance_id'];
	$server_status = $context['instance_status_pre_rebuild'];

	$response = _nova_server_action ($device_id, $server_id, "Rebuild", $context['simulator_image_id']);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

	$response = wait_for_server_status($device_id, $server_id, $server_status, $context);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

}

$ret = prepare_json_response(ENDED, "VNF instance was rebuilt Successfully.", $context, true);
echo $ret;

?>
