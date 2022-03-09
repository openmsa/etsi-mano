<?php

/**
 * This file is necessary to include to use all the in-built libraries of /opt/fmc_repository/Reference/Common
 */
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/ETSI-MANO/WORKFLOWS/ETSI-MANO/KUBERNETES/utility.php';

/**
 * List all the parameters required by the task
 */
function list_args(){}

$response = $context['deploy_response'];

//$json = json_decode($response, true);
preg_match('/\\"name\\":\s+\\"(.*)\\",\\n\s+\\"namespace\\"/', $response, $matches);
$deployment_name = $matches[1];
$context['deployment_name']=$deployment_name;

if (empty($deployment_name)) {
	task_exit(FAILED, "Failed to parse deployment from CNF deploy response.");
}

task_exit(ENDED, "Deployment:" . $deployment_name . '.');

?>