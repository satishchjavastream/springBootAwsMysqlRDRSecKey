FROM amazoncorretto:17-alpine-jdk

RUN mkdir -p /usr/app/

COPY ./springbootmysqlcrudexample.jar /usr/app/

WORKDIR /usr/app

#RUN pwd
#RUN ls
EXPOSE 9091
###RUN sh -c 'touch springbootmysqlcrudexample.jar'

CMD java -jar /usr/app/springbootmysqlcrudexample.jar