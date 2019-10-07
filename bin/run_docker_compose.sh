#!/bin/bash

set -e

start_msa() {
	cd docker

	docker-compose up -d

	MSA="msa"

	docker-compose exec $MSA ifconfig eth0

	time wait_for_msa_api || {
		: "WARNING: check_login --wait timeout"
		docker-compose exec $MSA service tomcat restart
	}

	docker-compose exec $MSA check_services_status.sh
	docker-compose exec $MSA service elasticsearch status

	time wait_for_msa_api
}

start_mano_api() {
	docker-compose ps | grep ubimano_mano-api || {
		: "WARNING: restarting mano-api"
		docker-compose up -d mano-api
	}
	time wait_for_mano_api || {
		: "WARNING: wait_for_mano_api timeout"
	}

	docker-compose logs mano-api
}

wait_for_msa_api() {
	../bin/check_login.sh --wait
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
