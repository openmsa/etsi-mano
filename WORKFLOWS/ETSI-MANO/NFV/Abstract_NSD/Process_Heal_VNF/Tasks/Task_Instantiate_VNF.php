<?php
require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

$vnfPack_list = unserialize($context['vnfPack_list']);
$vnfPkgId = $context['vnfPkgId'];

$ubiqube_id = $context['UBIQUBEID'];
$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";

$array = array("vnfPkgId" => $vnfPkgId);
$json_body = json_encode($array);

msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $json_body, true);

$vnfPack_list[$vnfPkgId] = $context['executed_service_id'];

$context['vnfPack_list'] = serialize($vnfPack_list);
/**
 * End of the task do not modify after this point
 */
task_exit(ENDED, "Task OK");

?>
