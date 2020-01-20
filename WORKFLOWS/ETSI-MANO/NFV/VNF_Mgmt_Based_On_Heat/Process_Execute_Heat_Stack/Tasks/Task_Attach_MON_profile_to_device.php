<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args() {
}

foreach ($context['servers'] as $server) {

if (!isset($context['monitoring_profile_ref']) || empty($context['monitoring_profile_ref'])) {
	$response = prepare_json_response(ENDED, "Skip task.", $context, true);
	echo $response;
	exit;
}

$response = _profile_attach_to_device_by_reference($context['monitoring_profile_ref'], $server['device_id']);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}
}

$response = prepare_json_response(ENDED, "Device monitoring profil attached successfull.", $context, true);
echo $response;

