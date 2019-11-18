<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\VnfPkgSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
  create_var_def('vnf_model', 'String');
}
check_mandatory_param('vnf_model');
$vnf = $context['vnf_model'];
$base = '/opt/fmc_repository/Datafiles/templates/';

if($vnf == "cw-aio") {
	$content = file_get_contents($base . 'cw-aio-vnf-pkg.json');
} else if ($vnf == "juniper") {
	$content = file_get_contents($base . 'juniper-vsrx-vnf-pkg.json');
} else if ($vnf == "cirros") {
	$content = file_get_contents($base . 'cirros-vnf-pkg.json');
} else {
	$content = file_get_contents($base . 'cirros-vnf-pkg.json');
}

$url = get_url_from_device($context['device_id']);
$vnfPkgApi = new VnfPkgSol005($url);
try {
	$response = $vnfPkgApi->vnfPackagesPost($content);
} catch (ManoException $e) {
        task_error($e->getMessage());
}

unset($context['vnf_model']);
unset($context['nsd_model']);
// logToFile(debug_dump($response, "MSA CONTEXT:\n"));
task_exit(ENDED, 'VNF ' . $response['VnfPkgInfo']['id'] . ' successfully onboarded.');
?>
