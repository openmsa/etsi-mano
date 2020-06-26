<?php

require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

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

$array = array();
// Get Service instance reference from $context
$ns_service_id = $context['ns_instance_id'];

$process_info = $context[$service_name][$ns_service_id]['context'];
logToFile("============================== ".print_r($process_info, TRUE));
$service_instance_ref = '';
foreach($process_info as $pi) {
	logToFile("----------------------------".$pi['name']);
	if($pi['name'] == 'SERVICEINSTANCEREFERENCE') {
		$service_instance_ref = $pi['value'];
		break;
	}
}

msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, '{}', false, $service_instance_ref);

task_exit(ENDED, "The NS was terminated successfully.");

?>
