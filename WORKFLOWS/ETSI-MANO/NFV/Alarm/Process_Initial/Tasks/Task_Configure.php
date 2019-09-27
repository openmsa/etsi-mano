<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
function list_args()
{
   create_var_def ('customer_id', 'String');
   create_var_def ('device_id', 'String');
   create_var_def ('alarm_subtype', 'String');

}

$ubiqube_id = $context['UBIQUBEID'];
$service_instance_ref = $context['SERVICEINSTANCEREFERENCE'];
$alarm_wf_path = "Process/ETSI-MANO/NFV/Alarm/";
$operation_name = "None";

// Check the alarm type and is corresponding operation to trigge.
$tab = array();
$tab[0] = "SNMPTHLD-CPU_Utilization";
$tab[1] = "SNMPTHLD-Memory_Utilization";
$tab[1] = "SNMPTHLD-Storage_Utilization";

if (!empty($context['alarm_subtype'])) {
	if ($context['alarm_subtype'] == $tab[0]) {
		$service_name = $alarm_wf_path . "Alarm";
    		$process_name = $alarm_wf_path . "Process_Auto-Scale_out";
		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, '{}', false, $service_instance_ref);
		$operation_name = "Scale-out VNF";
	} else if ($context['alarm_subtype'] == $tab[1]) {
		$service_name = $alarm_wf_path . "Alarm";
    		$process_name = $alarm_wf_path . "Process_Auto-Scaling";
		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, '{}', false, $service_instance_ref);
		$operation_name = "Scale-up VNF";

	} else if ($context['alarm_subtype'] == $tab[2]) {
		$service_name = $alarm_wf_path . "Alarm";
    		$process_name = $alarm_wf_path . "Process_Auto-Heal";
		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, '{}', false, $service_instance_ref);
		$operation_name = "Heal VNF";
	} else {
		task_exit(ENDED, "No alarm subtype: " . $context['alarm_subtype'] . " matched in the list.");
	}
}
task_exit(ENDED, "Alarm process handler is triggered according to the alarm subtype: " . $operation_name);

?>