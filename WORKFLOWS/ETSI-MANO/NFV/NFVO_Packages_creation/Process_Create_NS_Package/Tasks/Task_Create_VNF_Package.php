<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\VnfPkgSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
	create_var_def('vnf_pkg_content', 'String');
	create_var_def('vimId', 'String');
	create_var_def('manufacturerId', 'string');
	create_var_def('modelId', 'string');
	create_var_def('device_login', 'string');
	create_var_def('device_password', 'string');
}

// Craft payload
$payload = array('CreateVnfPkgInfoRequest' => array(
	'userDefinedData' => array(
		'customerId' => $context['UBIQUBEID'],
		'vimId' => $context['vimId'],
		'manufacturerId' => $context['manufacturerId'],
		'modelId' => $context['modelId'],
		'device_login' => $context['device_login'],
		'device_password' => $context['device_password'],
		'heat' => $context['vnf_pkg_content']?$context['vnf_pkg_content']:null
	)
));
$vnfPkgApi = new VnfPkgSol005('http://localhost:8380/ubi-etsi-mano/');
try {
	$response = $vnfPkgApi->vnfPackagesPost(json_encode($payload));
	if($context['vnf_pkg_content'] != null) {
		$vnfPkgApi->vnfPackagesVnfPkgIdPackageContentPut($response['VnfPkgInfo']['id'], $context['vnf_pkg_content']);
	}
} catch (ManoException $e) {
	task_error($e->getMessage());
}
$context['vnfPkgIds'][] = $response['VnfPkgInfo']['id'];

task_exit(ENDED, "VNF Package is created successfully. " .$response['VnfPkgInfo']['id']);

?>
