<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php';
# require_once '/opt/devops/OpenMSA_Adapters/vendor/autoload.php';

/**
 * 
 */
function get_url_from_device($device_id) {
	global $context;
	$device_id = substr($device_id, 3);
	$res = _device_get_management_ip_address($device_id);
	$address = $res['address'];
	
	$response = _configuration_variable_list ($device_id);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
		$response = json_encode($response);
		echo $response;
		exit;
	}
	if (isset($response['wo_newparams']) && !empty($response['wo_newparams'])) {
		foreach ($response['wo_newparams'] as &$conf_variable) {
			if ($conf_variable['name'] == "HTTP_PORT") {
				$port =  $conf_variable['value'];
			} 
			if ($conf_variable['name'] == "BASE_URL") {
				$base = $conf_variable['value'];
			}
		}
	}
	$ret = 'http://' . $address;
	if(!empty($port) && 80 != $port) {
		$ret .= ':' . $port;
	}
	$ret .= $base;
	return $ret;
}

function gen_uuid()
{
    return sprintf('%04x%04x-%04x-%04x-%04x-%04x%04x%04x',
        mt_rand(0, 0xffff), mt_rand(0, 0xffff),
        mt_rand(0, 0xffff),
        mt_rand(0, 0x0fff) | 0x4000,
        mt_rand(0, 0x3fff) | 0x8000,
        mt_rand(0, 0xffff), mt_rand(0, 0xffff), mt_rand(0, 0xffff)
    );
}
