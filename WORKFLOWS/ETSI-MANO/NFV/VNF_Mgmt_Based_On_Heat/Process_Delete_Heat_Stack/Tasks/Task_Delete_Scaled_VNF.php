<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{

}

if(!array_key_exists('servers_scaled', $context)) {
	task_exit(ENDED, "OK, No scaled instance.");
}

$scaled = $context['servers_scaled'];
foreach($scaled as $instance) {
	$ubiqube_id = $context['UBIQUBEID'];
	$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
	$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Scale_In";
	$service_instance_ref = $context['SERVICEINSTANCEREFERENCE'];

	msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, '{}', false, $service_instance_ref);
}


task_exit(ENDED, "Scale out instance removed.");

?>
