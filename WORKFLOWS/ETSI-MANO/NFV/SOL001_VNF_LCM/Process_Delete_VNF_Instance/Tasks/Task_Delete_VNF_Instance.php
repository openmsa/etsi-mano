<?php

require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\VnfLcmSol003;
use Ubiqube\EtsiMano\ManoException;

function list_args(){}


$vnfInstance = $context['vnfInstance'];
$vnfInstanceId = $vnfInstance['id'];

$vnfLcm = new VnfLcmSol003($context['url']);

try {
	$response = $vnfLcm->vnfLcmDeleteInstanceOfVnf($vnfInstanceId);
} catch (ManoException $e) {
        task_error($e->getMessage());
}

task_success("VNF Instance (id = $vnfInstanceId) is Deleted successfully.");
?>




