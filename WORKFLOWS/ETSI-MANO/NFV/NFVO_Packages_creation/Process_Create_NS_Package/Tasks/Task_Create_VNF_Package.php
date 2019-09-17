<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
  create_var_def('vnf_pkg_content', 'String');
}

$response = _nfvo_vnf_package_creation ($context['vnf_pkg_content']);

$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

task_exit(ENDED, "VNF Package is created successfully.");

?>