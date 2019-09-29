<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";

use Ubiqube\EtsiMano\VnfPkgSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
	create_var_def('vnfPkgId', 'string');
}
$id = $context['vnfPkgId'];
$url = get_url_from_device($context['device_id']);
$vnfPkgApi = new VnfPkgSol005($url); 
try {
	$vnfPkgApi->vnfPackagesVnfPkgIdDelete($id);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

unset($context['vnfPkgId']);

task_exit(ENDED, "VNF Package $id deleted.");

?>
