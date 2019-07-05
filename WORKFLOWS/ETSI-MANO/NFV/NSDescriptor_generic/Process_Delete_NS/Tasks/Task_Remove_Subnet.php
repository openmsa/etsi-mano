<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

// List all the parameters required by the task
function list_args()
{
}

$device_id = substr($context['deviceid'], 3);

foreach ($context['networks'] as $network) {

//$cidr = $network['subnet_cidr'];
$subnet_id = $network['subnet_id'];

//if ($cidr === "192.168.1.0/24" || $cidr === "25.0.0.0/24" || $cidr === "31.0.0.0/24") {
$response = _neutron_router_interface_update($device_id, $context['router_id'], $subnet_id, "Remove Router Interface");
/**$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}*/
//}

$response = msa_object_delete($device_id, "subnets", $subnet_id);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

}

$ret = prepare_json_response(ENDED, "Subnets Deleted Successfully.", $context, true);
echo "$ret\n";

?>
