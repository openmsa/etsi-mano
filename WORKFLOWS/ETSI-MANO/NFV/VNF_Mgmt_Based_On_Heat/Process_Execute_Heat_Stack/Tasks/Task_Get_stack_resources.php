<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args() {
}

$stack_id = $context['stackid'];
$stack_name = $context['stackname'];
$auth_token = $context['token_id'];
$endpoints = $context['endpoints'];

foreach ($endpoints as &$endpoint) {
	if ($endpoint["type"] == "orchestration") {
		$stacks_endpoint = $endpoint["publicURL"];
	}
}
$resources = "";
$index = 3;
$isResourceOk = false;
while ($isResourceOk !== true && $index > 0) {
	$response = _list_stacks_resources ($stacks_endpoint, $auth_token, $stack_name, $stack_id);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}
	$resources = $response['wo_newparams']['resources'];
	
	// Check that floating IP resource is containing on the stack resources.
	foreach ($resources as &$resource) {
		logToFile("Resource ID: " . $resource['resource_type'] . " ========= \n");
		if ($resource['resource_type'] == "OS::Neutron::FloatingIP") {
			$context['floating_ip_id'] = $resource['physical_resource_id'];
			$isResourceOk = true;
			break;
		}
	}
	$index--;
}
$context['resources'] = $resources;

$response = prepare_json_response(ENDED, "Get stack resources successfully.", $context, true);
echo $response;

?>
