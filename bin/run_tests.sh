#!/bin/bash

set -e

run_tests() {
	for test in $@; do
		: ========================================= $((++nb)) / $#
		./$test
	done
}

travis_fold() {
	local func=$1
	shift
	echo -e "travis_fold:start:$func\r"
	( set -x; $func "$@")
	echo -e "travis_fold:end:$func\r"
}

travis_fold run_tests tests/test_*
