<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\VnfLcmSol003;


function list_args(){
  create_var_def('aspectId', 'String');
  create_var_def('numberOfSteps', 'Integer');
}

$vnfInstance = $context['vnfInstance'];
$vnfInstanceId = $vnfInstance['id'];

$vnfLcm = new VnfLcmSol003($context['url']);

try{
    $type = 'SCALE_OUT';
    $aspectId = $context['aspectId'];
    $numberOfSteps = intval($context['numberOfSteps']);
    $additionalParamas = new stdClass();
	$body_array = array("type" => $type,
		      "aspectId" => $context['aspectId'], "numberOfSteps" => $numberOfSteps, "additionalParams" => $additionalParamas
		);
	$body = json_encode($body_array);
	logToFile("DEBUG-SLE \n" . $body);
	$response = $vnfLcm->vnfLcmOperateInstanceVnf($vnfInstanceId, $body);
	$location = preg_split("/[\\/]+/", trim($response['location']));
	$vnfLcmOpOccId = $location[7];
} catch (ManoException $e) {
        task_error($e->getMessage());
}
# Set the vnfLcmOpOccsId in the $context
$context['vnfLcmOpOccId'] = $vnfLcmOpOccId;

task_success("VNF instantiate scale out operation is executed successfully with 'vnfLcmOpOccId' = $vnfInstanceId" );
?>