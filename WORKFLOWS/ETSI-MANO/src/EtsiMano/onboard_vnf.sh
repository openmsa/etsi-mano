#!/bin/bash

curl -v -su ncroot:ubiqube --location --request PUT "http://localhost:8380/ubi-etsi-mano/sol005/vnfpkgm/v1/vnf_packages/$1/package_content" \
	--header 'Accept: application/json' \
	--form "file=@$2"

