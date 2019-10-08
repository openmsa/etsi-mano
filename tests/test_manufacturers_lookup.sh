#!/bin/bash

source .test-env.sh

$curl $msa_api'/device/v1/manufacturers'
