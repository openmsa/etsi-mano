<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

use Ubiqube\EtsiMano\VnfPkgSol005;
use Symfony\Component\Yaml\Yaml;

function list_args()
{
  // Empty
}

$vnfArrs = array();
# Execute WF via MSA API
$vnfPack_list = $context['vnfPkgs'];
if (isset($vnfPack_list) && !empty($vnfPack_list)) {
	foreach ($vnfPack_list as &$vnfPkgId) {
		$ubiqube_id = $context['UBIQUBEID'];
		$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
		$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Execute_Heat_Stack";

		$array = array("vnfPkgId" => $vnfPkgId['vnfPkgId']);
		$json_body = json_encode($array);

		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $json_body, true);

		# Store VNF stack_id in $context from the VNF WF baseline response. 
		$vnfArrs[$vnfPkgId['vnfPkgId']] = $context['executed_service_id'];
	}
}
$context['vnfPack_list'] = serialize($vnfArrs);
task_exit(ENDED, "The VNF was instantiate successfully with ID: ". $context['vnfPack_list'] ." .");

?>
