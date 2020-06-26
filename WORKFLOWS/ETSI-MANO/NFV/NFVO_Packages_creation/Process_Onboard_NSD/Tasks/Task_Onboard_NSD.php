<?php
require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

/**
 * List all the parameters required by the task
 */
function list_args()
{
	create_var_def('nsdPkgId', 'String');
	create_var_def('nsd_pkg_content', 'File');
}
$vnf_pkg_content = '/opt/fmc_repository/' . $context['nsd_pkg_content'];

$url = get_url_from_device($context['device_id']);
$nsdApi = new NsdSol005($url);
try {
	$nsdApi->nsDescriptorsNsdInfoIdNsdFilePut($context['nsdPkgId'], $context['nsd_pkg_content']);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

unset($context['nsd_pkg_content']);

task_exit(ENDED, 'NSD #' . $context['nsdPkgId'] . ' sucessfully onboarded.');
