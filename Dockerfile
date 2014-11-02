FROM ubuntu:precise
#make sure the package repository is up to date
run apt-get update
run apt-get install -y less
run apt-get install -y software-properties-common
run apt-get install -y software-properties-common python-software-properties
run add-apt-repository -y ppa:webupd8team/java
run apt-get update

run echo "oracle-java8-installer shared/accepted-oracle-license-v1-1 boolean true" | debconf-set-selections
run apt-get install -y oracle-java8-installer

run apt-get -y install tomcat7

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle 
ENV PATH $PATH:$JAVA_HOME/bin
ENV fastbill.apiKey 4ab64295a1d52590fccca7f9be4c2dcaLHkM2AcfUcMTVzlEiO5rBJCoY1IhDgNG

run apt-get install -y unzip
run apt-get install -y curl

COPY /target/test-0.0.1-SNAPSHOT.jar /sources/app3.jar

#expose 8080

# Install tools
#CMD service tomcat7 start && tail -f /var/lib/tomcat7/logs/catalina.out
#CMD java -Djava.security.egd=file:/dev/./urandom -jar /sources/app.jar
