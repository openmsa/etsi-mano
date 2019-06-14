<?php

/**
 * This file is necessary to include to use all the in-built libraries of /opt/fmc_repository/Reference/Common
 */
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require '/opt/vendor/autoload.php';

use Aws\Ec2\Ec2Client;

/**
 * List all the parameters required by the task
 */
function list_args()
{
  create_var_def('vimTenant', 'Device');
  create_var_def('stackname', 'String');
  create_var_def('director_image_name', 'OBMFRef');
  create_var_def('director_flavor_name', 'OBMFRef');
  create_var_def('inspector_image_name', 'OBMFRef');
  create_var_def('inspector_flavor_name', 'OBMFRef');
  create_var_def('public_net_id', 'OBMFRef');
  create_var_def('net_sfc_id', 'OBMFRef');
  create_var_def('net_mgmt_id', 'String');
  create_var_def('net_mgmt_subnet_id', 'String');
  create_var_def('net_mgmt_cidr', 'String');
  create_var_def('director_ip', 'String');
  create_var_def('dns_nameservers', 'String');
  create_var_def('activate_code', 'String');
  create_var_def('template_url', 'String');
  create_var_def('stackid', 'String');
  create_var_def('cluster_size', 'String');
}

check_mandatory_param("vimTenant");
check_mandatory_param("director_image_name");
check_mandatory_param("director_flavor_name");
check_mandatory_param("inspector_image_name");
check_mandatory_param("inspector_flavor_name");
check_mandatory_param("public_net_id");
check_mandatory_param("net_sfc_id");

task_exit(ENDED, "Instantiate VNF is successfull.");

?>
