# ETSI-MANO API

## Development setup
### Configuration file
* Copy ``configuration.properties.tmpl`` into ``$HOME/.mano/configuration.properties``
* Adjust values

### ActiveMQ/JMS Server

```
$ docker run -it --rm -p 8161:8161 -p 61616:61616 vromero/activemq-artemis:2.11.0
```

### Launch service

```
$ gradle bootRun
```
Or for debugging :

```
$ gradle bootRun --debug-jvm 
```

