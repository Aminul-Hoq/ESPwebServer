FROM tomcat:latest
ADD target/web-server.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
