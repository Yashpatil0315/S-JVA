# Build stage
FROM maven:3.8.4-openjdk-11 as builder

# Copy the project files
WORKDIR /app
COPY . .

# Build the project
RUN mvn clean package

# Runtime stage
FROM tomcat:9-jdk11

# Remove default Tomcat applications
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file from builder stage
COPY --from=builder /app/target/S_JVA.war /usr/local/tomcat/webapps/ROOT.war

# Copy MySQL JDBC driver
ADD https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.28/mysql-connector-java-8.0.28.jar /usr/local/tomcat/lib/

# Expose port 8080
EXPOSE 8080

CMD ["catalina.sh", "run"]