<?php 

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
   create_var_def('application_device_id', 'Device');
   create_var_def('var_name1', 'String');
}

$device_id = substr($context['application_device_id'], 3);

$command1 = "stop " . $context['var_name1'];
$command2 = "start abc";
$configuration = "$command1\n$command2\n";

/**
Dont change below code. Only change commands above.
*/

$response = _device_do_push_configuration_by_id($device_id, $configuration);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}
	
$response = wait_for_pushconfig_completion($device_id, $context);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}
$pushconfig_status_message = $response['wo_comment'];

$response = prepare_json_response(ENDED, $pushconfig_status_message, $context, true);
echo $response;

?>