<?php
require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/Common/Library/msa_common.php';




$CENTOS = "centos";
$IMS = "ims";
$LEFT = "left";
$RIGHT = "right";
$VSRX = "juniper";


$fullname ="";
if (isset($vnfPkg['userDefinedData']['vnf_package_name'])) {
$fullname = $vnfPkg['userDefinedData']['vnf_package_name'];
}

if(strstr($fullname,$CENTOS) !== FALSE){

if(strstr($fullname,$LEFT) !== FALSE){

//execute_linux_command_and_wait_for_output ("ip route add 10.10.10.10 via 192.168.30.2","",null);
}

if(strstr($fullname,$RIGHT) !== FALSE){

//execute_linux_command_and_wait_for_output ("ip route add 10.10.10.10 via 192.168.30.2","",null);
}

task_exit(ENDED, "CENTOS");
}

if(strstr($fullname,$IMS) !== FALSE){
//execute_linux_command_and_wait_for_output ("ip route add 10.10.10.10 via 192.168.30.2","",null);
task_exit(ENDED, "IMS");
}

if(strstr($fullname,$VSRX) !== FALSE){
//execute_linux_command_and_wait_for_output ("ip route add 10.10.10.10 via 192.168.30.2","",null);
task_exit(ENDED, "VSRX");
}

task_exit(ENDED, "Nothing to do for this VNF");

?>