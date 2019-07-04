<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
  //create_var_def('deviceid', 'Device');
  //create_var_def('template_url', 'String');
  //create_var_def('stackname', 'String');
}


if (!empty($context['deviceid'])){
	$devicelongid = substr($context['deviceid'], 3);
	$create=_obmf_create('{"stack":{"'.$context['stackname'].'":{"object_id":"'.$context['stackname'].'","parameters":[],"template_url":"'.$context['template_url'].'"}}}',$devicelongid);
	
$status=substr($create,strpos($create, "<status>")+8);
$status=substr($status,0,strpos($status,"</status>"));
$message="";
if ($status=="ERROR"){
	//get reason
	$reason=substr($create,strpos($create, "<message>")+9);
	$reason=substr($reason,0,strpos($reason, "</message>"));
	echo prepare_json_response(FAILED, $reason, '');
}else{
	//stack is launched
	$message.="Stack is launched \n";
	update_asynchronous_task_details($context, $message);
	
	//check result
	$stat="CREATE_IN_PROGRESS";
	$message.="Deployment status: ".$stat;
    	while ($stat=="CREATE_IN_PROGRESS") {
		//wait 3 seconds
		sleep(3);
		//launch import
		$imp=_obmf_import('{"stacklist":"0"}',$devicelongid);
		$st = substr($imp,strpos($imp, "stack_status&quot")+25);
		$stat = substr($st,0,strpos($st, "&quot;"));
		$id = substr($imp,strpos($imp, "id&quo")+15);
		$id = substr($id,0,strpos($id, "&quot;"));
		$context['stackid']=$id;
		$message.=".";
		update_asynchronous_task_details($context, $message);
	}
	$message.="\n";
	if ($stat=="CREATE_COMPLETE"){
		echo prepare_json_response(ENDED,"Stack is deployed with id ".$context['stackid'],$context, true);
	}else{
		echo prepare_json_response(FAILED, 'Cannot terminated the stack', '');
	}
}
}else{
    echo prepare_json_response(FAILED, 'Missing parameters', '');
}
?>
