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

if (isset($context["ns_routers"]) && ! empty($context["ns_routers"])) {
	foreach ($context['ns_routers'] as &$router) {
		$response = remove_all_router_interfaces($auth_token, $neutron_endpoint, $router);
		logToFile(debug_dump($response, "HTH1"));
		$wsws1 = $response['wo_status'];
		logToFile("HTH1.01  $response :: $wsws1 ::");
		$response = json_decode($response, true);
		$wsws2 = $response['wo_status'];
		logToFile("HTH1.02  $response :: $wsws2");
		if ($response['wo_status'] !== ENDED) {
			// logToFile(debug_dump($response, "HTH1.2" ));
			$response = json_encode($response);
			logToFile(debug_dump($response, "HTH1.3"));
			return $response;
		}
		logToFile(debug_dump($response, "HTH2"));
	}
} else {
	task_exit(ENDED, "Nothing to be completed.");
}

task_exit(ENDED, "Routers interfaces were removed successfully.");

