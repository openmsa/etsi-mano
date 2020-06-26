<?php
require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
	create_var_def('nsdPkgId', 'string');
}
$url = get_url_from_device($context['device_id']);
$nsdApi = new NsdSol005($url);
try {
	$nsdApi->nsDescriptorsNsdInfoIdDelete($context['nsdPkgId']);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

unset($context['nsdPkgId']);
task_success("NSD Suppressed.");
?>
