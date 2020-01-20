<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{}

$ubiqube_id = $context['UBIQUBEID'];
if (! isset($context['lm_wf_service_instance_ref'])) {
	task_exit(ENDED, "Skip task: no VNF instance to Live-migrate.");
}
$lm_wf_service_instance_ref = $context['lm_wf_service_instance_ref'];

if (empty($lm_wf_service_instance_ref)) {
	task_exit(ENDED, "Skip task: no VNF instance to Live-migrate.");
}

foreach ($lm_wf_service_instance_ref as $service_instance_ref) {
	$body = array(
		"host" => $context['destination_hypervisor']
	);
	$json = json_encode($body);

	$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
	$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Live-Migrate_VNF";
	msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $json, false, $service_instance_ref);
}
task_exit(ENDED, "Automatic Live-migration OK");

