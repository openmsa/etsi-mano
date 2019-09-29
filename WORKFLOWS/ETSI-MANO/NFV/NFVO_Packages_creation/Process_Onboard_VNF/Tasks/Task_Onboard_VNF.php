<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\VnfPkgSol005;
use Ubiqube\EtsiMano\ManoException;

/**
 * List all the parameters required by the task
 */
function list_args()
{
	create_var_def('vnfPkgId', 'String');
	create_var_def('vnf_pkg_content', 'String');
}
$url = get_url_from_device($context['device_id']);
$vnfPkgApi = new VnfPkgSol005($url);
try {
	$vnfPkgApi->vnfPackagesVnfPkgIdPackageContentPut($context['vnfPkgId'], $context['vnf_pkg_content']);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

task_exit(ENDED, 'VNF #' . $context['vnfPkgId'] . ' successfully onboarded.');

