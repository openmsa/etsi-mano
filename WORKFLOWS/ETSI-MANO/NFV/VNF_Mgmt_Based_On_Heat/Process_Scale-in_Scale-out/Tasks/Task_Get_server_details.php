<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{
}

$device_id = substr($context['deviceid'], 3);
$auth_token = $context['token_id'];
$endpoints = $context['endpoints'];
$server_id = $context['server_id'];

foreach ($endpoints as &$endpoint) {
	if ($endpoint["type"] == "compute") {
		$nova_endpoint = $endpoint["publicURL"];
	}
}

// Get server details from VIM
$response = _nova_get_server_details ($nova_endpoint, $auth_token, $context['server_id']);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

$response_body = json_decode($response['wo_newparams']['response_body'], true);
$isFixedIpFind = false;
foreach ($response_body['server']['addresses'] as &$network_addresses) {
	
	if ($isFixedIpFind !== true) { 
		foreach ($network_addresses as &$address) {
			if ($address['OS-EXT-IPS:type'] == "fixed") {
				$fixed_address = $address['addr'];
				$isFixedIpFind = true;
				break;
			}
		}
	} else {
		break;
	}
}
if (isset($context['servers_scaled'])) {
	foreach ($context['servers_scaled'] as &$server) {
		if (isset($server['server_id']) && $server['server_id'] == $server_id) {
			$server['fixed_address'] = $fixed_address;
		}
	}
}
task_exit(ENDED, "Server details retrieved successfully with server ID: $server_id \n");

?>
