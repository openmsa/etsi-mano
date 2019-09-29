<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/OBMF/openstack_common_obmf.php';

function list_args()
{
  create_var_def('servers.0.public_network', 'OBMFRef');
}

$device_id = substr($context['deviceid'], 3);

// Create floating IP for each of server in servers list
if (isset($context['servers'])) {
        foreach ($context['servers'] as &$server) {


	    if(!isset($server['floating_ip_address']))
            {
          
		$response = allocate_floatingip_address($device_id, $server['public_network'], $context['tenant_id']);

		//$response = _neutron_floatingip_create ($device_id, $server['public_network'], $context['tenant_id']);
		$response = json_decode($response, true);
		if ($response['wo_status'] !== ENDED) {
			$response = json_encode($response);
			echo "Floating IP creation FAILED:" . $response . "\n";
			exit;
		}
		$wo_comment = $response['wo_newparams'];
	
		$floating_ip_id = $wo_comment['floating_ip_id'];
		
                $floating_ip_address = $wo_comment['floating_ip_address'];


                $server['floating_ip_id'] = $floating_ip_id;
                $server['floating_ip_address'] = $floating_ip_address;

//logToFile("******FURTHER $floating_ip_id");
		// Associate floating IP to server
		$response = _nova_floating_ip_associate ($device_id, $server['server_id'], $floating_ip_address);
		$response = json_decode($response, true);
		if ($response['wo_status'] !== ENDED) {
			$response = json_encode($response);
			echo "Floating ip association failure \n".$response;
		exit;
		}
                              }

	}
}

task_exit(ENDED, "Floating IP addresses created  and associated with servers successfully. \n");

?>

