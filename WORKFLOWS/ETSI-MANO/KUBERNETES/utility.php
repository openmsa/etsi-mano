<?php 

require_once '/opt/fmc_repository/Process/Reference/Common/common.php';
require_once '/opt/fmc_repository/Process/Reference/OPENSTACK/Library/constants.php';

/**
 * Create Kubernetes CURL operation request
 *
 * @param unknown $operation
 * @param unknown $kubernetes_rest_api
 * @param string $auth_token
 * @param string $yaml_body
 * @return string
 */

/*

global $CURL_CMD;
global $CURL_OPENSTACK;
global $OS_CURL_VERBOSE;
global $OS_CURL_RETRY_COUNT;
global $OS_CURL_RETRY_DELAY;
global $OS_CURL_RETRY_MAX_TIME;


	 curl --tlsv1.2 -i -sw 'HTTP_CODE=%{http_code}' --connect-timeout 50 --max-time 50 -X POST --header "Authorization: Bearer $Token " -H "Content-Type: application/yaml" -k 'https://10.31.1.241:6443/apis/apps/v1/namespaces/default/deployments' -d '
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deployment-2
  labels:
    app: nginx
spec:
  replicas: 1
  selector:
    matchLabels:
      app: nginx
  template:
    metadata:
      labels:
        app: nginx
    spec:
      containers:
      - name: nginx
        image: nginx:1.14.2
        ports:
        - containerPort: 80

'
*/

function create_kubernetes_operation_request ($operation, $kubernetes_rest_api, $auth_token = "", $json_body = "",$connection_timeout, $max_time) {

	
	$curl_cmd = "curl --tlsv1.2 -i -sw '\nHTTP_CODE=%{http_code}' --connect-timeout $connection_timeout --max-time $max_time -X {$operation} --header \"Authorization: Bearer $auth_token\" -H \"Content-Type: application/json\" -k '{$kubernetes_rest_api}'";

	if ($json_body !== "") {
		$curl_cmd .= " -d '" . pretty_print_json($json_body) . "'";
	}
	//logToFile("Curl Request : $curl_cmd\n");
	return $curl_cmd;
}

function kubernetes_pod_deployment ($operation, $kubernetes_rest_api, $auth_token = "", $yaml_body = "",$connection_timeout, $max_time) {

	
	$curl_cmd = "curl --tlsv1.2 -i -sw '\nHTTP_CODE=%{http_code}' --connect-timeout $connection_timeout --max-time $max_time -X {$operation} --header \"Authorization: Bearer $auth_token\" -H \"Content-Type: application/yaml\" -k '{$kubernetes_rest_api}'";
        
	if ($yaml_body !== "") {
                $yaml_body = file_get_contents($yaml_body);
		$curl_cmd .= " -d '" . $yaml_body . "'";
	}
	//logToFile("Curl Request : $curl_cmd\n");
	return $curl_cmd;
}

function kubernetes_apply_yaml ($operation, $kubernetes_rest_api, $auth_token = "", $yaml_body = "",$connection_timeout, $max_time) {


        $curl_cmd = "curl --tlsv1.2 -i -sw '\nHTTP_CODE=%{http_code}' --connect-timeout $connection_timeout --max-time $max_time -X {$operation} --header \"Authorization: Bearer $auth_token\" -H \"Content-Type: application/yaml\" -k '{$kubernetes_rest_api}'";

        if ($yaml_body !== "") {
                $yaml_body = file_get_contents($yaml_body);
                $curl_cmd .= " -d '" . $yaml_body . "'";
        }
        //logToFile("Curl Request : $curl_cmd\n");
        return $curl_cmd;
}


?>
