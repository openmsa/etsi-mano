<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\NsLcmSol005;


function list_args(){}

// TODO: Check the NS Operation State (if = INSTANTIATED or NOT_INSTANTIATED)

$nsInstance = $context['nsInstance'];
$nsInstanceId = $nsInstance['id'];

$nsLcm = new NsLcmSol005($context['url']);

try{
	$response = $nsLcm->nsLcmInstantiateNs($nsInstanceId);
	$location = preg_split("/[\\/]+/", trim($response['location']));
	$nsLcmOpOccId = $location[7];
} catch (ManoException $e) {
        task_error($e->getMessage());
}
# Set the nsLcmOpOccsId in the $context
$context['nsLcmOpOccId'] = $nsLcmOpOccId;

task_success("NS instantiate operation is executed successfully with 'nsLcmOpOccId' = $nsInstanceId" );
?>