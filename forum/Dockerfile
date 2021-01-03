FROM azul/zulu-openjdk-alpine:11
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
RUN echo ${JAR_FILE}
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]