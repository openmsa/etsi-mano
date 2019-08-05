<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

// List all the parameters required by the task
function list_args()
{
  create_var_def('networks.0.subnet_name', 'String');
  create_var_def('networks.0.subnet_cidr', 'String');
  create_var_def('networks.0.subnet_enable_dhcp', 'Boolean');
  create_var_def('networks.0.subnet_attached_to_router', 'Boolean');

}

$device_id = substr($context['deviceid'], 3);
$tenant_id = $context['tenant_id'];

$index = 0;
foreach ($context['networks'] as $network) {

	$name = $network['subnet_name'];
	$enable_dhcp = $network['subnet_enable_dhcp'];
	$cidr = $network['subnet_cidr'];
	$network_id = $network['network_id'];

	$response = _neutron_subnet_create ($device_id, $name, $network_id, $tenant_id, $cidr, 4, "", $enable_dhcp);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}

	$wo_comment = $response['wo_comment'];
	$wo_comment = json_decode(str_replace("\\\"", "\"", $wo_comment), true);
	$subnet_id = $wo_comment['subnet']['id'];

	//if ($cidr === "192.168.1.0/24" || $cidr === "25.0.0.0/24" || $cidr === "31.0.0.0/24") {
	if ($network['subnet_attached_to_router'] == "true") {
		$response = _neutron_router_interface_update($device_id, $context['router_id'], $subnet_id);
		$response = json_decode($response, true);
		if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}
}

$context['networks'][$index++]['subnet_id'] = $subnet_id;

}

$ret = prepare_json_response(ENDED, "Subnets Created Successfully.", $context, true);
echo "$ret\n";