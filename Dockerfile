FROM tomcat:10.0.16-jre17-temurin

LABEL maintainer="hassan benhzaine"

ADD target/gestionemployes.war /usr/local/tomcat/webapps/gestionemployes.war

EXPOSE 8080

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]