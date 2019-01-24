FROM maven:3.5.3-jdk-8
WORKDIR ./
ADD / ./
CMD mvn -s settings.xml clean verify