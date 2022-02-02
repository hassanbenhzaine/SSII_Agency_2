FROM tomcat:10.0.16-jre17-temurin

LABEL maintainer="hassan benhzaine"

COPY target/gestionemployes.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["/usr/local/tomcat/bin/catalina.sh", "run"]