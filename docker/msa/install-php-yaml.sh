#!/bin/bash

set -x
set -e

PACKAGES="
http://dl.fedoraproject.org/pub/epel/6/x86_64/Packages/p/php-pecl-yaml-1.1.1-5.el6.x86_64.rpm
http://mirror.centos.org/centos/6/os/x86_64/Packages/php-pear-1.9.4-5.el6.noarch.rpm
http://mirror.centos.org/centos/6/os/x86_64/Packages/libyaml-0.1.3-4.el6_6.x86_64.rpm
"

mkdir php-yaml
cd php-yaml

for package in $PACKAGES; do
	wget -q $package
done

rpm -i *.rpm
