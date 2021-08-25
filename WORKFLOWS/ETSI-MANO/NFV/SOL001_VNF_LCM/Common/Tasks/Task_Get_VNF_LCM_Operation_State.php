<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";

use Ubiqube\EtsiMano\VnfLcmOpOccsSol003;


function list_args() {}


if (empty($context['vnfLcmOpOccId'])) {
	task_error('vnfLcmOpOccId is empty from $context.');
}
$vnfLcmOpOccId =  $context['vnfLcmOpOccId'];
$vnfLcmOpOccs = new VnfLcmOpOccsSol003($context['url']);

try{
	$vnfLcmOpOccsInfo = $vnfLcmOpOccs->vnfLcmOpOccsCompletionWait($vnfLcmOpOccId);
} catch (ManoException $e) {
        task_error($e->getMessage());
}
if (empty($vnfLcmOpOccsInfo) || $vnfLcmOpOccsInfo == null) {
	task_error('VNF LCM Operation Occurence is empty.');
}

$context['vnfLcmOpOccs'] = $vnfLcmOpOccsInfo;
# $operationState = json_decode($vnfLcmOpOccsInfo['operationState'], 1);
$operationState = json_decode($vnfLcmOpOccsInfo, 1)['operationState'];

if (strpos($operationState , 'FAILED')) {
	task_error("VNF Instance Operation Occurence state is '$operationState'.");
}

sleep(30);

task_success("VNF Instance Operation Occurence state was checked successfully 'operationState' = $operationState.");
?>