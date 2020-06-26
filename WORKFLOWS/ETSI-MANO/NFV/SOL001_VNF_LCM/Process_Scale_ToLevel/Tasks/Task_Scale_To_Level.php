<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\VnfLcmSol003;


function list_args(){
  create_var_def('aspectId', 'String');
  create_var_def('scaleLevel', 'Integer');
}

check_mandatory_param('aspectId');
check_mandatory_param('scaleLevel');

$vnfInstance = $context['vnfInstance'];
$vnfInstanceId = $vnfInstance['id'];

$vnfLcm = new VnfLcmSol003($context['url']);

try{
	$scaleInfo = array();
	$scaleInfo[] = array("aspectId" => $context['aspectId'],
		      "scaleLevel" => intval($context['scaleLevel'])
		);
	$body_array = array("instantiationLevelId" => "demo",
		      "scaleInfo" => $scaleInfo
		);
	$body = json_encode($body_array);
	logToFile("DEBUG-SLE \n" . $body);
	$response = $vnfLcm->vnfLcmScaleToLevelInstanceVnf($vnfInstanceId, $body);
	$location = preg_split("/[\\/]+/", trim($response['location']));
	$vnfLcmOpOccId = $location[7];
} catch (ManoException $e) {
        task_error($e->getMessage());
}
# Set the vnfLcmOpOccsId in the $context
$context['vnfLcmOpOccId'] = $vnfLcmOpOccId;

task_success("VNF instantiate scale to level operation is executed successfully with 'vnfLcmOpOccId' = $vnfInstanceId" );
?>