<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{}

$service_instance_ref = $context['vnfm_service_instance_ref'];
$ubiqube_id = $context['UBIQUBEID'];

$body = array(
	"host" => $context['destination_hypervisor']
);
$json = json_encode($body);

$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Live-Migrate_VNF";
msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $json, false, $service_instance_ref);

logToFile("msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $json, false, $service_instance_ref)");

task_exit(ENDED, "Migrate from $context[hypersource] to $context[hyperdestination]");

