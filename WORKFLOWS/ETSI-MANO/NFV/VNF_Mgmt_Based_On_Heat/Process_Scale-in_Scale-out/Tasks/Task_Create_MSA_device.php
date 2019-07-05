<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';


function list_args() {
}

// MSA device creation parameters
$customer_id = substr($context['UBIQUBEID'], 4);
$servers_array = array();

foreach ($context['servers_scaled'] as &$server) {
	// WARMING: all device is created as LINUX man and GENERIC model.			
	//logToFile(debug_dump($response, "RESPONSE ============>:\n"));
	
	if ($server['is_msa_device'] == "false") {
		// Create MSA devices

		$managed_device_name = $server['name'];
		$manufacturer_id = 14020601;
		$model_id = 14020601;
		$login =  $context['device_login'];
		$password =  $context['device_password'];
		$device_ip_address = $server['floating_ip_address'];
		if (empty($device_ip_address)) {
			echo "Missing device management IP address (Floating IP): MSA device creation FAILED.";
			exit;
		}	
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
		
		// Switch this device as MSA device
		$server['device_id'] = $device_id;
	}
}

$response = prepare_json_response(ENDED, "MSA Device created successfully.", $context, true);
echo $response;

?>
