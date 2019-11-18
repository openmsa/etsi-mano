<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";

use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
  create_var_def('nsd_model', 'String');
}
check_mandatory_param('nsd_model');

$nsd = $context['nsd_model'];
if($nsd == '3_net') {
	$content = file_get_contents('/opt/fmc_repository/Datafiles/templates/nsd-pkg.json');
} else {
	$content = file_get_contents('/opt/fmc_repository/Datafiles/templates/nsd-ovi.json');
}

$url = get_url_from_device($context['device_id']);
$nsdApi = new NsdSol005($url);

try {
	$response = $nsdApi->nsDescriptorsPost($content);
} catch (ManoException $e) {
        task_error($e->getMessage());
}

unset($context['vnf_model']);
unset($context['nsd_model']);
logToFile(debug_dump($response, "MSA CONTEXT:\n"));
task_exit(ENDED, 'NSD ' . $response['id'] . ' sucessfully onboarded.');
?>
