<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
	create_var_def('customer_id', 'String');
	create_var_def('device_id', 'String');
	create_var_def('subtype', 'String');
	create_var_def('rawlog', 'String');
}

$ubiqube_id = $context['UBIQUBEID'];
$service_instance_ref = $context['SERVICEINSTANCEREFERENCE'];
$alarm_wf_path = "Process/ETSI-MANO/NFV/Alarm/";
$operation_name = "None";
$vnfm_service_instance_ref = "";
$body = array();

if (isset($context['device_id']) && ! empty($context['device_id'])) {
	$device_id = substr($context['device_id'], 3);
	// Get VNFM_SERVICE_INSTANCE_REFERENCE from the device config variable.
	$response = _configuration_variable_list($device_id);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit();
	}
	if (isset($response['wo_newparams']) && ! empty($response['wo_newparams'])) {
		foreach ($response['wo_newparams'] as &$conf_variable) {
			$out1 = $conf_variable['name'];
			$out2 = $conf_variable['value'];
			logToFile("WAITWAITWAIT $out1 / $out2");
			if ($conf_variable['name'] == "VNFM_SERVICE_INSTANCE_REFERENCE") {
				$vnfm_service_instance_ref = $conf_variable['value'];
				break;
			}
		}
	}

	$body = array(
		"vnfm_service_instance_ref" => $vnfm_service_instance_ref
	);
}

logToFile("WAITWAIT  $vnfm_service_instance_ref ");

if (empty($body)) {
	$body_json = json_encode($body, JSON_FORCE_OBJECT);
} else {
	$body_json = json_encode($body);
}

// Check the alarm type and is corresponding operation to trigger.
$tab = array();
$tab[0] = "SNMPTHLD-CPU_Utilization";
$tab[1] = "Memory_Utilization";
$tab[2] = "DISK_FAILURE";
$tab[3] = "Critical";
$tab[4] = "IPDOWN";

if (isset($context['subtype']) && ! empty($context['subtype'])) {
	if ($context['subtype'] == $tab[0]) {
		$service_name = $alarm_wf_path . "Alarm";
		$process_name = $alarm_wf_path . "Process_Auto-Scale_out";
		msa_execute_service_by_reference_and_wait_for_completion_failsafe($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
		$operation_name = "Scale-out VNF";
	}
	if ($context['subtype'] == $tab[4]) {
		$service_name = $alarm_wf_path . "Alarm";
		$process_name = $alarm_wf_path . "Process_Auto_evacuate";
		msa_execute_service_by_reference_and_wait_for_completion_failsafe($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
		$operation_name = "Auto Evacuate";
	}
} else {

	if (isset($context['rawlog']) && ! empty($context['rawlog'])) {
		if (strpos($context['rawlog'], $tab[1]) !== false) {
			$service_name = $alarm_wf_path . "Alarm";
			$process_name = $alarm_wf_path . "Process_Auto-Scaling";
			msa_execute_service_by_reference_and_wait_for_completion_failsafe($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
			$operation_name = "Scale UP VNF";
		} else {
			if (strpos($context['rawlog'], $tab[2]) !== false) {
				$service_name = $alarm_wf_path . "Alarm";
				$process_name = $alarm_wf_path . "Process_Auto-Heal";
				msa_execute_service_by_reference_and_wait_for_completion_failsafe($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
				$operation_name = "Heal VNF";
			} else {
				if (strpos($context['rawlog'], $tab[3]) !== false) {

					$service_name = $alarm_wf_path . "Alarm";
					$process_name = $alarm_wf_path . "Process_AutoLiveMigration";
					msa_execute_service_by_reference_and_wait_for_completion_failsafe($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
					$operation_name = "Live-migrate VNF";
				}
			}
		}
	} else {
		task_exit(ENDED, "No alarm rules matched in the list.");
	}
}

task_exit(ENDED, "Alarm process handler is triggered according to the alarm subtype: " . $operation_name);

