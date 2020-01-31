<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include_once '/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php';
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
$url = get_url_from_device($context['nfvoDevice']);
$nsPkgManagement = new NsdSol005($url);
try {
	$nsPkg = $nsPkgManagement->nsDescriptorsNsdInfoIdGet($context['nsPkgId']);
} catch (ManoException $e) {
	task_error($e->getMessage());
}

$context['deviceid'] = $nsPkg['userDefinedData']['vimId'];
$path = '/opt/ses/share/htdocs/tech_report/ns_packages/heat/' . $context['nsPkgId'];
@mkdir($path);

if (array_key_exists('heat', $nsPkg['userDefinedData'])) {
	$heatJson = $nsPkg['userDefinedData']['heat'];
	$heatYaml = Yaml::dump($heatJson);
	file_put_contents($path . '/nsd.yaml', $heatYaml);

	$context['stackname'] = $nsPkg['nsdName'];
} else {
	try {
		$content = $nsPkgManagement->nsDescriptorsNsdInfoIdNsdContentGet($context['nsPkgId']);
		file_put_contents($path . '/nsd.yaml', $content);
		$context['stackname'] = $nsPkg['nsdName'];
	} catch (ManoException $e) {
		task_error($e->getMessage());
	}
}

// TODO get this IP on a NFVO
$context['template_url'] = 'http://10.10.14.223/tech_report/ns_packages/heat/' . $context['nsPkgId'] . '/nsd.yaml';

$i = 0;
if (array_key_exists('vnfPkgIds', $nsPkg)) {
	foreach ($nsPkg['vnfPkgIds'] as $vnfPkgId) {
		$context['vnfPkgs'][$i ++]['vnfPkgId'] = $vnfPkgId;
	}
}

task_exit(ENDED, "Task OK");
