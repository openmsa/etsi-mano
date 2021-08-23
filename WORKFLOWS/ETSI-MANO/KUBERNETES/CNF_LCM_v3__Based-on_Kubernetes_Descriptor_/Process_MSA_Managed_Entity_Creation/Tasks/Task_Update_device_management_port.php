<?php

/**
 * This file is necessary to include to use all the in-built libraries of /opt/fmc_repository/Reference/Common
 */
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

/**
 * List all the parameters required by the task
 */
function list_args()
{
  /**
   * You can use var_name convention for your variables
   * They will display automaticaly as "Var Name"
   * The allowed types are:
   *    'String', 'Boolean', 'Integer', 'Password', 'IpAddress',
   *    'IpMask', 'Ipv6Address', 'Composite', 'OBMFRef', 'Device'
   *
   * Add as many variables as needed
   */
  
}

function _device_update_port_management ($device_id,$port) {

	$msa_rest_api = "device/v1/updateDeviceManagementPort?deviceId={$device_id}&managementPort={$port}";
	$curl_cmd = create_msa_operation_request(OP_PUT, $msa_rest_api);
	$response = perform_curl_operation($curl_cmd, "DEVICE PORT MANAGEMENT UPDATE");
	return $response;
}

$device_id=$device_id = substr($context['device_id'], 3);
$port=$context['ssh_remote_access_port'];
$response=_device_update_port_management($device_id,$port);
task_exit(ENDED, $response);
?>