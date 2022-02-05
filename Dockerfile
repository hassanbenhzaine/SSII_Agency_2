FROM tomcat:10.0.16-jre17-temurin

COPY target/gestionemployes.war $CATALINA_HOME/webapps/ROOT.war

EXPOSE 8080

CMD catalina.sh run