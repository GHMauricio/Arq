FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline
COPY src ./src
COPY lombok.config ./
RUN MAVEN_OPTS="-Xmx256m" ./mvnw clean package -DskipTests -Dlombok.addLombokGeneratedAnnotation=true
EXPOSE 8081
ENTRYPOINT ["sh", "-c", "java -jar target/*.jar"]
