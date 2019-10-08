#!/bin/bash

set -e

run_tests() {
	for test in $@; do
		echo ====================================== $((++nb)) / $#
		echo :: $test
		echo --------------------------------------
		./$test >/dev/null || { echo "[FAILED]"; failed=1; }
	done
}

cd tests

echo -e "travis_fold:start:$0\r"
run_tests test_*
echo -e "travis_fold:end:$0\r"
exit $failed
