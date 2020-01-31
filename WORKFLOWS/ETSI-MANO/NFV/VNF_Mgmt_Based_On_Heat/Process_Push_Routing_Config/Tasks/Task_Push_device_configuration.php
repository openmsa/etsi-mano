<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{}
$PROCESSINSTANCEID = $context['PROCESSINSTANCEID'];
$EXECNUMBER = $context['EXECNUMBER'];
$TASKID = $context['TASKID'];
$process_params = array(
	'PROCESSINSTANCEID' => $PROCESSINSTANCEID,
	'EXECNUMBER' => $EXECNUMBER,
	'TASKID' => $TASKID
);

foreach ($context['servers'] as $server) {
	$server_id = $server['device_id'];
	$device_id = substr($server_id, 3);
	$response = _device_do_update_config($device_id);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		if (strpos($response['wo_newparams']['comment'], "FIPS") <= 0) {
			$response = json_encode($response);
			echo $response;
			exit();
		} else {

			logToFile("23477 SKIPPED1");
		}
	}
	if ($response['wo_newparams']['status'] !== STATUS_OK) {
		if (strpos($response['wo_newparams']['comment'], "FIPS") <= 0) {

			$wo_comment = "Update Configuration Failed on the Device $device_id";
			$code = $response['wo_newparams']['code'];
			$message = $response['wo_newparams']['message'];
			$rawSmsResult = $response['wo_newparams']['rawSmsResult'];
			$result = $response['wo_newparams']['result'];
			if ($code !== null) {
				$wo_comment .= "\nCode : $code";
			}
			if ($message !== null) {
				$wo_comment .= "\nMessage : $message";
			}
			if ($rawSmsResult !== null) {
				$wo_comment .= "\nRaw SMS Result : $rawSmsResult";
			}
			if ($result !== "") {
				$wo_comment .= "\nResult : $result";
			}
			$response = prepare_json_response(FAILED, $wo_comment, $context, true);
			echo $response;
			exit();
		} else {
			logToFile("23477 SKIPPED2");
		}
	}

	$response = wait_for_update_config_completion($device_id, $process_params);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$commit = strpos($response['wo_comment'], "commit complete");
		logToFile("23477 commit $commit");
		if ($commit <= 0) {
			$response = prepare_json_response(FAILED, $response['wo_comment'], $context, true);
			echo $response;
			exit();
		}
	}
	$update_config_status_message = $response['wo_comment'];

	$response = prepare_json_response(ENDED, "Device $device_id Update config completed successfully.\n" . $update_config_status_message, $context, true);
	echo $response;
}
