<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{}

// TODO: select the automatically the most available hypervisor (host) in which one to migrate the VNF instances from failed host.
// {"hypervisors": [{"status": "enabled", "state": "up", "id": 2, "hypervisor_hostname": "fta-contrailsriov-0.localdomain"}, {"status": "enabled", "state": "up", "id": 5, "hypervisor_hostname": "fta-contraildpdk-0.localdomain"}, {"status": "enabled", "state": "up", "id": 8, "hypervisor_hostname": "fta-ospcompute-0.localdomain"}]}

$context['destination_hypervisor'] = 8;

task_exit(ENDED, "Task OK");

