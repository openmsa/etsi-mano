<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\VnfPkgSol003;

use Symfony\Component\Yaml\Yaml;

function list_args()
{
	create_var_def('nfvoDevice', 'Device');
        create_var_def('vnfPkgId', 'String');
}
check_mandatory_param('vnfPkgId');
logToFile(debug_dump($context, "CONTEXT ============>:\n"));

$vnfPkgManagement = new VnfPkgSol003('http://localhost:8380/ubi-etsi-mano-0.0.1-SNAPSHOT/');
$vnfPkg = $vnfPkgManagement->vnfPackagesVnfPkgIdGet($context['vnfPkgId']);
$heatJson = $vnfPkg['userDefinedData']['heat'];
// Patch first resource entry.
//$firstEntry = key($heatJson['resources']);
//$heatJson['resources'][$context['SERVICEINSTANCEREFERENCE']] = $heatJson['resources'][$firstEntry];
foreach($heatJson['resources'] as &$resource) {
	if($resource['type'] == 'OS::Nova::Server') {
		$resource['properties']['name'] = $context['SERVICEINSTANCEREFERENCE'];
	}
}

$heatYaml = Yaml::dump($heatJson);

$path = '/opt/ses/share/htdocs/tech_report/vnf_packages/heat/' . $context['vnfPkgId'];
@mkdir($path);
file_put_contents($path . '/vnfd.yaml', $heatYaml);

$context['deviceid'] = $vnfPkg['userDefinedData']['vimId'];
$context['stackname'] = $context['SERVICEINSTANCEREFERENCE'];
$context['manufacturerId'] = $vnfPkg['userDefinedData']['manufacturerId'];
$context['modelId'] = $vnfPkg['userDefinedData']['modelId'];
$context['flavor'] = $vnfPkg['userDefinedData']['heat']['parameters']['flavor']['default'];
$context['device_login'] = $vnfPkg['userDefinedData']['device_login'];
$context['device_password'] = $vnfPkg['userDefinedData']['device_password'];

//$context['simulator_image_id'] = $vnfPkg['userDefinedData']['heat']['parameters']['simulator_image_id']['default'];

// TODO get this IP on a NFVO
$context['template_url'] = 'http://10.31.1.246/tech_report/vnf_packages/heat/' . $context['vnfPkgId'].'/vnfd.yaml';

//print_r($vnfPkg);
task_exit(ENDED, "Task OK");
