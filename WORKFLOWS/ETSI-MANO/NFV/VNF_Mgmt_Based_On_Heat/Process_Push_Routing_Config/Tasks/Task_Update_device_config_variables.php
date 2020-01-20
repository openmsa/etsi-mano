<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
	create_var_def('left_centos_ip', 'String');
	create_var_def('vsrx_inbound_ip', 'String');
	create_var_def('vsrx_outbound_ip', 'String');
	create_var_def('ims_inbound_ip', 'String');
	create_var_def('ims_outbound_ip', 'String');
	create_var_def('right_centos_ip', 'String');
	create_var_def('location', 'String');
}

$LEFT_CENTOS_IFACE = $context['left_centos_ip'];
$vSRX_LEFT_IFACE = $context['vsrx_inbound_ip'];
$vSRX_RIGHT_IFACE = $context['vsrx_outbound_ip'];
$IMS_LEFT_IFACE = $context['ims_inbound_ip'];
$IMS_RIGHT_IFACE = $context['ims_outbound_ip'];
$RIGHT_CENTOS_IFACE = $context['right_centos_ip'];
$NET_4_SUBNET_IP = "192.168.40.0";
$NET_2_SUBNET_IP = "192.168.20.0";
$template_config_base_path = "Configuration/";
$device_id = substr($context['deviceid'], 3);
$linux_man_id = "14020601";
$juniper_man_id = "18";

foreach ($context['servers'] as $server) {
	$server_id = $server['device_id'];
	$device_id = substr($server_id, 3);

	$cf = $context['image'];
	logToFile("CWW // $cf");

	if ($context['manufacturerId'] == $linux_man_id && strpos($context['image'], "CentOS") !== false) {
		if ($context['location'] == "LEFT") {
			$uri = $template_config_base_path . "LINUX/Generic/Centos_Left/routing";
			$response = _device_configuration_attach_file_to_device($device_id, $uri);
			$response = json_decode($response, true);
			if ($response['wo_status'] !== ENDED) {
				$response = json_encode($response);
				echo $response;
				exit();
			}

			$tab = array();
			$tab[0] = $NET_2_SUBNET_IP;
			$tab[1] = $vSRX_LEFT_IFACE;
			$tab[2] = $IMS_RIGHT_IFACE;
			$index = 0;
			foreach ($tab as &$var) {
				if ($index == 0) {
					$name = "NET_2_SUBNET_IP";
				} else if ($index == 1) {
					$name = "vSRX_LEFT_IFACE";
				} else {
					$name = "IMS_RIGHT_IFACE";
				}
				$response = _configuration_variable_create($device_id, $name, $var);
				$response = json_decode($response, true);
				if ($response['wo_status'] !== ENDED) {
					$response = json_encode($response);
					echo $response;
					exit();
				}
				$index ++;
			}
		} else {
			$uri = $template_config_base_path . "LINUX/Generic/Centos_Right/routing";
			$response = _device_configuration_attach_file_to_device($device_id, $uri);
			$response = json_decode($response, true);
			if ($response['wo_status'] !== ENDED) {
				$response = json_encode($response);
				echo $response;
				exit();
			}

			$tab = array();
			$tab[0] = $NET_4_SUBNET_IP;
			$tab[1] = $IMS_RIGHT_IFACE;
			$index = 0;
			foreach ($tab as &$var) {
				if ($index == 0) {
					$name = "NET_4_SUBNET_IP";
				} else if ($index == 1) {
					$name = "IMS_RIGHT_IFACE";
				}
				$response = _configuration_variable_create($device_id, $name, $var);
				$response = json_decode($response, true);
				if ($response['wo_status'] !== ENDED) {
					$response = json_encode($response);
					echo $response;
					exit();
				}
				$index ++;
			}
		}
	} else if ($context['manufacturerId'] == $linux_man_id && strpos($context['image'], "CWAIO") !== false) {
		logToFile("CWW");
		$uri = $template_config_base_path . "LINUX/Generic/Clearwater/routing";
		$response = _device_configuration_attach_file_to_device($device_id, $uri);
		$response = json_decode($response, true);
		if ($response['wo_status'] !== ENDED) {
			$response = json_encode($response);
			echo $response;
			exit();
		}

		$tab = array();
		$tab[0] = $vSRX_RIGHT_IFACE;
		$tab[1] = $RIGHT_CENTOS_IFACE;
		$tab[2] = $NET_4_SUBNET_IP;
		$index = 0;
		foreach ($tab as &$var) {
			if ($index == 0) {
				$name = "vSRX_RIGHT_IFACE";
			} else if ($index == 1) {
				$name = "RIGHT_CENTOS_IFACE";
			} else {
				$name = "NET_4_SUBNET_IP";
			}
			$response = _configuration_variable_create($device_id, $name, $var);
			$response = json_decode($response, true);
			if ($response['wo_status'] !== ENDED) {
				logToFile("CWW ENDED");
				$response = json_encode($response);
				echo $response;
				exit();
			}
			$index ++;
		}
	} else if ($context['manufacturerId'] == $juniper_man_id) {
		$command1 = "set routing-options static route " . $NET_2_SUBNET_IP . "/24 next-hop " . $IMS_LEFT_IFACE;
		$command2 = "set routing-options static route 10.10.89.3/32 next-hop " . $LEFT_CENTOS_IFACE;
		$configuration = "$command1\n$command2\n";

		$uri = $template_config_base_path . "JUNIPER/junOS_generic/routing";
		$response = _device_configuration_attach_file_to_device($device_id, $uri);
		$response = json_decode($response, true);
		if ($response['wo_status'] !== ENDED) {
			$response = json_encode($response);
			echo $response;
			exit();
		}

		$tab = array();
		$tab[0] = $NET_2_SUBNET_IP;
		$tab[1] = $IMS_LEFT_IFACE;
		$tab[2] = $LEFT_CENTOS_IFACE;
		$index = 0;
		foreach ($tab as &$var) {
			if ($index == 0) {
				$name = "NET_2_SUBNET_IP";
			} else if ($index == 1) {
				$name = "IMS_LEFT_IFACE";
			} else {
				$name = "LEFT_CENTOS_IFACE";
			}
			$response = _configuration_variable_create($device_id, $name, $var);
			$response = json_decode($response, true);
			if ($response['wo_status'] !== ENDED) {
				$response = json_encode($response);
				echo $response;
				exit();
			}
			$index ++;
		}
	}
}
task_exit(ENDED, "Device configurations updated successfully.");

