# ETSI-MANO API

## Development setup
### ActiveMQ/JMS Server

```
$ docker run -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis:2.17.0
```

### Launch service

```
$ cd mano-webapp
$ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=false"
```
Or for debugging :

```
$ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=false -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

