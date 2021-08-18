#!/bin/bash

MANO_USER="ncroot"
MANO_PWD="ubiqube"
MANO_BASE_URL="http://10.31.1.245:8080/ubi-etsi-mano"

curl -su $MANO_USER:$MANO_PWD --location --request PUT "$MANO_BASE_URL/sol005/nsd/v1/ns_descriptors/$1/nsd_content" --header 'Accept: application/json' --form "file=@$2"
