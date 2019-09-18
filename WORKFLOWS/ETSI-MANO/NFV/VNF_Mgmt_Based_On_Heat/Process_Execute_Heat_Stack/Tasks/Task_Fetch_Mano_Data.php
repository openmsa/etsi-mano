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

$vnfPkgManagement = new VnfPkgSol003('http://localhost:8380/ubi-etsi-mano/');
$vnfPkg = $vnfPkgManagement->vnfPackagesVnfPkgIdGet($context['vnfPkgId']);

// Check VNFPkg operational state
if ($vnfPkg['operationalState'] == "DISABLED") {
	task_exit(FAILED, "VNF Package with ID: " . $context['vnfPkgId'] . " cannot instantiate, its operational state is DISABLED.");
}
if (array_key_exists('heat', $vnfPkg['userDefinedData'])) {
	$heatJson = $vnfPkg['userDefinedData']['heat'];
	foreach ($heatJson['resources'] as &$resource) {
		if ($resource['type'] == 'OS::Nova::Server') {
			$resource['properties']['name'] = $context['SERVICEINSTANCEREFERENCE'];
		}
	}
	$context['image'] = $vnfPkg['userDefinedData']['heat']['parameters']['simulator_image_id']['default'];
	$context['flavor'] = $vnfPkg['userDefinedData']['heat']['parameters']['flavor']['default'];
	$context['old_flavor'] = $context['flavor'];
	$content = Yaml::dump($heatJson);
} else {
	$content = $vnfPkgManagement->vnfPackagesVnfPkgIdPackageContentGet($context['vnfPkgId']);
}
// Patch first resource entry.

$path = '/opt/ses/share/htdocs/tech_report/vnf_packages/heat/' . $context['vnfPkgId'];
@mkdir($path);
file_put_contents($path . '/vnfd.yaml', $content);

$context['deviceid'] = $vnfPkg['userDefinedData']['vimId'];
$context['stackname'] = $context['SERVICEINSTANCEREFERENCE'];
$context['manufacturerId'] = $vnfPkg['userDefinedData']['manufacturerId'];
$context['modelId'] = $vnfPkg['userDefinedData']['modelId'];
$context['device_login'] = $vnfPkg['userDefinedData']['device_login'];
$context['device_password'] = $vnfPkg['userDefinedData']['device_password'];

// TODO get this IP on a NFVO
$context['template_url'] = 'http://10.31.1.246/tech_report/vnf_packages/heat/' . $context['vnfPkgId'] . '/vnfd.yaml';

task_exit(ENDED, "VNFD fetched successfully from NFVO - VNF Catalogs.");
