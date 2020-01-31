<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

$table = $context['lm_wf_service_instance_ref'];
$output = "";

foreach ($table as &$item) {
	$output .= " / " . $item;
}

logToFile("OUTPUT $output");

task_exit(FAILED, "Evacutae VNF not supported by the VIM current configuration. Candidates VNFs: $output");

