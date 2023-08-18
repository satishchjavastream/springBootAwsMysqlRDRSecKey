FROM amazoncorretto:17-alpine-jdk

RUN mkdir -p /usr/app/

COPY ./springbootmysqlcrudexamplesekey.jar /usr/app/

WORKDIR /usr/app

#RUN pwd
#RUN ls
EXPOSE 9091
###RUN sh -c 'touch springbootmysqlcrudexamplesekey.jar'

CMD java -jar /usr/app/springbootmysqlcrudexamplesekey.jar