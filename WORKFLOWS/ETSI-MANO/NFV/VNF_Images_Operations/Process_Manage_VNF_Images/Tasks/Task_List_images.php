<?php

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/REST/openstack_common_rest.php';

function list_args() {
  create_var_def ("images.0.id", "String");
  create_var_def ("images.0.name", "String");
  create_var_def ("images.0.status", "String");
  create_var_def ("images.0.disk_format", "String");
  create_var_def ("images.0.container_format", "String");
  create_var_def ("images.0.size", "String");
  create_var_def ("images.0.min_ram", "String");
  create_var_def ("images.0.min_disk", "String");
  create_var_def ("images.0.protected", "Boolean");
  create_var_def ("images.0.visibility", "String");
}

$auth_token = $context['token_id'];
$endpoints = $context['endpoints'];

foreach ($endpoints as &$endpoint) {
	if ($endpoint["type"] == "image") {
		$glance_endpoint = $endpoint["publicURL"];
	}
}

$response = _glance_list_images ($glance_endpoint, $auth_token);
$response = json_decode($response, true);
if ($response['wo_status'] !== ENDED) {
	$response = json_encode($response);
	echo $response;
	exit;
}

$images_list = $response['wo_newparams'];

logToFile(debug_dump($images_list, "IMAGES RESPONSES. ============>:\n"));
// Clean images list from the $context
unset($context['images']);

$index = 0;
foreach ($images_list['images'] as &$image_details) {
	$context['images'][$index]['selected'] = false;
	$context['images'][$index]['id'] = $image_details['id'];
	$context['images'][$index]['name'] = $image_details['name'];
	$context['images'][$index]['status'] = $image_details['status'];
	$context['images'][$index]['disk_format'] = $image_details['disk_format'];
	$context['images'][$index]['container_format'] = $image_details['container_format'];
	$context['images'][$index]['size'] = $image_details['size'];
	$context['images'][$index]['min_ram'] = $image_details['min_ram'];
	$context['images'][$index]['min_disk'] = $image_details['min_disk'];
	$context['images'][$index]['protected'] = $image_details['protected'];
	$context['images'][$index]['visibility'] = $image_details['visibility'];
	
	$index++;
}

//logToFile(debug_dump($context['images'], "IMAGES CONTEXT. ============>:\n"));

$response = prepare_json_response(ENDED, "Get VNF images list successfully.", $context, true);
echo $response;

?>
