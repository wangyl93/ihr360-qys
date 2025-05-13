FROM harbor.ihr360.com/build-deps/ihr360-oracle-java-docker
MAINTAINER Grayson Xu <grayson.xu@ihr360.com>

## Change the timezone for 16.04
RUN apt-get install -y tzdata
RUN ln -fs /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone

RUN locale-gen en_US.UTF-8
ENV LANG=en_US.UTF-8
RUN rm -rf /var/lib/apt/lists/*
WORKDIR /opt
VOLUME /opt

ARG SERVICE_NAME
ARG SW_AGENT_COLLECTOR

ENV SW_AGENT_NAME=$SERVICE_NAME
ENV SW_AGENT_COLLECTOR_BACKEND_SERVICES=$SW_AGENT_COLLECTOR

ADD ./target/ihr360-applet-customization-hljqys-*.jar ihr360-applet-customization-hljqys.jar
ADD ./target/classes/application.properties application.properties
ADD ./target/classes/application-prod.properties application-prod.properties
ENV JAVA_OPTS="-Xmx2g -Xmx2g  -XX:NewSize=96m -XX:MaxNewSize=192m -XX:+HeapDumpOnOutOfMemoryError"
EXPOSE 41717
ENTRYPOINT [ "sh", "-c", "exec java -server $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /opt/ihr360-applet-customization-hljqys.jar" ]