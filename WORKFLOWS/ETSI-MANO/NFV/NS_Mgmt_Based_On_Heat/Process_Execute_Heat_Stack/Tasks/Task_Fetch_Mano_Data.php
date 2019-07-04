<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\NsdSol005;

use Symfony\Component\Yaml\Yaml;

function list_args()
{
        create_var_def('nsPkgId', 'String');
}
check_mandatory_param('nsPkgId');

$nsPkgManagement = new NsdSol005('http://localhost:8380/ubi-etsi-mano-0.0.1-SNAPSHOT/');
$nsPkg = $nsPkgManagement->nsDescriptorsNsdInfoIdGet($context['nsPkgId']);
$heatJson = $nsPkg['userDefinedData']['heat'];
$heatYaml = Yaml::dump($heatJson);

$path = '/opt/ses/share/htdocs/tech_report/ns_packages/heat/' . $context['nsPkgId'];
@mkdir($path);
file_put_contents($path . '/nsd.yaml', $heatYaml);

$context['deviceid'] = $nsPkg['userDefinedData']['vimId'];
$context['stackname'] = key($heatJson['resources']);
// TODO get this IP on a NFVO
$context['template_url'] = 'http://10.30.18.103/tech_report/ns_packages/heat/' . $context['nsPkgId'].'/nsd.yaml';

task_exit(ENDED, "Task OK");
