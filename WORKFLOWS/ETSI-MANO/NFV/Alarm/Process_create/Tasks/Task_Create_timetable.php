<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

$now = time();

$context['timestamps'] = array();
$context['timestamps'][0] = $now;
$context['timestamps'][1] = $now;
$context['timestamps'][2] = $now;
$context['timestamps'][3] = $now;

task_exit(ENDED, "Timetable created");

