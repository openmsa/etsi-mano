<?php
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";
use Ubiqube\EtsiMano\VnfPkgSol003;

use Symfony\Component\Yaml\Yaml;

function list_args()
{
        create_var_def('nfvoDevice', 'Device');
        create_var_def('vnfPkgId', 'String');
}

check_mandatory_param('vnfPkgId');
$url = get_url_from_device($context['device_id']);
$vnfPkgManagement = new VnfPkgSol003($url);
$vnfPkg = $vnfPkgManagement->vnfPackagesVnfPkgIdGet($context['vnfPkgId']);

// Check VNFPkg operational state
if ($vnfPkg['operationalState'] == "DISABLED") {
        task_exit(FAILED, "VNF Package with ID: " . $context['vnfPkgId'] . " cannot instantiate, its operational state is DISABLED.");
}

$content=$vnfPkgManagement->vnfPackagesVnfPkgIdPackageContentGet($context['vnfPkgId']);

$path = '/opt/ses/share/htdocs/tech_report/vnf_packages/heat/' . $context['vnfPkgId'];
@mkdir($path);
file_put_contents($path . '/vnfd', $content);


$context['template_url'] = 'http://10.10.14.250/tech_report/vnf_packages/heat/' . $context['vnfPkgId'].'/vnfd';










task_exit(ENDED, "Task OK");

?>
