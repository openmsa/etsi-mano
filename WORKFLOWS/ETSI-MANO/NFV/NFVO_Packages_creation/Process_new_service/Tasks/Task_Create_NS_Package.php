<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
	create_var_def('nsd_pkg_content', 'String');
	create_var_def('name', 'String');
	create_var_def('vimId', 'String');
	create_var_def('vnfPkgIds.0.id', 'String');
}
// Craft MANO payload.
$vnfs = array();
foreach($context['vnfPkgIds'] as $v) {
	$vnfs[] = $v['id'];
}
$payload = array('CreateNsdInfoRequest' => array(
		'userDefinedData' => array(
			'name' => $context['name'],
			'customerId' => $context['UBIQUBEID'],
			'vimId' => $context['vimId'],
			'vnfPkgIds' => $vnfs,
			'heat' => $context['nsd_pkg_content']?$context['nsd_pkg_content']:null
		)
	)
);
$payJson = json_encode($payload);
$nsdApi = new NsdSol005('http://localhost:8380/ubi-etsi-mano/');
$response= '';
try {
	$response = $nsdApi->nsDescriptorsPost($payJson);
} catch (ManoException $e) {
        task_error($e->getMessage());
}

$context['nsdPkgIds'][] = $response['id'];

task_exit(ENDED, "NSD Package is created successfully. ". $response['id']);

?>
