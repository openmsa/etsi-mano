#!/bin/bash

set -x
set -e

cd docker

docker-compose up -d

MSA="ubimano_msa_1"

docker exec $MSA ifconfig eth0

sleep 3m

docker exec -it $MSA check_services_status.sh
docker exec $MSA service elasticsearch status

../tests/test_login.sh || {
	docker exec $MSA service tomcat restart
}
