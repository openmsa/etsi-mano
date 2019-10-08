#!/bin/bash

source .test-env.sh

$curl	-H  "Content-Type: application/json" \
	$mano_api/sol003/vnfpkgm/v1/vnf_packages
