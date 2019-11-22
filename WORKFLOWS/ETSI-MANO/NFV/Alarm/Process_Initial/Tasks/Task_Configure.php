<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
function list_args()
{
   create_var_def ('customer_id', 'String');
   create_var_def ('device_id', 'String');
   create_var_def ('subtype', 'String');

}
$device_id = substr($context['device_id'], 3);
$ubiqube_id = $context['UBIQUBEID'];
$service_instance_ref = $context['SERVICEINSTANCEREFERENCE'];
$alarm_wf_path = "Process/ETSI-MANO/NFV/Alarm/";
$operation_name = "None";
$vnfm_service_instance_ref = "";

// Get VNFM_SERVICE_INSTANCE_REFERENCE from the device config variable.
$response = _configuration_variable_list ($device_id);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

//logToFile(debug_dump($response, "RESPONSE OF VAR CONF =========================== \n"));

if (isset($response['wo_newparams']) && !empty($response['wo_newparams'])) {
	foreach ($response['wo_newparams'] as &$conf_variable) {
		if ($conf_variable['name'] == "VNFM_SERVICE_INSTANCE_REFERENCE") {
			$vnfm_service_instance_ref =  $conf_variable['value'];
		} 
		break;
	}
}

$body = array("vnfm_service_instance_ref" => $vnfm_service_instance_ref);
$body_json = json_encode($body);

// Check the alarm type and is corresponding operation to trigge.
$tab = array();
$tab[0] = "SNMPTHLD-CPU_Utilization";
$tab[1] = "SNMPTHLD-Memory_Utilization";
$tab[2] = "SNMPTHLD-Storage_Utilization";

if (!empty($context['subtype'])) {
	if ($context['subtype'] == $tab[0]) {
		$service_name = $alarm_wf_path . "Alarm";
    		$process_name = $alarm_wf_path . "Process_Auto-Scale_out";
		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
		$operation_name = "Scale-out VNF";
	} else if ($context['subtype'] == $tab[1]) {
		$service_name = $alarm_wf_path . "Alarm";
    		$process_name = $alarm_wf_path . "Process_Auto-Scaling";
		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
		$operation_name = "Scale-up VNF";

	} else if ($context['subtype'] == $tab[2]) {
		$service_name = $alarm_wf_path . "Alarm";
    		$process_name = $alarm_wf_path . "Process_Auto-Heal";
		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
		$operation_name = "Heal VNF";
	} else {
		task_exit(ENDED, "No alarm subtype: " . $context['subtype'] . " matched in the list.");
	}
}
task_exit(ENDED, "Alarm process handler is triggered according to the alarm subtype: " . $operation_name);

?>