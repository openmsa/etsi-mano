<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

function list_args()
{

}

$device_id = substr($context['deviceid'], 3);
$server_id = $context['server_id'];

// Create floating IP
$response = _neutron_floatingip_create ($device_id, $context['public_network'], $context['tenant_id']);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo "Floating IP creation FAILED:" . $response . "\n";
	exit;
}

$wo_comment = $response['wo_comment'];
$wo_comment = json_decode(str_replace("\\\"", "\"", $wo_comment), true);
$floating_ip_id = $wo_comment['floatingip']['id'];
$floating_ip_address = $wo_comment['floatingip']['floating_ip_address'];

if (isset($context['servers_scaled'])) {
        foreach ($context['servers_scaled'] as &$server) {
                if (isset($server['server_id']) && $server['server_id'] == $server_id) {
                        $server['floating_ip_id'] = $floating_ip_id;
                        $server['floating_ip_address'] = $floating_ip_address;
                }
        }
}


task_exit(ENDED, "Floating IP address created successfully: " . $floating_ip_address . "\n");

?>
