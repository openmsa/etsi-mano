<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args()
{
	
}

$auth_token = $context['token_id'];
$endpoints = $context['endpoints'];

foreach ($endpoints as &$endpoint) {
	if ($endpoint["type"] == "image") {
		$glance_endpoint = $endpoint["publicURL"];
	}
}

// Tab lists image paramaters to be excluded of the update.
$tab = array();
$tab[] = "id";
$tab[] = "status";
$tab[] = "size";

$user_metadata_array = array();
$body = array();

foreach ($context['images'] as &$images_details) {
	//if ($images_details['selected'] !== false) {
		foreach (images_details as $key => $value) {
			if (in_array($key, $tab)) {
				$body['op'] = "replace";
				$body['path'] = "/" . $key;
				$body['value'] = "/" . $value;
			}
		}
		$user_metadata_array[] = $body;
		unset($body);
	//}
}

$user_metadata = json_encode(array());

$response = _glance_update_images ($glance_endpoint, $auth_token, $user_metadata);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

$images_list = $response['wo_newparams'];

$response = prepare_json_response(ENDED, "Update images operation is done successfully.", $context, true);
echo $response;

?>