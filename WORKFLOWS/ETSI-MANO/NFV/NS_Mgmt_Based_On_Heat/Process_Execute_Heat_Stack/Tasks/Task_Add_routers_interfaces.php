<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{}

$stack_id = $context['stackid'];
$stack_name = $context['stackname'];
$auth_token = $context['token_id'];
$endpoints = $context['endpoints'];

foreach ($endpoints as &$endpoint) {
	if ($endpoint["type"] == "network") {
		$neutron_endpoint = $endpoint["publicURL"];
	}
}

$networks = array();
$networks[0] = "net4";
$networks[1] = "net2";

$routers = array();
$routers[0] = "msa_router-left";
$routers[1] = "msa_router-right";

$subnets_id = array();

$response = _neutron_subnets_tm_poc($neutron_endpoint, $auth_token, $networks);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	return $response;
}

$subnets_id = $response['wo_newparams'];
$context['nsd_rt_subnets'] = $subnets_id;

$response = _neutron_routers_by_routers_name($neutron_endpoint, $auth_token, $routers);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	return $response;
}

if (isset($response['wo_newparams']) && ! empty($response['wo_newparams'])) {
	$context['ns_routers'] = $response['wo_newparams'];
	$index = 0;
	foreach ($response['wo_newparams'] as &$rt) {
		$response2 = _neutron_router_interface_add($neutron_endpoint, $auth_token, $rt, $subnets_id[$index][0]);
		if ($response2['wo_status'] !== ENDED) {
			$response2 = json_encode($response2);
			task_exit(FAILED, "$response2");
			return $response2;
		}
		$index ++;
	}
}

task_exit(ENDED, "Routers interfaces are attached successfully.");



