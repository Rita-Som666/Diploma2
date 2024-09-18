FROM mysql:8.0

# Установите необходимые пакеты
RUN apt-get update && apt-get install -y mysql-client

# Копируйте скрипт в контейнер
COPY import_data.sh /docker-entrypoint-initdb.d/

# Дайте разрешения на выполнение скрипта
RUN chmod +x /docker-entrypoint-initdb.d/import_data.sh
