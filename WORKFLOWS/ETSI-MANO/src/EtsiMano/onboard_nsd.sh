#!/bin/bash
curl -su ncroot:ubiqube --location --request PUT "http://localhost:8380/ubi-etsi-mano/sol005/nsd/v1/ns_descriptors/$1/nsd_content" --header 'Accept: application/json' --form "file=@$2"
