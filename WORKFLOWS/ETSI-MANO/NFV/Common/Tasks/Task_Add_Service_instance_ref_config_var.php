<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{}

foreach ($context['servers'] as $server) {

	$device_id = substr($server['device_id'], 3);
	$response = _configuration_variable_create($device_id, "VNFM_SERVICE_INSTANCE_REFERENCE", $context['SERVICEINSTANCEREFERENCE']);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit();
	}
}

$response = prepare_json_response(ENDED, "Configuration variable VNFM_SERVICE_INSTANCE_REFERENCE created successfully to the device.", $context, true);
echo $response;

