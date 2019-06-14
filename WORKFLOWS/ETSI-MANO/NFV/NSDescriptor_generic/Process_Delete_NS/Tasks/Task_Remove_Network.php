<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

// List all the parameters required by the task
function list_args()
{
}

$device_id = substr($context['deviceid'], 3);

foreach ($context['networks'] as $network) {

$network_id = $network['network_id'];

$response = msa_object_delete($device_id, "networks", $network_id);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

}

$ret = prepare_json_response(ENDED, "Networks Deleted Successfully.", $context, true);
echo "$ret\n";

?>