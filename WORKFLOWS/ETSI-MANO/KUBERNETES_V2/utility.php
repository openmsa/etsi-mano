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


	 curl --tlsv1.2 -i -sw 'HTTP_CODE=%{http_code}' --connect-timeout 50 --max-time 50 -X POST --header "Authorization: Bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkN1NDFDSmo0cW9haVo1V1dKOVlxS3prTW9lLXplckYySHVOQmF4V1IxYjQifQ.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJkZWZhdWx0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6ImRlZmF1bHQtdG9rZW4tbmM0eG4iLCJrdWJlcm5ldGVzLmlvL3NlcnZpY2VhY2NvdW50L3NlcnZpY2UtYWNjb3VudC5uYW1lIjoiZGVmYXVsdCIsImt1YmVybmV0ZXMuaW8vc2VydmljZWFjY291bnQvc2VydmljZS1hY2NvdW50LnVpZCI6IjkzNThhNTJhLTI2NTItNDE4MS05NThjLTQ3NGViMzJiNzYxOSIsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDpkZWZhdWx0OmRlZmF1bHQifQ.yTawxTUfQewCHEGpA1MvV7-xOqM-1gq_3s9gxZYRHqnYDJNtlpo3yxtBLDBnIWc-BJNKXrpgnKlymRBsstwAxBOJTYX-4AZrDNT7EpkFOAtKFBoUE2iGSG6kOCt3RhNjnmCMNh6pE-ljzfbi6wWydFSSrY-QEIrnJOkDHLEl9VvypLMxuviA2uDSEVTKny-0_qHuV_MlLC-AxTUqo41-XvrCPXCUk8qMp9yNo9I3kz8Cm8UGnN5MlvwjrF6BjEonN8NoUpvRGKCAmh_qugZYFoRVy--k8kjKERANvZhDL_iZTSuvlYrqVampZGcvnIIexQdAsOzGw-J7cIla7tv_6w" -H "Content-Type: application/yaml" -k 'https://10.31.1.241:6443/apis/apps/v1/namespaces/default/deployments' -d '
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

?>