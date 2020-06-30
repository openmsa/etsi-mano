<?php

/**
 * This file is necessary to include to use all the in-built libraries of /opt/fmc_repository/Reference/Common
 */
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

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
    create_var_def('docker_reg_url', 'String');
    create_var_def('docker_reg_username', 'String');
    create_var_def('docker_reg_password', 'String');
    create_var_def('docker_reg_email', 'String');
    create_var_def('docker_reg_config', 'String');
}
$auth=base64_encode($context['docker_reg_username'].':'.$context['docker_reg_password']);
//$auth=$context['docker_reg_username'].':'.$context['docker_reg_password'];

$config_json='
{
  "auths": {
    "'.$context['docker_reg_url'].'": {
      "username": "'.$context['docker_reg_username'].'",
      "password": "'.$context['docker_reg_password'].'",
      "email": "'.$context['docker_reg_email'].'",
      "auth": "'.$auth.'"
    }
  }
}';

$context['docker_reg_config']=$config_json;
task_exit(ENDED, $context['docker_reg_config']);

?>