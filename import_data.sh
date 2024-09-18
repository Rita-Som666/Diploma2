#!/bin/bash

# Задержка для ожидания старта MySQL сервиса
sleep 10

# Создайте таблицу и импортируйте данные из JSON файла
mysql -u$user -p$password $MYSQL_DATABASE -e "CREATE TABLE IF NOT EXISTS my_table (id INT AUTO_INCREMENT PRIMARY KEY, data JSON);"
mysql -u$user -p$password $MYSQL_DATABASE -e "LOAD DATA LOCAL INFILE '/docker-entrypoint-initdb.d/data.json' INTO TABLE my_table;"

echo "Data imported successfully."
