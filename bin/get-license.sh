#!/bin/bash

#set -x

LOGIN_PAGE="https://www.openmsa.co/accounts/login/"
DWNLD_PAGE="https://www.openmsa.co/download/2/"

CREDENTIALS_FILE=".openmsa.co.credentials"

LOGIN=
PASSWORD=


do_login() {
	token=$(get_token)
	curl -s -b $COOKIES -c $COOKIES \
	-F "$TOKEN_NAME=$token" \
	-F "login=$LOGIN" \
	-F "password=$PASSWORD" \
	$LOGIN_PAGE
}

get_download_link() {
	curl -s -b $COOKIES \
	$DWNLD_PAGE \
	| grep "/ubiqube/OpenMSA-eval.lic" \
	| sed 's:.*location.href = "::' \
	| sed 's:";.*::'
}

get_license_file() {
	curl -s $(get_download_link)
}

get_token() {
	curl -s -L -c $COOKIES \
	$LOGIN_PAGE \
	| extract_token
}

extract_token() {
	grep $TOKEN_NAME \
	| sed 's:.* value="::; s:".*::'
}

get_credentials() {
	[ -f $CREDENTIALS_FILE ] || {
		echo -e "LOGIN=\nPASSWORD=" > $CREDENTIALS_FILE
		echo "created empty credentials file: $CREDENTIALS_FILE" >&2
	}

	source $CREDENTIALS_FILE

	[ "$LOGIN" ]	|| { echo "LOGIN not defined" >&2; exit 1; }
	[ "$PASSWORD" ]	|| { echo "PASSWORD not defined" >&2; exit 1; }
}

main() {
	COOKIES=$(mktemp)

	TOKEN_NAME="csrfmiddlewaretoken"

	get_credentials
	do_login
	get_license_file

	\rm -f $COOKIES
}

main
