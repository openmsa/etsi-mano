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
   create_var_def('app_name', 'String');
   create_var_def('service_lb_name', 'String');
  
}
if(empty($context['load_balancer_service_name'])){
task_exit(ENDED, "No Load Balancer to delete");
}
$api=$context['kubernetes_endpoint']."api/v1/namespaces/".$context['namespace']."/services/".$context['load_balancer_service_name'];
$response=create_kubernetes_operation_request("DELETE", $api, $context['token_id'], "","50", 
"50");
$response = shell_exec($response);
task_exit(ENDED, $response);
?>