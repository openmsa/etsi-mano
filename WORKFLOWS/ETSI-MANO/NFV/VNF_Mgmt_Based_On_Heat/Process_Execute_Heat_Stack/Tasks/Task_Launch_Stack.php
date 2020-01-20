<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{}

if (! empty($context['deviceid'])) {
	$devicelongid = substr($context['deviceid'], 3);
	$create = json_decode(_order_command_execute($devicelongid, 'CREATE', '{"stack":{"' . $context['stackname'] . '":{"object_id":"' . $context['stackname'] . '","parameters":[],"template_url":"' . $context['template_url'] . '"}}}'), 1);
	$innerMsg = json_decode($create['wo_newparams']['message'], 1);
	$status = $create['wo_newparams']['status'];
	$stackId = $innerMsg['stack']['id'];
	$context['stackid'] = $stackId;
	$context['stack_id'] = $stackId;
	$message = "";
	if ($status == "ERROR") {
		// get reason
		echo prepare_json_response(FAILED, $create['wo_newparams']['message'], '');
	} else {
		// stack is launched
		$message .= "Stack is launched \n";
		update_asynchronous_task_details($context, $message);
		// check result
		$stat = "CREATE_IN_PROGRESS";
		$message .= "Deployment status: " . $stat;
		while ($stat == "CREATE_IN_PROGRESS") {
			// wait 3 seconds
			sleep(3);
			// launch import
			$resp = json_decode(_order_command_execute($devicelongid, 'IMPORT', '{"stacklist":"0"}'), 1);
			$innerMsg = json_decode($resp['wo_newparams']['message'], 1);
			$stack = $innerMsg['stacklist'];
			$stack = $stack[$stackId];
			$stat = $stack['stack_status'];
			$message = $stack['stack_status_reason'];
			update_asynchronous_task_details($context, $message);
		}
		$message .= "\n";
		if ($stat == "CREATE_COMPLETE") {
			echo prepare_json_response(ENDED, "Stack is deployed with id " . $context['stackid'], $context, true);
		} else {
			task_error('Error encountered, check your openstack.');
		}
	}
} else {
	echo prepare_json_response(FAILED, 'Missing parameters', '');
}

