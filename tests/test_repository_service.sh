#!/bin/bash

source .test-env.sh

$curl $msa_api/'repository/v1/search?URI=Process/ETSI-MANO&pattern='
