<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\VnfLcmSol003;


function list_args(){}


$vnfInstance = $context['vnfInstance'];
$vnfInstanceId = $vnfInstance['id'];

$vnfLcm = new VnfLcmSol003($context['url']);

$content = ' { "gracefulTerminationTimeout": 0, "terminationType": "FORCEFUL", "additionalParams": {} }';

try {
	$response = $vnfLcm->vnfLcmTerminateVnf($vnfInstanceId, $content);
	$location = preg_split("/[\\/]+/", trim($response['location']));
	$vnfLcmOpOccId = $location[7];
} catch (ManoException $e) {
        task_error($e->getMessage());
}
# Set the vnfLcmOpOccsId in the $context
$context['vnfLcmOpOccId'] = $vnfLcmOpOccId;

task_success("VNF Instance (id = $vnfInstanceId) is Terminated successfully.");
?>