<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';


function list_args()
{
}
// set the old_flavor in the $context
$context['old_flavor'] = $context['flavor'];

if ($context['is_auto_scale'] == "true") {

	$response = _nova_get_flavors_with_details ($context['endpoints'][NOVA][PUBLIC_URL], $context['token_id']);
	$response = json_decode($response, true);
	if ($response['wo_status'] !== ENDED) {
			task_exit(FAILED, "Flavors get with details failed.");
	}

	$flavors = json_decode($response['wo_newparams']['response_body'], true);

	//logToFile(debug_dump($flavors, "FLLAVORS=====================\n"));

	// SCALE UP: store in array only the flavor where CPU, RAM and DISK are higher than current flavor values.
	$flavors_higher_than_ref  = array();
	foreach ($flavors['flavors'] as &$flavor) {
		// flavor reference
		$cpu_ref = (int) $context['flavor_details']['flavor']['vcpus'];
		$ram_ref = (int) $context['flavor_details']['flavor']['ram'];
		$disk_ref = (int) $context['flavor_details']['flavor']['disk'];

		// flavor from list
		$cpu = (int) $flavor['vcpus'];
		$ram = (int) $flavor['ram'];
		$disk = (int) $flavor['disk'];

		logToFile ("OUTSIDE +++++ CPU $cpu > $cpu_ref , RAM: $ram > $ram_ref, DISK: $disk > $disk_ref \n");
		if ($cpu > $cpu_ref && $ram > $ram_ref && $disk > $disk_ref) {
			logToFile ("INSIDE ==> CPU $cpu > $cpu_ref , RAM: $ram > $ram_ref, DISK: $disk > $disk_ref \n");
			$flavors_higher_than_ref[] = $flavor;
		} 
	}
	logToFile (debug_dump($flavors_higher_than_ref,"HIGHER_REF flavors_higher_than_ref ========> \n"));

	// Compare each flavor in flavors_higher_than_ref array and order them desc
	if (count($flavors_higher_than_ref) > 0) {
		$flavor_scale_up = $flavors_higher_than_ref[0]; // initialize flavor for scale up op.
	} else {
		$ret = prepare_json_response(FAILED, "There is no higher flavor than current applied flavor " . $context['flavor'], $context, true);
        	echo $ret;
		exit;		
	}
	foreach ($flavors_higher_than_ref as &$flavor) {

		$cpu_ref = (int) $flavor_scale_up['vcpus'];
		$ram_ref = (int) $flavor_scale_up['ram'];
		$disk_ref = (int) $flavor_scale_up['disk'];

		$cpu = (int) $flavor['vcpus'];
		$ram = (int) $flavor['ram'];
		$disk = (int) $flavor['disk'];

		if ($cpu < $cpu_ref && $ram < $ram_ref && $disk < $disk_ref) {
			//logToFile ("ORDERED::::::::::::: CPU $cpu > $cpu_ref , RAM: $ram > $ram_ref, DISK: $disk > $disk_ref \n");
			$flavor_scale_up = $flavor;
		}	
	}
	$context['flavor_scale'] = $flavor_scale_up['id'];
	$context['flavor_scale_name'] = $flavor_scale_up['name'];
	//logToFile ("AUTO_SCALE_UP========> " . $flavor_scale_up['name'] . " \n");

	$ret = prepare_json_response(ENDED, "Calucl higher flavor done.", $context, true);
	echo $ret;
} else {
	$ret = prepare_json_response(ENDED, "Skip task: Calucl higher flavor is not required.", $context, true);
	echo $ret;

}

?>
