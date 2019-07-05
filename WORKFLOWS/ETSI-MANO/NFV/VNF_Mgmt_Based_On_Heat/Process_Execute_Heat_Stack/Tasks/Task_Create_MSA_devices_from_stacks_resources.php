<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';


function list_args() {
  create_var_def('device_login', 'String');
  create_var_def('device_password', 'String');
}

// MSA device creation parameters
$customer_id = substr($context['UBIQUBEID'], 4);
$resources = $context['resources'];
$server_serialized = "";
$servers_array = array();


foreach ($resources as &$resource) {
	// WARMING: all device is created as LINUX man and GENERIC model.
	logToFile("Resource ID: " . $resource['resource_type'] . " ========= \n");

	if ($resource['resource_type'] == "OS::Nova::Server") {
		
		//$instance_name = $resource['resource_name'];
		$instance_id = $resource['physical_resource_id'];
		$auth_token = $context['token_id'];
		$endpoints = $context['endpoints'];

		foreach ($endpoints as &$endpoint) {
			if ($endpoint["type"] == "compute") {
				$nova_endpoint = $endpoint["publicURL"];
			}
		}
				
		// Get server details from VIM
		$response = _nova_get_server_details ($nova_endpoint, $auth_token, $instance_id);
		$response = json_decode($response, true);
		if ($response['wo_status'] !== ENDED) {
			$response = json_encode($response);
			echo $response;
			exit;
		}
		//logToFile(debug_dump($response, "RESPONSE ============>:\n"));
		
		// Create MSA devices
		$response_body = json_decode($response['wo_newparams']['response_body'], true);
		logToFile(debug_dump($response_body, "RESPONSE ============>:\n"));

		$managed_device_name = $response_body['server']['name'];
		$manufacturer_id = 14020601;
		$model_id = 14020601;
		if (!isset($context['device_login']) || empty($context['device_login'])) {
			$login =  "admin";
		} else {
			$login =  $context['device_login'];
		}
		if (!isset($context['device_password']) || empty($context['device_password'])) {
			$password =  "ubiqube";
		} else {
			$password =  $context['device_password'];
		}
		// Retrieve floating from server details
		$isFloatingIpFind = false;
		foreach ($response_body['server']['addresses'] as &$network_addresses) {
			
			if ($isFloatingIpFind !== true) { 
				foreach ($network_addresses as &$address) {
					if ($address['OS-EXT-IPS:type'] == "floating") {
						$device_ip_address = $address['addr'];
						$isFloatingIpFind = true;
						break;
					}
				}
			} else {
				break;
			}
		}
		//logToFile("Floating IP is ::::::::: ----------------------> : " . $device_ip_address . "\n");		
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
		
		$servers_array[] = array('device_id' => $device_id, 'instance_id' => $instance_id, 'floating_ip_address' => $device_ip_address);
	}

}

//$servers_json = json_encode($servers_array);
//$server_serialized = serialize($servers_json);
//$context['servers'] = $server_serialized;
$context['servers'] = $servers_array;

$response = prepare_json_response(ENDED, "MSA Devices created successfully.", $context, true);
echo $response;

?>
