#!/bin/bash

set -x
set -e

cd docker

docker-compose up -d

MSA="ubimano_msa_1"

docker exec $MSA ifconfig eth0

time ../bin/check_login.sh --wait || {
	: "WARNING: check_login --wait timeout"
	docker exec $MSA service tomcat restart
}

docker exec -it $MSA check_services_status.sh
docker exec $MSA service elasticsearch status
