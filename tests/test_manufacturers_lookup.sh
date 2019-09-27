#!/bin/bash

set -e
set -x

curl -u ncroot:OpenMSA http://localhost/ubi-api-rest/'device/v1/manufacturers'
