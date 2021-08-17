#!/bin/bash

MANO_USER="ncroot"
MANO_PWD="ubiqube"
MANO_BASE_URL="http://10.31.1.245:8080/ubi-etsi-mano"

curl -v -su $MANO_USER:$MANO_PWD --location --request PUT "$MANO_BASE_URL/sol005/vnfpkgm/v1/vnf_packages/$1/package_content" \
	--header 'Accept: application/json' \
	--form "file=@$2"

