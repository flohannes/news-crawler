# crawler/video
# docker build --no-cache -t crawler/video -f  crawler-video.dockerfile .
# docker run localhost /home/pi/ 30 0
FROM maven:3.6-jdk-11 as build
WORKDIR /build
COPY . .
RUN mvn clean install
RUN cp /build/target/run-data-crawler-jar-with-dependencies.jar /build/news-crawler.jar

FROM arm32v7/openjdk:11-jre-slim
WORKDIR /
COPY --from=build /build/news-crawler.jar .
ENTRYPOINT ["java", "-cp", "news-crawler.jar", "fschmidt.data.crawler.CreateWordcloudMain"]