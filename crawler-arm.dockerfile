# crawler/core
# docker build --no-cache -t crawler/core -f  crawler.dockerfile .
# docker run localhost 720
FROM maven:3.6-jdk-11 as build
WORKDIR /build
COPY . .
RUN mvn clean install
RUN cp /build/target/run-data-crawler-jar-with-dependencies.jar /build/news-crawler.jar

FROM arm32v7/openjdk:11-jre-slim
WORKDIR /
COPY --from=build /build/news-crawler.jar .
ENTRYPOINT ["java", "-jar", "news-crawler.jar"]