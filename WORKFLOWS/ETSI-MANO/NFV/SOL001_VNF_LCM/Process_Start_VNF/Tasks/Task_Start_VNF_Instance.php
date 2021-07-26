<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\VnfLcmSol003;


function list_args(){

}

$vnfInstance = $context['vnfInstance'];
$vnfInstanceId = $vnfInstance['id'];

$vnfLcm = new VnfLcmSol003($context['url']);

try{
    $changeStateTo = 'STOPPED';
    $stopType = 'FORCEFUL';
    $gracefulStopTimeout = 0;
    $additionalParamas = new stdClass();
	$body_array = array("changeStateTo" => $changeStateTo,
		      "stopType" => $stopType, "gracefulStopTimeout" => 0, "additionalParams" => $additionalParamas
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

task_success("VNF instantiate start operation is executed successfully with 'vnfLcmOpOccId' = $vnfInstanceId" );
?>