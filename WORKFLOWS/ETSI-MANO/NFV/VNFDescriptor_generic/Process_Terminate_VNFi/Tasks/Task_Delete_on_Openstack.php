<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';


function list_args()
{
}

$device_id = substr($context['deviceid'], 3);

$index = 0;
foreach ($context['servers'] as $server) {

$server_id = $server['server_id'];
$servers = array('servers' => $server_id);

$response = execute_command_and_verify_response($device_id, CMD_DELETE, $servers, "DELETE servers");
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

}

$ret = prepare_json_response(ENDED, "Servers Deleted Successfully.", $context, true);
echo $ret;

?>