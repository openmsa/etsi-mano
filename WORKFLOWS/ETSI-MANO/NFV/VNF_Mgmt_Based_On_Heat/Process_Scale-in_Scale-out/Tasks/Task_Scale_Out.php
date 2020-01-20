<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{
create_var_def('scaled_out_device', 'String');
}

$stack_id = $context['stackid'];
$stack_name = $context['stackname'];
$auth_token = $context['token_id'];
$endpoints = $context['endpoints'];

foreach ($endpoints as &$endpoint) {
	if ($endpoint["type"] == "network") {
		$neutron_endpoint = $endpoint["publicURL"];
	}
}

logToFile(debug_dump($context, "CONTEXT ============>:\n"));

$device_id = substr($context['deviceid'], 3);
$random_num = rand(5, 15);
$networks = array();
$server_name = $context['SERVICEINSTANCEREFERENCE']."_scale-out_".$random_num;
$availability_zone = "standard";
$flavor_id = $context['flavor_id'];
$image = $context['simulator_image_id'];
$key = '';
$user_data = "";

// Extract user_data from VNF heat template
if (isset($context['heatJson']) && !empty($context['heatJson'])) {
	$heat_array = unserialize($context['heatJson']);
	//$heat_array = json_decode($heat_json, true);
	$resources = $heat_array['resources'];
	foreach ($resources as &$resource) {
		if ($resource['type'] == "OS::Nova::Server") {
			if (isset($resource['properties']['user_data']['str_replace']['template'])) {
				$preparation_data = str_replace("VAR_IP_ADDRESS_NET_GE_0_0_0/24","192.168.40.200",$resource['properties']['user_data']['str_replace']['template']);
				$preparation_data = str_replace("VAR_IP_ADDRESS_NET_GE_0_0_1/24","192.168.30.200",$preparation_data);

				$user_data = base64_encode($preparation_data);
				break;
			}
		}
	}
}

// TODO : Refactor this part of the code too static.
################
$network_fqdn = array();
$network_fqdn[0]= "Floating-Public";
$network_fqdn[1]= "net4";
$network_fqdn[2]= "net3";

$response = _neutron_networks_from_name ($neutron_endpoint, $auth_token, $network_fqdn);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	return $response;
}

$networks[] = array("uuid" => $response['wo_newparams'][0]);
$networks[] = array("uuid" => $response['wo_newparams'][1]);
$networks[] = array("uuid" => $response['wo_newparams'][2]);

// Create server
//$response = _nova_server_create($device_id, $server_name, $networks, $availability_zone, $flavor_id, $image, array(), $key);
$response = _nova_server_create ($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server_name, $flavor_id, $image, $networks, $availability_zone, $user_data, true);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
    task_exit(FAILED, "Server scale out failed.");
}
$server_id = $response['wo_newparams']['server']['id'];

$response = wait_for_server_status($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server_id, ACTIVE, $context);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

$context['server_id'] = $server_id;

$response = _nova_get_server_details ($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server_id);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	task_exit(FAILED, "Server got details failed.");
}
$server_details = json_decode($response['wo_newparams']['response_body'], true);
logToFile(debug_dump($server_details , "SERVER_DETAILS ====================\n "));
$flavor_id = $server_details['server']['flavor']['id'];
$floating_ip = $server_details['server']['addresses']['Floating-Public'][0]['addr'];

$value = array('server_id' => $server_id, 'name' => $server_name, 'is_msa_device' => false, "floating_ip_address" =>  $floating_ip);
$context['servers_scaled'] = array($server_id => $value);
$context['scaled_out_device'] = $server_id;

task_exit(ENDED, "Server scaled out successfully with server ID: " . $server_id . "\n");

?>
