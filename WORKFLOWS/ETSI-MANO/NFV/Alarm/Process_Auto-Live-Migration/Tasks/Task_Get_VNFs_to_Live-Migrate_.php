<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{}

// Hardcode VIM hypervisor
$hypervisor_device = "fta-contrailsriov-0.localdomain";

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
		if (strpos($name, "VNF_INSTANCE_ID") !== false) {
			$server_id = $conf_variable['value'];

			// Get server details, then get the server host (hypervisor) id:
			$response = _nova_get_server_details($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id'], $server_id);
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

task_exit(ENDED, "VNF instances need to be migreted were listed.");

