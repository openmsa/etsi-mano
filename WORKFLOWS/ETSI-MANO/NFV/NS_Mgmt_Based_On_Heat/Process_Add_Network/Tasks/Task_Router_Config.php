<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

// List all the parameters required by the task
function list_args()
{
  create_var_def('router_name', 'String');
  create_var_def('router_admin_state_up', 'Boolean');
  create_var_def('external_gateway_network', 'OBMFRef');
  create_var_def('networks.0.network_name', 'String');
  //create_var_def('networks.0.network_type', 'String');
  create_var_def('networks.0.admin_state_up', 'Boolean');
  create_var_def('networks.0.router_external', 'Boolean');
  create_var_def('networks.0.port_security_enabled', 'Boolean');
}

$device_id = substr($context['deviceid'], 3);

$name = $context['router_name'];
$admin_state_up = $context['router_admin_state_up'];
$external_network = $context['external_gateway_network'];

$response = _neutron_router_create ($device_id, $name, $admin_state_up, $external_network);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

$wo_comment = $response['wo_comment'];
$wo_comment = json_decode(str_replace("\\\"", "\"", $wo_comment), true);
$router_id = $wo_comment['router']['id'];

$context['router_id'] = $router_id;
sleep(10);
$ret = prepare_json_response(ENDED, "Router Created Successfully.", $context, true);
echo "$ret\n";
