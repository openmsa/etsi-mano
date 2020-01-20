<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{}

// NFVO device ID (Hardcode)
$context['nfvo_device'] = "TMA129";

// VIM device ID (Hardcode)
$context['deviceid'] = "TMA125";

task_exit(ENDED, "Task OK");

