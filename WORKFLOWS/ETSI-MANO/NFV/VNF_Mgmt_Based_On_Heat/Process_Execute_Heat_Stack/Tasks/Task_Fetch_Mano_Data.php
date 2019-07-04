<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\VnfPkgSol003;

use Symfony\Component\Yaml\Yaml;

function list_args()
{
        create_var_def('vnfPkgId', 'String');
}
check_mandatory_param('vnfPkgId');

$vnfPkgManagement = new VnfPkgSol003('http://localhost:8380/ubi-etsi-mano-0.0.1-SNAPSHOT/');
$vnfPkg = $vnfPkgManagement->vnfPackagesVnfPkgIdGet($context['vnfPkgId']);
$heatJson = $vnfPkg['userDefinedData']['heat'];
$heatYaml = Yaml::dump($heatJson);

$path = '/opt/ses/share/htdocs/tech_report/vnf_packages/heat/' . $context['vnfPkgId'];
@mkdir($path);
file_put_contents($path . '/vnfd.yaml', $heatYaml);

$context['deviceid'] = $vnfPkg['userDefinedData']['vimId'];
$context['stackname'] = key($heatJson['resources']);
// TODO get this IP on a NFVO
$context['template_url'] = 'http://10.30.18.103/tech_report/vnf_packages/heat/' . $context['vnfPkgId'].'/vnfd.yaml';

//print_r($vnfPkg);
task_exit(ENDED, "Task OK");
