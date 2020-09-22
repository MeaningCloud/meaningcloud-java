FROM gradle:6.6.1
COPY ./src /tmp/src
COPY build.gradle /tmp
COPY gradle /tmp/gradle
COPY gradlew /tmp
COPY settings.gradle /tmp
COPY version.gradle /tmp

WORKDIR /tmp

CMD gradle clean test -DMEANINGCLOUD_KEY=$DMEANINGCLOUD_KEY