#!/bin/bash

set -e
set -x

curl	--fail \
	--user x: \
	-X GET \
	-H  "Content-Type: application/json" \
	"http://localhost:8666/ubi-etsi-mano/sol003/vnfpkgm/v1/vnf_packages"
