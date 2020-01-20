<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args() {
}

$device_id = substr($context['nfvoDevice'], 3);

foreach ($context['servers'] as &$server) {

	$var_config_name = "VNF_INSTANCE." . $context['SERVICEINSTANCEREFERENCE'] . "." . $server['device_id'];
	$var_config_value = "";
	if (isset($server['instance_id'])) {
		$var_config_value = $server['instance_id'];
	}
	$response = _configuration_variable_create ($device_id, $var_config_name, $var_config_value);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}
}

$response = prepare_json_response(ENDED, "Configuration variable VNF_INSTANCE_ID created successfully to the device.", $context, true);
echo $response;

?>
