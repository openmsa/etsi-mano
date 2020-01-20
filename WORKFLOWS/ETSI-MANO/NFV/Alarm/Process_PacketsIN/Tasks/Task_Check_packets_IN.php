<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{}

$ALARM_LIMIT = 10000; // In number of packets received
$CHECK_WINDOW = 20; // In seconds

$out = exec_shell("snmpwalk -v 2c -c ubiqube 10.134.160.104 1.3.6.1.2.1.31.1.1.1.7.13");
$pkt_number_t1 = substr($out, 40);

logToFile("PKKKKT:$pkt_number_t1");
logToFile("PKKKKT:$out");

sleep($CHECK_WINDOW);

$out = exec_shell("snmpwalk -v 2c -c ubiqube 10.134.160.104 1.3.6.1.2.1.31.1.1.1.7.13");
$pkt_number_t2 = substr($out, 40);

$packet_diff = $pkt_number_t2 - $pkt_number_t1;
logToFile("DIIIIIIIIIIIIIF:$packet_diff");

if ($packet_diff > $ALARM_LIMIT) {}

task_exit(ENDED, "Task OK");

