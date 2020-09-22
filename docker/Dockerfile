#FROM ubuntu:20.04
FROM gradle:6.6.1 AS build
#FROM openjdk:latest

###
COPY ./src /tmp/src
COPY build.gradle /tmp
COPY gradle /tmp/gradle
COPY gradlew /tmp
COPY settings.gradle /tmp
COPY version.gradle /tmp


#COPY . /tmp

WORKDIR /tmp

#RUN gradle clean test --tests *


#RUN gradle clean

CMD gradle clean test -DMEANINGCLOUD_KEY=111
#RUN ./gradlew tasks
#CMD ./gradlew clean test -DMEANINGCLOUD_KEY=0df215e6029d01b5e96def02552b375c

#CMD gradle build --no-daemon