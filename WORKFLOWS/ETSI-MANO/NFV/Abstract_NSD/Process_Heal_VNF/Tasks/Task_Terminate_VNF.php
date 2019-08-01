<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
  create_var_def('vnfPkgId', 'String');
}
check_mandatory_param('vnfPkgId');
$vnfPkgId = $context['vnfPkgId'];

$vnfPack_list = unserialize($context['vnfPack_list']);

$vnfServiceId = $vnfPack_list[$vnfPkgId];
$ubiqube_id = $context['UBIQUBEID'];
$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";

$process_info = $context[$service_name][$vnfServiceId]['context'];

$service_instance_ref = '';
foreach ($process_info as $pi) {
	if ($pi['name'] == 'SERVICEINSTANCEREFERENCE') {
	$service_instance_ref = $pi['value'];
		break;
	}
}
msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, '{}', false, $service_instance_ref);

unset($vnfPack_list[$vnfPkgId]);
$context['vnfPack_list'] = serialize($vnfPack_list);

task_exit(ENDED, "Task OK");

?>
