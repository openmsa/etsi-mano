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
  create_var_def('file', 'String');
  create_var_def('namespace', 'String');
  create_var_def('resource', 'String');
  
}
$context['status in msa']="Device is not created";

if ($context['resource'] == 'services') {
    $api=$context['kubernetes_endpoint']."api/v1/namespaces/".$context['namespace']."/".$context['resource'];
} elseif ($context['resource'] == 'deployments') {
    $api=$context['kubernetes_endpoint']."apis/apps/v1/namespaces/".$context['namespace']."/".$context['resource'];
} elseif ($context['resource'] == 'namespaces') {
    $api=$context['kubernetes_endpoint']."api/v1/".$context['resource'];
} else {
    // default api
    $api=$context['kubernetes_endpoint']."api/v1/namespaces/".$context['namespace']."/".$context['resource'];
}

$response=kubernetes_apply_yaml ("POST", $api, $context['token_id'], $context['file'],"50", "50");

$response = shell_exec($response);

preg_match('/HTTP_CODE=(?<code>.*)/', $response, $matches);

if ($matches['code'] == '201') {
  $context['deploy_response'] = $response;
  task_exit(ENDED, $response);
}

task_exit(FAILED, $response);
?>