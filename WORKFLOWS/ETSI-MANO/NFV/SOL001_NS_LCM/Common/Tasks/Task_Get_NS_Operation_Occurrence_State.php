<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";

use Ubiqube\EtsiMano\NsLcmOpOccsSol005;


function list_args() {}


if (empty($context['nsLcmOpOccId'])) {
	task_error('nsLcmOpOccId is empty from $context.');
}
$nsLcmOpOccId =  $context['nsLcmOpOccId'];
$nsLcmOpOccs = new NsLcmOpOccsSol005($context['url']);

try{
	$nsLcmOpOccsInfo = $nsLcmOpOccs->nsLcmOpOccsCompletionWait($nsLcmOpOccId, 60);
} catch (ManoException $e) {
        task_error($e->getMessage());
}
if (empty($nsLcmOpOccsInfo) || $nsLcmOpOccsInfo == null) {
	task_error('NS LCM Operation Occurence is empty.');
}

$context['nsLcmOpOccs'] = $nsLcmOpOccsInfo;
$operationState = json_decode($nsLcmOpOccsInfo, 1)['operationState'];

if (strpos($operationState , 'FAILED')) {
	task_error("NS Instance Operation Occurence state is '$operationState'.");
}

task_success("NS Instance Operation Occurence state was checked successfully 'operationState' = $operationState.");
?>