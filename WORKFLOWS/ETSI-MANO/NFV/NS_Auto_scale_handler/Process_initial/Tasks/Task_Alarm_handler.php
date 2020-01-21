<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
	create_var_def('customer_id', 'String');
	create_var_def('device_id', 'String');
	create_var_def('rawlog', 'String');
}

$device_id = substr($context['device_id'], 3);
$ubiqube_id = $context['UBIQUBEID'];
$service_instance_ref = $context['SERVICEINSTANCEREFERENCE'];
$alarm_wf_path = "Process/ETSI-MANO/NFV/NS_Auto_scale_handler/";
$operation_name = "None";

// Get VNFM_SERVICE_INSTANCE_REFERENCE from the device config variable.
$response = _configuration_variable_list($device_id);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit();
}

$nfvo_service_instance_ref = "";
$vnfm_service_instance_ref = "";

if (isset($response['wo_newparams']) && ! empty($response['wo_newparams'])) {
	foreach ($response['wo_newparams'] as &$conf_variable) {
		if ($conf_variable['name'] == "NFVO_SERVICE_INSTANCE_REFERENCE") {
			$nfvo_service_instance_ref = $conf_variable['value'];
		} else if ($conf_variable['name'] == "VNFM_SERVICE_INSTANCE_REFERENCE") {
			$vnfm_service_instance_ref = $conf_variable['value'];
		}
	}
}

$body = array(
	"nfvo_service_instance_ref" => $nfvo_service_instance_ref,
	"vnfm_service_instance_ref" => $vnfm_service_instance_ref
);
$body_json = json_encode($body);

// Check the alarm type and is corresponding operation to trigge.
$tab = array();
$tab[0] = "CPU Utilization threshold";
$tab[1] = "idle";

if (! empty($context['rawlog'])) {
	if (strpos($context['rawlog'], $tab[0]) !== false) {
		$service_name = $alarm_wf_path . "NS_Auto_scale_handler";
		$process_name = $alarm_wf_path . "Process_NS_Auto_Scale-out";
		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
		$operation_name = "Scale-out VNF";
	} else if (strpos($context['rawlog'], $tab[1]) !== false) {
		$service_name = $alarm_wf_path . "NS_Auto_scale_handler";
		$process_name = $alarm_wf_path . "Process_NS_Auto_Scale-in";
		msa_execute_service_by_reference_and_wait_for_completion($ubiqube_id, $service_name, $process_name, $body_json, false, $service_instance_ref);
		$operation_name = "Scale-in VNF";
	} else {
		task_exit(ENDED, "No alarm rawlog: " . $context['rawlog'] . " matched in the list.");
	}
}
task_exit(ENDED, "Alarm process handler is triggered according to the alarm rawlog: " . $operation_name);

