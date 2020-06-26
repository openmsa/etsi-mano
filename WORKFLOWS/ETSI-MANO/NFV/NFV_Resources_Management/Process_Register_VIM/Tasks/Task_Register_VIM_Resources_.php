<?php


require_once '/opt/fmc_repository/Process/ETSI-MANO/Reference/Common/mano.php';


use Ubiqube\EtsiMano\NfviVim;


function list_args(){
  create_var_def('vimType', 'String');
  create_var_def('interfaceInfo', 'String');
  create_var_def('username', 'String');
  create_var_def('password', 'String');
  create_var_def('projectId', 'String');
  create_var_def('projectDomain', 'String');
  create_var_def('userDomain', 'String');
  create_var_def('vim_project', 'String');
}

$url = get_url_from_device($context['device_id']);

$nfviVim= new NfviVim($context['url']);

$accessInfo = array("username" => $context['username'],
		    "password" => $context['password'],
		    "projectId" => $context['projectId'],
		    "projectDomain" => $context['projectDomain'],
		    "userDomain" => $context['userDomain'],
		    "vim_project" => $context['vim_project'],
		    "device_id" => $context['device_id'],
	);

$body_array = array("id" => gen_uuid(),
	      "vimType" => $context['vimType'],
	      "interfaceInfo" => array("endpoint" => $context['interfaceInfo']),
	      "vimType" => $context['vimType'],
	      "accessInfo" => $accessInfo		
	);

try{
	$body = json_encode($body_array);
	//logToFile("DEV-DEBU:: " . $body . "\n");
	$response = $nfviVim->nfviVimRegister($body);
} catch (ManoException $e) {
        task_error($e->getMessage());
}
$vimId = $response['id'];
$context['vimId'] = $vimId;

task_success("Vim is registered successfully with id = $vimId");
?>