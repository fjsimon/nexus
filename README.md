# Nexus Microservice

#### Create database docker

Using the terminal run following command:

``
sudo docker-compose up
``

#### Startup & Build

Build the project using following command:

``
mvn clean install
`` 

#### mariadb profile, MariaDB database (requires running container)

Run the project using following command from the service folder:

``
spring-boot:run -Dspring-boot.run.profiles=mariadb 
``

Check that environment.properties file contains the right properties.


#### Setup

Set JAVA_HOME

Set M2_HOME

Add M2_HOME/bin to the execution path

mvn package -DskipTests


#### Docker Commands

##### Start MySql Container (downloads image if not found)
``
docker run  --detach   --name ec-name -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=database -e MYSQL_USER=user -e MYSQL_PASSWORD=password -d mysql
``

##### view all images
``
docker images
``

##### view all containers
``
docker ps -a
``
##### Interact with Database (link to ec-mysql container) with mysql client
``
docker run -it --link ec-mysql:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``
##### Stop ec-mysql container
``
docker stop ec-mysql
``
##### (ReStart) ec-mysql container
``
docker start ec-mysql
``
##### Remove ec-mysql container (must stop it first)
``
docker rm ec-mysql
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest
``