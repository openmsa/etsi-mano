<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\NsLcmSol005;


function list_args(){}


$nsInstance = $context['nsInstance'];
$nsInstanceId = $nsInstance['id'];

$nsLcm = new NsLcmSol005($context['url']);

try {
	$response = $nsLcm->nsLcmTerminateNs($nsInstanceId);
	$location = preg_split("/[\\/]+/", trim($response['location']));
	$nsLcmOpOccId = $location[7];
} catch (ManoException $e) {
        task_error($e->getMessage());
}
# Set the nsLcmOpOccsId in the $context
$context['nsLcmOpOccId'] = $nsLcmOpOccId;

task_success("NS Instance (id = $nsInstanceId) is Terminated successfully.");
?>