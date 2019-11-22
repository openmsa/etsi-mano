<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{

}

$device_id = substr($context['deviceid'], 3);
$server_id = $context['server_id'];


$response = _neutron_get_pubkic_networks($context['endpoints'][NEUTRON][PUBLIC_URL], $context['token_id']);
$resp = json_decode($response, 1);

//$network = $resp['wo_newparams']['networks'][0]['id'];
$network = $context['floating_network_id'];
// Create floating IP
$response = _neutron_floatingip_create ($context['endpoints'][NEUTRON][PUBLIC_URL], $context['token_id'], $network, $context['tenant_id']);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo "Floating IP creation FAILED:" . $response . "\n";
	exit;
}

logToFile(debug_dump($response, "DONE ******************"));
//$wo_comment = $response['wo_comment'];
//$wo_comment = json_decode(str_replace("\\\"", "\"", $wo_comment), true);
//$floating_ip_id = $wo_comment['floatingip']['id'];
//$floating_ip_address = $wo_comment['floatingip']['floating_ip_address'];
$floating_ip_address = $response['wo_newparams']['floatingip']['floating_ip_address'];
$floating_ip_id = $response['wo_newparams']['floatingip']['id'];

if (isset($context['servers_scaled'])) {
        foreach ($context['servers_scaled'] as &$server) {
		logToFile(debug_dump($server), "**************** DONE ******************");
                if (isset($server['server_id']) && $server['server_id'] == $server_id) {
                        $server['floating_ip_id'] = $floating_ip_id;
                        $server['floating_ip_address'] = $floating_ip_address;
			logToFile("**************** DONE ******************");
                }
        }
}
$context['servers_scaled'][$server_id]['floating_ip_id'] = $floating_ip_id;
$context['servers_scaled'][$server_id]['floating_ip_address'] = $floating_ip_address;
logToFile(debug_dump($context['servers_scaled'], "RESPONSE ============++++++++++++>: $server_id\n"));
task_exit(ENDED, "Floating IP address created successfully: " . $floating_ip_address . "\n");

?>
