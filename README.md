# Для запука автотестов необходимо

1. Установить (если нет) и запустить Docker Desktop
2. Запустить [docker-compose](https://github.com/Rita-Som666/diploma/blob/temp/docker-compose.yml) командой ```docker-compose up```
3. Запустить [jar-файл](https://github.com/Rita-Som666/diploma/blob/temp/artifacts/aqa-shop.jar) командой ```java -jar .\artifacts\aqa-shop.jar```
4. Запустить тесты командой ```./gradlew test```
5. (При необходимости) Сгенерировать отчёт командой ```./gradlew allureReport``` (Отчёт будет находиться в папке build\reports\allure-report\allureReport)  
