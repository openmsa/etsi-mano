<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';

function list_args(){}

$url = get_url_from_device($context['device_id']);

$context['url'] = $url;

task_success("NFVO URL is seved in the context.");
?>