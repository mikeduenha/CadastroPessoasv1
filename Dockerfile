FROM maven:3-amazoncorretto-24 AS BUILD
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto:24
COPY --from=BUILD /app/target/*.jar /app/CadastroPessoas-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar" , "/app/CadastroPessoas-0.0.1-SNAPSHOT.jar"]