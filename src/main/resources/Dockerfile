FROM java:8
MAINTAINER smartrick
WORKDIR /opt/shucangdh
VOLUME /opt/shucangdh

ADD ./shucangdh-admin-1.0-SNAPSHOT.jar ./

EXPOSE 8599

ENTRYPOINT ["java","-jar","/opt/shucangdh/shucangdh-admin-1.0-SNAPSHOT.jar"]