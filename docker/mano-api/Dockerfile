FROM gradle:jdk8-alpine

ADD --chown=gradle MANO-API .
RUN gradle build

CMD [ "gradle", "bootRun" ]
