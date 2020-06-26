<?php
require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

use Ubiqube\EtsiMano\VnfPkgSol005;
use Ubiqube\EtsiMano\ManoException;

/**
 * List all the parameters required by the task
 */
function list_args()
{
	create_var_def('vnfPkgId', 'String');
	create_var_def('vnf_pkg_content', 'File');
}
$vnf_pkg_content = $context['vnf_pkg_content'];
$url = get_url_from_device($context['device_id']);
$vnfPkgApi = new VnfPkgSol005($url);
logToFile("DEV-DEBUG \n" . $vnf_pkg_content . "\n");

try {
	$vnfPkgApi->vnfPackagesVnfPkgIdPackageFilePut($context['vnfPkgId'], $vnf_pkg_content);
} catch (ManoException $e) {
	task_error($e->getMessage());
}
unset($context['vnf_pkg_content']);
task_exit(ENDED, 'VNF #' . $context['vnfPkgId'] . ' successfully onboarded.');

