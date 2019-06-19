FROM gradle:jdk8-alpine

ADD --chown=gradle tests .
RUN gradle build

CMD [ "gradle", "test" ]
