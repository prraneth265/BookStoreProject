FROM tomcat:9-jre11

RUN rm -rf /usr/local/tomcat/webapps/*

COPY target/BookStoreProject-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/

EXPOSE 8080
CMD ["catalina.sh", "run"] 
