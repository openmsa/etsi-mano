<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{
	create_var_def ('is_auto_scale', 'Boolean');
	create_var_def ('old_flavor', 'String');
	create_var_def ('flavor','OBMFRef');
}

if ($context['is_auto_scale'] == "true") {

	foreach ($context['servers'] as $server) {
		$server_id = $server['instance_id'];
	}

	$response = _nova_get_server_details ($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server_id);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		task_exit(FAILED, "Server got details failed.");
	}
	$server_details = json_decode($response['wo_newparams']['response_body'], true);
	logToFile(debug_dump($server_details , "SERVER_DETAILS ====================\n "));
	$flavor_id = $server_details['server']['flavor']['id'];

	$response = _nova_get_flavor_details ($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $flavor_id);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		task_exit(FAILED, "Flavors get details failed.");
	}
	$flavor_details = json_decode($response['wo_newparams']['response_body'], true);
	logToFile(debug_dump($flavor_details, "FLAVOR_DETAILS ====================\n "));
	$flavor_name = $flavor_details['flavor']['name'];
	$context['flavor'] = $flavor_name;
	$context['flavor_details'] = $flavor_details;

	task_exit(ENDED, "Flavor get instance flavor details successfully.");
} else {
	
	$context['flavor_scale'] = $context['flavor'];
	task_exit(ENDED, "Skip tasks: it is not required.");
}
?>
