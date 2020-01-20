<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{

}

if (isset($context['servers_scaled']) && !empty($context['servers_scaled'])) {
foreach ($context['servers_scaled'] as $server) {
	$server_id = $server['server_id'];
	$context['scale_in_server_id'] = $server_id;
	$device_id = $server['device_id'];
	$device_id = substr($device_id, 3);

	$response = _nova_server_delete($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server_id);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		task_exit(FAILED, "Server scale in (delete) failed.");
	}

	break;
}
} else {
	task_exit(ENDED, "No VNF instance to scale in.");
}

task_exit(ENDED, "Openstack Server deleted successfully.");

?>