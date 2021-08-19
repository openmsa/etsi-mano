<?php

/**
 * This file is necessary to include to use all the in-built libraries of /opt/fmc_repository/Reference/Common
 */
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/ETSI-MANO/KUBERNETES/utility.php';

/**
 * List all the parameters required by the task
 */
function list_args()
{
  /**
   * You can use var_name convention for your variables
   * They will display automaticaly as "Var Name"
   * The allowed types are:
   *    'String', 'Boolean', 'Integer', 'Password', 'IpAddress',
   *    'IpMask', 'Ipv6Address', 'Composite', 'OBMFRef', 'Device'
   *
   * Add as many variables as needed
   */
  create_var_def('file', 'File');
  create_var_def('deployment_name', 'String');
  create_var_def('namespace', 'String');
  create_var_def('app_name', 'String');
  
}
$context['status in msa']="Device is not created";
$api=$context['kubernetes_endpoint']."apis/apps/v1/namespaces/".$context['namespace']."/deployments";
$response=kubernetes_pod_deployment ("POST", $api, $context['token_id'], $context['file'],"50", 
"50");
$response = shell_exec($response);
//$json = json_decode($response, true);
preg_match('/\"metadata\": {\s+\"name\": \"(.*)\",\s+\"namespace\":/', $response, $matches);
if(empty($matches[1])){
task_exit(ENDED, $response);
}
$context['deployment_name']=$matches[1];

preg_match('/\"labels\": {\s+\"app\": \"(.*)\"\s+}/', $response, $matches);
$context['app_name']=$matches[1];

task_exit(ENDED, $response);

?>