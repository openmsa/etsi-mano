<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

function list_args()
{
  //create_var_def('key_name', 'String');
  //create_var_def('flavor_scale_out', 'OBMFRef');
  //create_var_def('image_scale_out', 'OBMFRef');
  //create_var_def('private_network_id', 'OBMFRef');
}
logToFile(debug_dump($context, "CONTEXT ============>:\n"));

$device_id = substr($context['deviceid'], 3);
$server_name = "VNF_scale-out";
$networks = $context['networks_id'];
$availability_zone = "nova";
$flavor_id = $context['flavor_id'];
$image = $context['simulator_image_id'];
//$key = $context['key_name'];
$key = '';

// Create server
$response = _nova_server_create($device_id, $server_name, $networks, $availability_zone, $flavor_id, $image, array(), $key);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
    task_exit(FAILED, "Server scale out failed.");
}
$wo_comment = $response['wo_comment'];
$wo_comment = json_decode(str_replace("\\\"", "\"", $wo_comment), true);
$server_id = $wo_comment['server']['id'];

$response = wait_for_server_status($device_id, $server_id, ACTIVE, $context);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

$context['server_id'] = $server_id;

$value = array('server_id' => $server_id, 'name' => $server_name, 'is_msa_device' => false);
$context['servers_scaled'] = array($server_id => $value);

task_exit(ENDED, "Server scaled out successfully with server ID: " . $server_id . "\n");

?>
