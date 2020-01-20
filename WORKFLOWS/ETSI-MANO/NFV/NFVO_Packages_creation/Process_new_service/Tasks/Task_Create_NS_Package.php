<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
	create_var_def('name', 'String');
	create_var_def('vimId', 'String');
	create_var_def('vnfPkgIds.0', 'String');
}
// Craft MANO payload.
$vnfs = array();
foreach ($context['vnfPkgIds'] as $v) {
	$vnfs[] = $v;
}
$payload = array(
	'CreateNsdInfoRequest' => array(
		'userDefinedData' => array(
			'name' => $context['name'],
			'customerId' => $context['UBIQUBEID'],
			'vimId' => $context['vimId'],
			'vnfPkgIds' => $vnfs
		)
	)
);
$payJson = json_encode($payload);
$url = get_url_from_device($context['device_id']);
$nsdApi = new NsdSol005($url);
$response = '';
try {
	$response = $nsdApi->nsDescriptorsPost($payJson);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

$context['nsdPkgIds'][] = $response['id'];
unset($context['nsd_pkg_content']);

task_exit(ENDED, 'NSD Package created successfully. ' . $response['id']);

