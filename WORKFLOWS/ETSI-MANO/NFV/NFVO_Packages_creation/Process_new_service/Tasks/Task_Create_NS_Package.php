<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
  create_var_def('nsd_pkg_content', 'String');
}

$response = _nfvo_nsd_package_creation ($context['nsd_pkg_content']);

$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

task_exit(ENDED, "NSD Package is created successfully.");

?>