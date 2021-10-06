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

Check that environment.properties contains the right properties.


#### Docker Commands

##### Start MySql Container (downloads image if not found)
``
docker run --detach --name container -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=database -e MYSQL_USER=user -e MYSQL_PASSWORD=password -d mysql
``

##### view all images
``
docker images
``

##### view all containers
``
docker ps -a
``
##### Interact with Database (link to container) with mysql client
``
docker run -it --link container:mysql --rm mysql sh -c 'exec mysql -h"$MYSQL_PORT_3306_TCP_ADDR" -P"$MYSQL_PORT_3306_TCP_PORT" -uroot -p"$MYSQL_ENV_MYSQL_ROOT_PASSWORD"'
``
##### Stop container container
``
docker stop container
``
##### (ReStart) container container
``
docker start container
``
##### Remove container (must stop it first)
``
docker rm container
``
##### Remove image (must stop and remove container first)
``
docker rmi mysql:latest
``