#!/bin/bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=false -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
