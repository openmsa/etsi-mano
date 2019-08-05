<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

// List all the parameters required by the task
function list_args()
{
  create_var_def('networks.0.network_name', 'String');
  //create_var_def('networks.0.network_type', 'String');
  create_var_def('networks.0.admin_state_up', 'Boolean');
  create_var_def('networks.0.router_external', 'Boolean');
  create_var_def('networks.0.port_security_enabled', 'Boolean');
  //create_var_def('networks.0.shared', 'Boolean');
  //create_var_def('networks.0.segmentation_id', 'String');
  //create_var_def('networks.0.physical_network', 'String');
}

$device_id = substr($context['deviceid'], 3);
$tenant_id = $context['tenant_id'];

$index = 0;
foreach ($context['networks'] as $network) {
	$name = $network['network_name'];
	$admin_state_up = $network['admin_state_up'];
	if (isset($network['router_external']) && !empty($network['router_external'])) {
		$router_external = $network['router_external'];
	} else {
		$router_external = "false";
	}
	$port_security_enabled = $network['port_security_enabled'];
	
	$response = _neutron_network_create($device_id, $name, $tenant_id, $admin_state_up, $router_external, $port_security_enabled);

	//$port_security_enabled, $shared, $network_type, $segmentation_id, $physical_network);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

	$wo_comment = $response['wo_comment'];
	$wo_comment = json_decode(str_replace("\\\"", "\"", $wo_comment), true);
	$network_id = $wo_comment['network']['id'];

	$context['networks'][$index++]['network_id'] = $network_id;
}

$ret = prepare_json_response(ENDED, "Networks Created Successfully.", $context, true);
echo "$ret\n";
