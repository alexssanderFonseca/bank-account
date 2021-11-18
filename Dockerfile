FROM adoptopenjdk/openjdk11
MAINTAINER alex
COPY target/bank-account-0.0.1-SNAPSHOT.jar bank-account-1.0.0.jar
ENTRYPOINT ["java","-jar","/bank-account-1.0.0.jar]