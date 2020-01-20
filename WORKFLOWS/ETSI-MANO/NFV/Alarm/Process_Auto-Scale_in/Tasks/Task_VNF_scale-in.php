<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
	create_var_def('vnfm_service_instance_ref', 'String');
}
$ubiqube_id = $context['UBIQUBEID'];
$service_instance_ref = $context['vnfm_service_instance_ref'];

$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Scale_In";
msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, '{}', false, $service_instance_ref);

task_exit(ENDED, "Auto-Scale-in OK");

