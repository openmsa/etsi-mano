<?php
include_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/nova_rest.php';

function list_args()
{
	create_var_def('network_id', 'String');
}

$network_id = $context('network_id');


$server_id = $context['$server_id']['instance_id'];

_nova_interface_attach($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server_id, "", $network_id);



task_exit(ENDED, "Task OK");

?>
