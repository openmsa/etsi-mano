#!/bin/bash

LOGIN_URL="https://localhost/OPE/en/j_security_check"
if
	curl -k -s \
		-d 'j_username=ncroot' \
		-d 'j_password=OpenMSA' \
		$LOGIN_URL \
	| grep -q script/ses/util.js

then
	echo Login OK
else
	echo Login FAILED
	exit 1
fi
