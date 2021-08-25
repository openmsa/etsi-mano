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
  create_var_def('deployment_name', 'String');
  create_var_def('container_name', 'String');
  
  
}
$api=$context['kubernetes_endpoint']."api/v1/namespaces/".$context['namespace']."/pods/".$context['container_name']."/status";
$response=create_kubernetes_operation_request("GET", $api, $context['token_id'], "","50", 
"50");
$response = shell_exec($response);
task_exit(ENDED, $response);

?>