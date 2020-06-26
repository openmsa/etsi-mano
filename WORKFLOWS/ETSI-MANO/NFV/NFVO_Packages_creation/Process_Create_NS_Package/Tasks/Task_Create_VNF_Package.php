<?php
require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

use Ubiqube\EtsiMano\VnfPkgSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args(){
  create_var_def("vnf_package_name", "String");
}

$url = get_url_from_device($context['device_id']);
$vnfPkgApi = new VnfPkgSol005($url);

try {
	// { "userDefinedData": { "name": "Test TOSCA VNF" } }'
	$body_array = array('userDefinedData' => array("name" => $context['name']));
	$body = json_encode($body_array);
	$response = $vnfPkgApi->vnfPackagesPost($body);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

task_exit(ENDED, 'VNF Package ' . $response['id'] . ' successfully Created.');
