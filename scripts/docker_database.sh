# Restore
docker exec -i docker_container mysql -uuser -ppassword database_name < dump.sql

# Backup
docker exec docker_container /usr/bin/mysqldump -uroot -ppassword database_name > dump.sql

sudo service docker status
sudo service docker start
sudo service docker restart
sudo service docker stop