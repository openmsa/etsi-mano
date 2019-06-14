<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args() {
  create_var_def('servers.0.device_man_model', 'String');
  create_var_def('servers.0.device_login', 'String');
  create_var_def('servers.0.device_password', 'String');
}

// MSA device creation parameters
$customer_id = substr($context['UBIQUBEID'], 4);

$index = 0;
foreach ($context['servers'] as $server) {

if (empty($server['floating_ip_address'])) {
	continue;
}

$managed_device_name = $server['name'];

if ($server['device_man_model'] == "CISCO-ISR-CSR") {
	$manufacturer_id = 1;
	$model_id = 113;
} else if ($server['device_man_model'] == "LINUX-GENERIC") {
	$manufacturer_id = 14020601;
	$model_id = 14020601;
} else if ($server['device_man_model'] == "PALOALTO") {
	$manufacturer_id = 28;
	$model_id = 134;
} else if ($server['device_man_model'] == "FORTINET") {
	$manufacturer_id = 17;
	$model_id = 81;
}

$login = $server['device_login'];
$password = $server['device_password'];
$device_ip_address = $server['floating_ip_address'];
$device_external_reference = "";
if (array_key_exists('device_external_reference', $context)) {
	$device_external_reference = $context['device_external_reference'];
}

$response = _device_create($customer_id, $managed_device_name, $manufacturer_id,
							$model_id, $login, $password, "", $device_ip_address, $device_external_reference);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}
$device_id = $response['wo_newparams']['entity']['externalReference'];
$wo_comment = "Device External Reference : $device_id";
logToFile($wo_comment);
	
$context['servers'][$index++]['device_id'] = $device_id;
}

$response = prepare_json_response(ENDED, "MSA Devices created successfully.", $context, true);
echo $response;

?>
