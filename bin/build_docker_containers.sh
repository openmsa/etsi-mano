#!/bin/bash

set -e

docker_compose_build() {
	cd docker
	docker-compose build
}

travis_fold() {
	local func=$1
	echo -e "travis_fold:start:$func\r"
	( set -x; $func )
	echo -e "travis_fold:end:$func\r"
}

travis_fold docker_compose_build
