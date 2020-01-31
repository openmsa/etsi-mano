<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
	create_var_def('nfvo_service_instance_ref', 'String');
	create_var_def('vnfm_service_instance_ref', 'String');
}
$ubiqube_id = $context['UBIQUBEID'];
$service_instance_ref = $context['nfvo_service_instance_ref'];

$body = array(
	"service_instance_ref" => $context['vnfm_service_instance_ref']
);
$json = json_encode($body);

$service_name = "Process/ETSI-MANO/NFV/Abstract_NSD/Abstract_NSD";
$process_name = "Process/ETSI-MANO/NFV/Abstract_NSD/Process_Scale-in_NS";
msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $json, false, $service_instance_ref);

task_exit(ENDED, "NS Auto-Scale-in OK");

