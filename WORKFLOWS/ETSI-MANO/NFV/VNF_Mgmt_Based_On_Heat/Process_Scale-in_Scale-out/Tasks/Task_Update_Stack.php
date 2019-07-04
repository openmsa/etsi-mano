<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
  create_var_def('cluster_size', 'Integer');
}


if (!empty($context['deviceid'])){
	$devicelongid = substr($context['deviceid'], 3);
	$update=_obmf_update('{"stacklist":{"'.$context['stackid'].'":{"object_id":"'.$context['stackid'].'","stackname":"'.$context['stackname'].'","parameters":[{"paramname":"director_image_name","paramvalue":"'.$context['director_image_name'].'"},{"paramname":"director_flavor_name","paramvalue":"'.$context['director_flavor_name'].'"},{"paramname":"inspector_image_name","paramvalue":"'.$context['inspector_image_name'].'"},{"paramname":"inspector_flavor_name","paramvalue":"'.$context['inspector_flavor_name'].'"},{"paramname":"public_net_id","paramvalue":"'.$context['public_net_id'].'"},{"paramname":"net_mgmt_id","paramvalue":"'.$context['net_mgmt_id'].'"},{"paramname":"net_mgmt_subnet_id","paramvalue":"'.$context['net_mgmt_subnet_id'].'"},{"paramname":"net_mgmt_cidr","paramvalue":"'.$context['net_mgmt_cidr'].'"},{"paramname":"director_ip","paramvalue":"'.$context['director_ip'].'"},{"paramname":"dns_nameservers","paramvalue":"'.$context['dns_nameservers'].'"},{"paramname":"net_sfc_id","paramvalue":"'.$context['net_sfc_id'].'"},{"paramname":"activate_code","paramvalue":"'.$context['activate_code'].'"},
{"paramname":"cluster_size","paramvalue":"'.$context['cluster_size'].'"}],"template_url":"'.$context['template_url'].'"}}}',$devicelongid);
	
$status=substr($update,strpos($update, "<status>")+8);
$status=substr($status,0,strpos($status,"</status>"));
$message="";
if ($status=="ERROR"){
	//get reason
	$reason=substr($update,strpos($update, "<message>")+9);
	$reason=substr($reason,0,strpos($reason, "</message>"));
	echo prepare_json_response(FAILED, $reason, '');
}else{
	//stack is launched
	$message.="Stack is launched \n";
	update_asynchronous_task_details($context, $message);
	
	//check result
	$stat="UPDATE_IN_PROGRESS";
	$message.="Deployment status: ".$stat;
    	while ($stat=="UPDATE_IN_PROGRESS") {
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
	if ($stat=="UPDATE_COMPLETE"){
		echo prepare_json_response(ENDED,"Stack id ".$context['stackid']." updated",$context, true);
	}else{
		echo prepare_json_response(FAILED, 'Cannot terminate the stack', '');
	}
}
}else{
    echo prepare_json_response(FAILED, 'Missing parameters', '');
}
?>
