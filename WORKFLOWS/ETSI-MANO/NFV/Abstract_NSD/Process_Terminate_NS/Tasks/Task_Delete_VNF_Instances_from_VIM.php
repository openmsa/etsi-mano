<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

use Ubiqube\EtsiMano\VnfPkgSol005;
use Symfony\Component\Yaml\Yaml;

function list_args()
{
    // Empty
}

// Execute WF via MSA API
$i=0;
if(!isset($context['vnfPack_list'])) {
    task_exit(ENDED, "No VNF to stop.");
}
$vnfPkg_list = unserialize($context['vnfPack_list']);

foreach ($vnfPkg_list as &$vnfServiceId) {
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

    // Store VNF stack_id in $context from the VNF WF baseline response.
    $context['vnfPack_list']['vnf_instances_ids'] = "";
}
task_exit(ENDED, "The VNF was instantiate successfully where ID: ");

?>
