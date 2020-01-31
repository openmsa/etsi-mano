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
	if ($resource['resource_type'] == "OS::Neutron::Port") {
		$port_id = $resource['physical_resource_id'];
		$response = _neutron_get_port_info($context['endpoints'][NEUTRON][PUBLIC_URL], $context['token_id'], $port_id);
		
		logToFile(debug_dump($response, "RESPONSE ============++++++++++++>:\n"));
		$res = json_decode($response, 1);
		if (isset($res['wo_newparams']['port']['network_id'])) {
			$nId = $res['wo_newparams']['port']['network_id'];
			//$context['networks_id'][0]['network'] = $nId;
			$context['networks_id'][]['network'] = $nId;
		}
	}	
	if ($resource['resource_type'] == "OS::Nova::Server") {
		
		//$instance_name = $resource['resource_name'];
		$instance_id = $resource['physical_resource_id'];
		$context['vnf_instance_id'] = $instance_id;
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
		$context['simulator_image_id'] = $response_body['server']['image']['id'];
		$context['flavor_id'] = $response_body['server']['flavor']['id'];
		//$manufacturer_id = 14020601;
		//$model_id = 14020601;

		$manufacturer_id = $context['manufacturerId'];
		$model_id = $context['modelId'];
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
		// Retrieve floating from server details
		foreach ($endpoints as &$endpoint) {
			if ($endpoint["type"] == "network") {
				$stacks_endpoint = $endpoint["publicURL"];
			}
		}
		$device_ip_address = "";
		$isFloatingIpFind = false;
		if (isset($context['floating_ip_id']) && !empty($context['floating_ip_id'])) {
			$floatingData = json_decode(get_floating_ip_address_and_network_from_id($context['token_id'], $stacks_endpoint, $context['floating_ip_id']), 1);
			logToFile(print_r($floatingData, true));
			$device_ip_address = $floatingData['wo_newparams']['floating_ip_address'];
			$context['floating_network_id'] = $floatingData['wo_newparams']['floating_ip_network'];
			//logToFile("Floating IP is ::::::::: ----------------------> : " . $device_ip_address . "\n");
		  }	
			if (empty($device_ip_address)) {
				logToFile(debug_dump($response_body['server']['addresses'], "SLE_DEBUG_SERVER_ADDRESSES ***************************"));
				foreach ($response_body['server']['addresses'] as &$subnet) {
					// TODO: REMOVE AFTER TM POC
					logToFile(debug_dump($subnet[0]['addr'], "SLE_DEBUG_SUBNETDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD"));
					if (strpos($subnet[0]['addr'], "10.134.160.") !== false) {
						$device_ip_address = $subnet[0]['addr'];
						break;
					}
				} 
			}
		if (empty($device_ip_address)) {
			echo "Missing device management IP address (Floating IP): MSA device creation FAILED.";
			exit;
		}	
	
		logToFile("SLE_DEBUG: ________________________+++++++++++++ " .  $device_ip_address . "\n"); 
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

$response = prepare_json_response(ENDED, "MSA Devices created successfully with ID:" . $device_id, $context, true);
echo $response;

?>
