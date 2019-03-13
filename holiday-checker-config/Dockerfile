FROM mysql:latest
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=user
ENV MYSQL_DATABASE=holiday_check
ADD /src/main/resources/db.sql /docker-entrypoint-initdb.d
EXPOSE 3306