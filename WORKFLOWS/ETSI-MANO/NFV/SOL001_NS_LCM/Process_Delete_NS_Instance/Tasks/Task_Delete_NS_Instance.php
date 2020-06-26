<?php

require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\NsLcmSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args(){}


$nsInstance = $context['nsInstance'];
$nsInstanceId = $nsInstance['id'];

$nsLcm = new NsLcmSol005($context['url']);

try {
	$response = $nsLcm->nsLcmDeleteInstanceOfNs($nsInstanceId);
} catch (ManoException $e) {
        task_error($e->getMessage());
}

task_success("NS Instance (id = $nsInstanceId) is Deleted successfully.");
?>