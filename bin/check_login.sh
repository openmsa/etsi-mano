#!/bin/bash

LOGIN_URL="https://localhost/OPE/en/j_security_check"

is_login_ok() {
	curl -k -s \
		-d 'j_username=ncroot' \
		-d 'j_password=OpenMSA' \
		$LOGIN_URL \
	| grep -q script/ses/util.js
}

wait_for_login() {
	while ! is_login_ok; do
		sleep 30
		[ ! $((i++)) -gt 6 ] || return 1
	done
}

test_for_login() {
	if is_login_ok;
	then
		echo Login OK
	else
		echo Login FAILED
		exit 1
	fi
}

usage() {
	cat $0 | grep -P '^\t-'
}

case $1 in
	-w|--wait)	wait_for_login
		;;
	-t|--test)	test_for_login
		;;
	""|-h|--help)
		usage ;;
	*) echo "unknown command: $1";
		exit 1;;
esac
