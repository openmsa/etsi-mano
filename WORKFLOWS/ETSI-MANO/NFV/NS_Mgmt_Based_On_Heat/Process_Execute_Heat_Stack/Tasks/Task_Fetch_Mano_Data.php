<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\NsdSol005;
use Ubiqube\EtsiMano\ManoException;

use Symfony\Component\Yaml\Yaml;

function list_args()
{
	create_var_def('nfvoDevice', 'Device');
        create_var_def('nsPkgId', 'String');
        create_var_def('vnfPkgs.0.vnfPkgId', 'String');
}
check_mandatory_param('nsPkgId');

$nsPkgManagement = new NsdSol005('http://localhost:8380/ubi-etsi-mano/');
try {
	$nsPkg = $nsPkgManagement->nsDescriptorsNsdInfoIdGet($context['nsPkgId']);
} catch (ManoException $e) {
	task_error($e->getMessage());
}
$heatJson = $nsPkg['userDefinedData']['heat'];
$heatYaml = Yaml::dump($heatJson);

$path = '/opt/ses/share/htdocs/tech_report/ns_packages/heat/' . $context['nsPkgId'];
@mkdir($path);
file_put_contents($path . '/nsd.yaml', $heatYaml);

$context['deviceid'] = $nsPkg['userDefinedData']['vimId'];
$context['stackname'] = key($heatJson['resources']);
// TODO get this IP on a NFVO
$context['template_url'] = 'http://10.31.1.246/tech_report/ns_packages/heat/' . $context['nsPkgId'].'/nsd.yaml';


$i=0;
foreach ($nsPkg['vnfPkgIds'] as $vnfPkgId) {
	$context['vnfPkgs'][$i++]['vnfPkgId']=$vnfPkgId;
}


task_exit(ENDED, "Task OK");
