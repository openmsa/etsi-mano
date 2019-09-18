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

$vnfPkgManagement = new VnfPkgSol003('http://localhost:8380/ubi-etsi-mano/');
$vnfPkg = $vnfPkgManagement->vnfPackagesVnfPkgIdGet($context['vnfPkgId']);

// Check VNFPkg operational state
if ($vnfPkg['operationalState'] == "DISABLED") {
        task_exit(FAILED, "VNF Package with ID: " . $context['vnfPkgId'] . " cannot instantiate, its operational state is DISABLED.");
}

$content=$vnfPkgManagement->vnfPackagesVnfPkgIdPackageContentGet($context['vnfPkgId']);

$path = '/opt/ses/share/htdocs/tech_report/vnf_packages/heat/' . $context['vnfPkgId'];
@mkdir($path);
file_put_contents($path . '/vnfd', $content);


$context['template_url'] = 'http://10.31.1.246/tech_report/vnf_packages/heat/' . $context['vnfPkgId'].'/vnfd';










task_exit(ENDED, "Task OK");

?>