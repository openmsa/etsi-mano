#!/bin/bash

cd docker

docker-compose build
docker-compose up -d

docker exec ubimano_msa_1 ifconfig eth0

sleep 3m
docker exec -it ubimano_msa_1 bash -lc check_services_status.sh
docker-compose down
