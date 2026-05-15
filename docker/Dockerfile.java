# Vulnerable Dockerfile - Java Application
# DO NOT USE IN PRODUCTION!

# VULNERABILITY: Using OpenJDK 11 without specific digest
FROM openjdk:11-jdk-slim

# VULNERABILITY: Running as root
WORKDIR /app

# VULNERABILITY: Unpinned apt packages
RUN apt-get update && apt-get install -y \
    curl \
    wget \
    git \
    maven \
    && rm -rf /var/lib/apt/lists/*

# VULNERABILITY: Maven wrapper not verified
RUN curl -L https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper/3.1.0/maven-wrapper-3.1.0.jar -o maven-wrapper.jar

COPY pom.xml .
COPY src ./src

# VULNERABILITY: Building with potentially malicious dependencies
RUN mvn package -DskipTests

# VULNERABILITY: Running with debug enabled
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=*:5005"

# VULNERABILITY: Multiple ports exposed
EXPOSE 8080 5005

# VULNERABILITY: Running as root with debug port exposed
CMD ["java", "-jar", "target/vuln-java-app-1.0.0.jar"]
