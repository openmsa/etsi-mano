<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\NfviVim;


function list_args(){
  create_var_def('vimId', 'OBMFRef');
}

$vimId = $context['vimId'];
$url = get_url_from_device($context['device_id']);

$nfviVim= new NfviVim($context['url']);

try{
	$response = $nfviVim->nfviVimDelete($vimId);
} catch (ManoException $e) {
        task_error($e->getMessage());
}

task_success("Vim (id=$vimId) is deleted successfully from NFVO.");
?>