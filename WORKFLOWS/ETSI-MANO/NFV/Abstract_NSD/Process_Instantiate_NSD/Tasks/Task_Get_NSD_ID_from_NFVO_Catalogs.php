<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
include "/opt/fmc_repository/Process/ETSI-MANO/vendor/autoload.php";

use Ubiqube\EtsiMano\NsdSol005;
use Symfony\Component\Yaml\Yaml;

function list_args()
{
  create_var_def('nsPkgId', 'String');
  create_var_def('vnfvo_device', 'String');
}

check_mandatory_param('nsPkgId');

$nsPkgId = $context['nsPkgId'];
//$nsPkgManagement = new NsdSol005('http://localhost:8380/ubi-etsi-mano-0.0.1-SNAPSHOT/');
$nsPkgManagement = new NsdSol005('http://localhost:8380/ubi-etsi-mano/');
$nsPkgInfo = $nsPkgManagement->nsDescriptorsNsdInfoIdGet($nsPkgId);

$vnfPkgIds = $nsPkgInfo['userDefinedData']['vnfPkgIds'];

$i=0;
foreach ($nsPkgInfo['vnfPkgIds'] as $vnfPkgId) {
	$context['vnfPkgs'][$i++]['vnfPkgId']=$vnfPkgId;
}


task_exit(ENDED, "VNF Packages IDs linked to the NSPackage with ID: $nsPkgId are stored in the context.");

?>
