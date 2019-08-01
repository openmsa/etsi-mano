<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
	create_var_def('vnfPkgId', 'String');
}

# Execute WF via MSA API

$ubiqube_id = $context['UBIQUBEID'];
$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Scale-in_Scale-out";
$nsPkgId = $context['nsPkgId'];

$array = array("nsPkgId" => $nsPkgId);
$json_body = json_encode($array);

msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $json_body, true);

# Store stack_id in $context list from the NS baseline response. 
$context[$vnfPkgId]['ns_instance_id'] = $context['executed_service_id'];

task_exit(ENDED, "Scale out of $vnfPkgId Successfull");

?>