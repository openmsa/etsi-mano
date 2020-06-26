<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\VnfLcmSol003;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
  create_var_def('nfvo_device', 'Device');
  create_var_def('vnfPkgId', 'OBMFRef');
  create_var_def('vnfInstanceName', 'String');
  create_var_def('vnfInstanceDescription', 'String');
  create_var_def('deviceManufaturer', 'String');
  create_var_def('deviceModel', 'String');
}

check_mandatory_param('vnfPkgId');
check_mandatory_param('nfvo_device');

$vnfPkgId= $context['vnfPkgId'];
$url = get_url_from_device($context['nfvo_device']);

//$url = "http://localhost:8380/ubi-etsi-mano/";
$vnfLcm = new VnfLcmSol003($url);

try {
	$metadata = array();
	$metadata['deviceManufaturer'] = $context['deviceManufaturer'];
	$metadata['deviceModel'] = $context['deviceModel'];

	$body = json_encode(array(
				"vnfdId" => $vnfPkgId,
				"vnfInstanceName" => "",
  				"vnfInstanceDescription" => "",
  				"metadata" => $metadata
			));
        $vnfInstance = $vnfLcm->vnfLcmCreateInstance($body);
	logToFile(debug_dump($vnfInstance, "\n"));
} catch (ManoException $e) {
        task_error($e->getMessage());
}

# Set the vnfInstance resource in the $context
$context['vnfInstance'] = $vnfInstance;

# Set the NFVO URL in to $context
$context['url'] = $url;

//$vnfInstanceArray = json_decode($vnfInstance, true);
$vnfInstanceId = $vnfInstance['id'];

task_success("VNF Instance is created with id = $vnfInstanceId");
?>