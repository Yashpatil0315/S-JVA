# Use Tomcat as base image
FROM tomcat:9-jdk11

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy your web application files
COPY target/S_JVA.war /usr/local/tomcat/webapps/ROOT.war

# Copy MySQL JDBC driver
ADD https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar /usr/local/tomcat/lib/

# Set environment variables (these will be overridden by Render's environment variables)
ENV DB_URL=jdbc:mysql://host:port/userdb
ENV DB_USER=root
ENV DB_PASSWORD=password

# Expose port (Render will override this)
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]