FROM gradle:jdk8-alpine

ADD --chown=gradle tests .
RUN gradle build

USER root
RUN apk add curl bash

CMD [ "gradle", "test" ]
