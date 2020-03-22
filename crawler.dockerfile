# crawler/core
# docker build --no-cache -t crawler/core -f  crawler.dockerfile .
# docker run --publish=8888:8888 iftm/service -port 8888 -config "{name:cabirch,optimizationInterval:0,ensemblerMode:false}" -flux_name project1 -influx_url "http://127.0.0.1:8086" -influx_user root -influx_pwd root
# curl -d "{\"x1\":1.0,\"x2\":2.0}" -H "Content-Type: application/json" localhost:8888/train
# curl -d "{\"x1\":1.0,\"x2\":2.0}" -H "Content-Type: application/json" localhost:8888/predict
# curl -d "{\"x1\":1.0,\"x2\":5.0}" -H "Content-Type: application/json" localhost:8888/predict
FROM maven:3.6-jdk-11 as build
WORKDIR /build
COPY . .
RUN mvn clean install
RUN cp /build/target/run-data-crawler-jar-with-dependencies.jar /build/news-crawler.jar

FROM openjdk:11-jre-slim
WORKDIR /
COPY --from=build /build/news-crawler.jar .
ENTRYPOINT ["java", "-jar", "news-crawler.jar"]