<?php

/**
 * This file is necessary to include to use all the in-built libraries of /opt/fmc_repository/Reference/Common
 */
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';

/**
 * List all the parameters required by the task
 */
function list_args()
{
  create_var_def('deviceid', 'Device');
}

check_mandatory_param("deviceid");

logToFile(debug_dump($context, "MSA CONTEXT:\n"));

$device_id = substr($context['deviceid'], 3);

// Read device to get its login/password
$response = _device_read_by_id($device_id);

$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

$tenant_login = $response['wo_newparams']['login'];
$tenant_password = $response['wo_newparams']['password'];
$tenant_ip_address= $response['wo_newparams']['managementAddress'];
$context['tenant_login'] = $tenant_login;
$context['tenant_password'] = $tenant_password;
$context['tenant_ip_address'] = $tenant_ip_address;

// Retrieve device configuration variables
$response = _configuration_variable_list ($device_id);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

if (isset($response['wo_newparams']) && !empty($response['wo_newparams'])) {
	foreach ($response['wo_newparams'] as &$conf_variable) {
		if ($conf_variable['name'] == "TENANT_ID") {
			$tenant_id =  $conf_variable['value'];
			$context['tenant_id'] = $tenant_id;
		} 
		if ($conf_variable['name'] == "PROJECT_DOMAIN_ID") {
			$project_domain_id = $conf_variable['value'];
			$context['project_domain_id'] = $project_domain_id ;
		}
		if ($conf_variable['name'] == "USER_DOMAIN_ID") {
			$user_domain_id = $conf_variable['value'];
			$context['user_domain_id'] = $user_domain_id ;
		} 
	}
}
//Create keystone_public_endpoint (this is only for keystone v3)
$context['keystone_public_endpoint'] = "http://".$tenant_ip_address.":5000/v3/";

// TODO - Call method to synchronize device (Micro Service)

//$response = synchronize_objects_and_verify_response($device_id);

//logToFile($response);

task_exit(ENDED, "Synchronisation to VIM tenant is successfull.");

?>
