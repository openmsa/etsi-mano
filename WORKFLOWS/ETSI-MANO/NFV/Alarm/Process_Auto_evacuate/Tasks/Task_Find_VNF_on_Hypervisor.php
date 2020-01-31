<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

$failed_hypervisor_name = "";
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
			if ($conf_variable['name'] == "hypervisor_id") {
				$failed_hypervisor_name = $conf_variable['value'];
			}
			break;
		}
	}

	$context['failed_hyper_name'] = $failed_hypervisor_name;
}

$hypervisor_device = $failed_hypervisor_name;

$nfvo_device = substr($context['nfvo_device'], 3);
$lm_wf_service_instance_ref = array();

// Get the device config variable.
$response = _configuration_variable_list($nfvo_device);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit();
}

if (isset($response['wo_newparams']) && ! empty($response['wo_newparams'])) {
	foreach ($response['wo_newparams'] as &$conf_variable) {
		$name = $conf_variable['name'];
		if (strpos($name, "VNF_INSTANCE") !== false) {
			$server_id = $conf_variable['value'];

			// Get server details, then get the server host (hypervisor) id:
			$response = _nova_get_server_details($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server_id);
			logToFile("RESPONSE TH1\n $response");
			$response = json_decode($response, true);

			if ($response['wo_status'] == ENDED) {


				$server_details = json_decode($response['wo_newparams']['response_body'], true);
				$hypervisor_id = $server_details['server']['OS-EXT-SRV-ATTR:hypervisor_hostname'];
				if ($hypervisor_device == $hypervisor_id) {
					$tab = explode(".", $name);
					$lm_wf_service_instance_ref[] = $tab[1];
				}
			}
		}
	}
}

$context['lm_wf_service_instance_ref'] = $lm_wf_service_instance_ref;

task_exit(ENDED, "VNF instances need to be migrated were listed.");

