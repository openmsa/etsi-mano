<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

function list_args()
{
}

$device_id = substr($context['deviceid'], 3);
$server_id = $context['server_id'];
if (isset($context['servers_scaled'])) {
        foreach ($context['servers_scaled'] as &$server) {
                if (isset($server['server_id']) && $server['server_id'] == $server_id) {
                        $floating_ip_address = $server['floating_ip_address'];
               		//$fixed_address =  $server['fixed_address'];
		}
        }
}

// Associate floating IP to server
$response = _nova_floating_ip_associate ($device_id, $server_id, $floating_ip_address);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

task_exit(ENDED, "Floating IP addess $floating_ip_address associated with instance $server_id successfully \n");

?>

