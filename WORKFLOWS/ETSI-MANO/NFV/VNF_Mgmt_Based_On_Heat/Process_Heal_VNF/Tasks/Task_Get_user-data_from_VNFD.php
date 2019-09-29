<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
}

if (isset($context['heatJson'])) {
	$heatJson = unserialize($context['heatJson']);
	//$heatArray = json_decode($heatJson,true);
	$resources = $heatJson ['resources'];
} else {
	$ret = prepare_json_response(ENDED, "Skipped task: VNFD does not contening resources.", $context, true);
	echo $ret;
}

logToFile(debug_dump($resources,"########### RESOURCES : \n"));

// WARNING: this code is only support an VNFD based-on heat contening 1 server resource type.
foreach ($resources as &$resource) {
	logToFile(debug_dump($resource,"########### RESOURCE TYPE : \n"));
	if ($resource['type'] == "OS::Nova::Server") {
		$context['user_data'] = $resource['properties']['user_data'];
	}
}

$ret = prepare_json_response(ENDED, "VNF user-data was extract successfully.", $context, true);
echo $ret;

?>
