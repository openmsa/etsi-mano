FROM gradle:jdk8-alpine

ARG SRC=tests
ARG DST=tests

RUN mkdir -p $DST
ADD $SRC $DSST
RUN cd $DST && gradle build
