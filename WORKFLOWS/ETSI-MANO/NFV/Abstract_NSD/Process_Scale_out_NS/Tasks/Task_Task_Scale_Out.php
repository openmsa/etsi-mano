<?php

require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

function list_args()
{
	create_var_def('vnfPkgId', 'String');
	create_var_def('service_instance_ref', 'String');
}
$service_instance_ref = "";
$ubiqube_id = $context['UBIQUBEID'];
$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Scale-in_Scale-out";

if (empty($context['service_instance_ref'])) {
# Execute WF via MSA API
//$vnfPack_list = unserialize($context['vnfPack_list']);
if(!isset($context['vnfPack_list'])) {
    task_exit(ENDED, "Skip task: VNF references packages list is empty.");
}
$vnfPkg_list = $context['vnfPack_list'];
$vnfServiceId = $vnfPkg_list[$context['vnfPkgId']];
$vnfPkgId = $context['vnfPkgId'];


$process_info = $context[$service_name][$vnfServiceId]['context'];

$service_instance_ref = '';
foreach ($process_info as $pi) {
	if ($pi['name'] == 'SERVICEINSTANCEREFERENCE') {
	$service_instance_ref = $pi['value'];
		break;
	}
} 

} else {
	$service_instance_ref = $context['service_instance_ref'];
}
msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, '{}', false, $service_instance_ref);

# Store stack_id in $context list from the NS baseline response. 
//$context[$vnfPkgId]['ns_instance_id'] = $context['executed_service_id'];

//task_exit(ENDED, "Scale out of $vnfPkgId Successfull");
task_exit(ENDED, "Scale out of NS is Successfull");

?>
