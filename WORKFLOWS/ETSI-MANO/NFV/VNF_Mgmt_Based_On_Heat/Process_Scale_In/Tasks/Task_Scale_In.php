<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

function list_args()
{
  create_var_def('server_id', 'OBMFRef');
}

$device_id = substr($context['deviceid'], 3);
$server_id = $context['server_id'];

$response = _nova_server_delete($device_id, $server_id);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
    task_exit(FAILED, "Server scale in (delete) failed.");
}

task_exit(ENDED, "Openstack Server $server_id Deleted successfully.");

?>