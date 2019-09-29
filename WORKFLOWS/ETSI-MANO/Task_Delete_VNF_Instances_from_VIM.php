<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

use Ubiqube\EtsiMano\VnfPkgSol005;
use Symfony\Component\Yaml\Yaml;

function list_args()
{
  // Empty
}

# Execute WF via MSA API

$ubiqube_id = $context['UBIQUBEID'];
$service_name = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/NS_Mgmt_Based_On_Heat";
$process_name = "Process/ETSI-MANO/NFV/NS_Mgmt_Based_On_Heat/Process_Delete_Heat_Stack";
$nsPkgId = $context['nsPkgId'];

$array = array("nsPkgId" => $nsPkgId);
$json_body = json_encode($array);

msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $json_body, false);

# Store stack_id in $context list from the NS baseline response. 
$context['ns_instance_id'] = "";

task_exit(ENDED, "The NS was instantiated successfully with ID: .");

?>