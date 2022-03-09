<?php

/**
 * This file is necessary to include to use all the in-built libraries of /opt/fmc_repository/Reference/Common
 */
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/ETSI-MANO/WORKFLOWS/ETSI-MANO/KUBERNETES/utility.php';

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
  create_var_def('load_balancer_template', 'String');
  create_var_def('load_balancer_service_name', 'String');
  create_var_def('ssh_remote_access_ip', 'String');
  create_var_def('ssh_remote_access_port', 'String');
  create_var_def('app_name', 'String');
  create_var_def('service_lb_name', 'String');
  create_var_def('container_port', 'String');
}

preg_match('/(?:[0-9]{1,3}\.){3}[0-9]{1,3}/', $context['kubernetes_endpoint'], $matches);
$context['ssh_remote_access_ip']=$matches[0];

$context['service_lb_name']=$context['deployment_name']."-lb";


$json_lb='
{
  "apiVersion": "v1",
  "kind": "Service",
  "metadata": {
    "name": "'.$context['service_lb_name'].'"
  },
  "spec": {
    "selector": {
      "app": "'.$context['app_name'].'"
    },
    "ports": [
      {
        "port": '.$context['container_port'].',
        "targetPort": '.$context['container_port'].'
      }
    ],
    "type": "LoadBalancer",
    "externalIPs": [
      "'.$context['ssh_remote_access_ip'].'"
    ]
  }
}';


$api=$context['kubernetes_endpoint']."api/v1/namespaces/".$context['namespace']."/services";
//$response=kubernetes_pod_deployment ("POST", $api, $context['token_id'], $context['load_balancer_template'],"50", "50");
$response=create_kubernetes_operation_request ("POST", $api, $context['token_id'], $json_lb,"50", "50");
$response = shell_exec($response);
$json = json_decode($response, true);
preg_match('/\"metadata\": {\s+\"name\": \"(.*)\",\s+\"namespace\":/', $response, $matches);

if(empty($matches[1])){
task_exit(ENDED, $response);
}
$context['load_balancer_service_name']=$matches[1];

preg_match('/\"nodePort\": (.*)/', $response, $matches);
$context['ssh_remote_access_port']=$matches[1];



task_exit(ENDED, $response);

?>