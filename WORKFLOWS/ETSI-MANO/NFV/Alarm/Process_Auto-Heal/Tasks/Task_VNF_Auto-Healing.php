<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';

function list_args()
{
   create_var_def ('customer_id', 'String');
   create_var_def ('device_id', 'String');
}

task_exit(ENDED, "Auto-Healing OK");

?>