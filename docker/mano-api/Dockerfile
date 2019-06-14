FROM gradle:jdk8-alpine

ARG SRC=MANO-API
ARG DST=mano

RUN mkdir -p $DST
ADD $SRC $DSST
RUN cd $DST && gradle build
