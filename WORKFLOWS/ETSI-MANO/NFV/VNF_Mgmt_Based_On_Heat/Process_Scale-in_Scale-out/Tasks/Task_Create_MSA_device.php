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
	//logToFile(debug_dump($server, "RESPONSE ============>:\n"));
	if(!isset($server['is_msa_device'])) {
		continue;
	}
	if ($server['is_msa_device'] == "false") {
		// Create MSA devices

		$managed_device_name = $server['name'];
		$manufacturer_id = $context['manufacturerId'];
		$model_id = $context['modelId'];
		$server_id = $context['server_id'];
		
		if (!isset($context['device_login']) || empty($context['device_login'])) {
                        $login =  "admin";
                } else {
                        $login =  $context['device_login'];
                }
                if (!isset($context['device_password']) || empty($context['device_password'])) {
                        $password =  "public";
                } else {
                        $password =  $context['device_password'];
                }

		$device_ip_address = $context['servers_scaled'][$server_id]['floating_ip_address'];
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
