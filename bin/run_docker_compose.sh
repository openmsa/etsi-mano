#!/bin/bash

set -e

start_msa() {
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
}

start_mano_api() {
	docker ps | grep ubimano_mano-api || {
		: "WARNING: restarting mano-api"
		docker-compose up -d mano-api
	}
	time wait_for_mano_api || {
		: "WARNING: wait_for_mano_api timeout"
	}

	docker-compose logs mano-api
}

check_mano_api() {
	curl -sL  -u x:x localhost:8666/ubi-etsi-mano/ | grep -q swagger-ui
}

wait_for_mano_api() {
	while ! check_mano_api; do
		sleep 1
		[ $(( ++nb )) -lt 30 ] || return 1
	done
}


echo -e "travis_fold:start:$0\r"
(set -x
start_msa
start_mano_api
)
echo -e "travis_fold:end:$0\r"
