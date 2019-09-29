<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args() {
}
$instance_status_pre_rebuild = $context['instance_status_pre_rebuild'];
if ($instance_status_pre_rebuild != "ACTIVATE") {

	foreach ($context['servers'] as $server) {

		$device_ip_address = $server['floating_ip_address'];
		$response = wait_for_ping_status($device_ip_address, $context);
		$response = json_decode($response, true);
		if ($response['wo_status'] !== ENDED) {
			$response = json_encode($response);
			echo $response;
			exit;
		}
	}
	$response = prepare_json_response(ENDED, "MSA Devices Ping check : OK.", $context, true);
	echo $response;

} else {
	$response = prepare_json_response(ENDED, "SKIPPED: VNF instance status before rebuild was: " . $instance_status_pre_rebuild, $context, true);
	echo $response;
}
?>
