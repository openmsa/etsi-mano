<?php
require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

use Ubiqube\EtsiMano\VnfPkgSol005;
use Symfony\Component\Yaml\Yaml;

function list_args()
{
	// Empty
}

// Execute WF via MSA API
$i = 0;
if (! isset($context['vnfPack_list'])) {
	task_exit(ENDED, "Skip task: VNF references packages list is empty.");
}
$vnfPkg_list = $context['vnfPack_list'];

foreach ($vnfPkg_list as &$vnfServiceId) {
	$ubiqube_id = $context['UBIQUBEID'];
	$service_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/VNF_Mgmt_Based_On_Heat";
	$process_name = "Process/ETSI-MANO/NFV/VNF_Mgmt_Based_On_Heat/Process_Push_Routing_Config";

	$process_info = $context[$service_name][$vnfServiceId]['context'];

	$service_instance_ref = '';
	// Step 1: Prepare the request body
	$linux_man_id = "14020601";
	$juniper_man_id = "18";
	$json_body = array();

	$server_addresses_details = "";
	$image = "";
	$location = "";
	$server_addresses_details = "";
	$manufacturerId = "";
	foreach ($process_info as $pi) {
		// Get variables
		if ($pi['name'] == 'manufacturerId') {
			$manufacturerId = $pi['value'];
		} else if ($pi['name'] == 'image') {
			$image = $pi['value'];
		} else if ($pi['name'] == 'location') {
			$location = $pi['value'];
		} else if ($pi['name'] == 'server_addresses_details') {
			$server_addresses_details = $pi['value'];
		} else if ($pi['name'] == 'service_instance_ref') {
			$service_instance_ref = $pi['value'];
		}

		// decode $server_addresses_details

		$server_addresses_details_json = base64_decode($server_addresses_details);
		$server_addresses_details = json_decode($server_addresses_details_json, true);

		logToFile(debug_dump($server_addresses_details, "TTTTTTTTTTTTT"));

		// Filter server by man and others criteria
		if (isset($server_addresses_details) && ! empty($server_addresses_details)) {
			if ($manufacturerId == $linux_man_id && strpos($image, "CentOS") !== false) {
				if ($location == "LEFT") {
					foreach ($output as $key => $subnet_details) {
						if ($key == "net4") {
							$json_body['left_centos_ip'] = $subnet_details[0]['addr'];
						}
					}
				} else {
					foreach ($output as $key => $subnet_details) {
						if ($key == "net2") {
							$json_body['right_centos_ip'] = $subnet_details[0]['addr'];
						}
					}
				}
			} else if ($manufacturerId == $linux_man_id && strpos($image, "CWAIO" !== false)) {
				foreach ($output as $key => $subnet_details) {
					if ($key == "net3") {
						$json_body['ims_inbound_ip'] = $subnet_details[0]['addr'];
					} else if ($key == "net2") {
						$json_body['ims_outbound_ip'] = $subnet_details[0]['addr'];
					}
				}
			} else if ($manufacturerId == $juniper_man_id) {
				foreach ($output as $key => $subnet_details) {
					if ($key == "net4") {
						$json_body['vsrx_inbound_ip'] = $subnet_details[0]['addr'];
					} else if ($key == "net3") {
						$json_body['vsrx_outbound_ip'] = $subnet_details[0]['addr'];
					}
				}
			}
		}
	}
	$json = json_encode($json_body);

	msa_execute_service_by_reference_and_wait_for_completion_failsafe($ubiqube_id, $service_name, $process_name, $json, false, $service_instance_ref);
}
task_exit(ENDED, "VNF routing configurations are done successfully. ");


