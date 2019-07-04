<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
}


if (!empty($context['deviceid']) && !empty($context['stackid']) ){
	$devicelongid = substr($context['deviceid'], 3);
	_obmf_delete('{"stacklist":"'.$context['stackid'].'"}',$devicelongid);
	echo prepare_json_response(ENDED,"Stack is deleted",$context, true);
}else{
	echo prepare_json_response(FAILED, 'Missing parameters', '');
}

?>