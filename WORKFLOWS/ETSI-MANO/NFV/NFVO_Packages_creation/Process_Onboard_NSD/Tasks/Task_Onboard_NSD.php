<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";

use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

/**
 * List all the parameters required by the task
 */
function list_args()
{
	create_var_def('nsdPkgId', 'String');
	create_var_def('nsd_pkg_content', 'String');
}
$url = get_url_from_device($context['device_id']);
$nsdApi = new NsdSol005($url);
try {
	$nsdApi->nsDescriptorsNsdInfoIdNsdContentPut($context['nsdPkgId'], $context['nsd_pkg_content']);
} catch (ManoException $e) {
	task_error($e->getMessage());
}
	
task_exit(ENDED, 'NSD #' . $context['nsdPkgId'] . ' sucessfully onboarded.');

?>
