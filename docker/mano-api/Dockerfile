FROM gradle:jdk8-alpine

RUN mkdir .mano/
COPY MANO-API/configuration.properties.tmpl .mano/configuration.properties

ADD --chown=gradle MANO-API .
RUN gradle build

CMD [ "gradle", "bootRun" ]
