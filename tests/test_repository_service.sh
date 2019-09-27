#!/bin/bash

set -e
set -x

curl -u ncroot:OpenMSA http://localhost/ubi-api-rest/'repository/v1/search&URI=Process/ETSI-MANO&pattern='
