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

$nsPkgId = $context['nsPkgId'];
$nsPkgManagement = new NsdSol005('http://localhost:8380/ubi-etsi-mano-0.0.1-SNAPSHOT/');
$nsPkgInfo = $nsPkgManagement->nsDescriptorsNsdInfoIdGet($nsPkgId);

$vnfPkgIds = $nsPkgInfo['userDefinedData']['vnfPkgIds'];

$context['vnfPkgIds'] = $vnfPkgIds;

task_exit(ENDED, "VNF Packages IDs linked to the NSPackage with ID: $nsPkgId are stored in the context.");

?>