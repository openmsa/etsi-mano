<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{
	create_var_def('host', 'String');
}

if (! empty($context['host'])) {
	$hypervisor_id = $context['host'];
} else {
	task_exit(FAILED, "Hypservisor ID is empty.");
}

$response = _nova_os_hypervisor_details($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $hypervisor_id);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	task_exit(FAILED, "hypervisor (host) get details failed.");
}
logToFile(debug_dump($response, "RESPONSESSSS ====================\n "));
$hypervisor_details = json_decode($response['wo_newparams']['response_body'], true);
logToFile(debug_dump($hypervisor_details, "HYPERVISOR_DETAILS ====================\n "));
$hypervisor_name = $hypervisor_details['hypervisor']['hypervisor_hostname'];
$context['hypervisor_name'] = $hypervisor_name;

task_exit(ENDED, "Hypervisor name get successfully.");

