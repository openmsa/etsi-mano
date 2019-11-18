<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{	
}

foreach ($context['servers'] as $server) {
	$server_id = $server['instance_id'];
}

$nova_endpoint = $context['endpoints'][NOVA][PUBLIC_URL];

$response =_nova_os_migration_live ($nova_endpoint, $context['token_id'], $server_id, $context['hypervisor_name']);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

$response = prepare_json_response(ENDED, "VNF Instance was migrated successfully to the '" . $context['hypervisor_name'] . "' hypervisor.", $context, true);
echo $response;

?>
