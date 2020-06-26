<?php
require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args(){}

$url = get_url_from_device($context['device_id']);
$nsdApi = new NsdSol005($url);

try {
	//$userDefinedData = json_encode(array(), new stdClass());
	$body = json_encode(array("userDefinedData" => new stdClass()));
	$response = $nsdApi->nsDescriptorsPost($body);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

unset($context['vnf_model']);
unset($context['nsd_model']);
logToFile(debug_dump($response, "MSA CONTEXT:\n"));
task_exit(ENDED, 'NS Package ' . $response['id'] . ' sucessfully created.');
