<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\NsLcmSol005;
use Ubiqube\EtsiMano\ManoException;

function list_args()
{
  create_var_def('nfvo_device', 'Device');
  create_var_def('nsPkgId', 'String');
}

check_mandatory_param('nsPkgId');
check_mandatory_param('nfvo_device');

$nsPkgId= $context['nsPkgId'];
$url = get_url_from_device($context['nfvo_device']);

$nsLcm = new nsLcmSol005($url);

try {
	$body = json_encode(array("nsdId" => $nsPkgId));
        $nsInstance = $nsLcm->nsLcmCreateInstance($body);
	logToFile(debug_dump($nsInstance , "\n"));
} catch (ManoException $e) {
        task_error($e->getMessage());
}

# Set the nsInstance resource in the $context
$context['nsInstance'] = $nsInstance ;

# Set the NFVO URL in to $context
$context['url'] = $url;

$nsInstance = $nsInstance['id'];

task_success("NS Instance is created with id = $nsInstance ");
?>