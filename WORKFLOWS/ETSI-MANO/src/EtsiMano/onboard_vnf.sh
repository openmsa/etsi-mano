#!/bin/bash
curl -v -su $MANO_USER:$MANO_PWD --location --request PUT "$MANO_BASE_URL/sol005/vnfpkgm/v1/vnf_packages/$1/package_content" \
	--header 'Accept: application/json' \
	--form "file=@$2"

