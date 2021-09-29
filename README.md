#Test task concord
## Требования:
  * Запрос и ответ в формате JSON.
  * Запрос, ответ и дату/время необходимо залогировать в отдельный файл.
  * Проект должен собираться в war, для установки на Tomcat.
  * Если в запросе содержится поле "id" со значением = 1, то ответ должен быть по примеру ниже, иначе ответ должен быть NULL.
  * Формат запроса POST.
  * Реализовать шифрование и дешифрование AES-256 входящего запроса и ответа. Эту часть необходимо только залогировать. Например (часть лога):
  * === encryption: sfdjnva9sfv87say9hdfow3
  * === decryption: {"fio": "Test Testov"}

 <hr>

 #### Пример запроса BODY: {"id": 1}
 #### Пример ответа BODY: {"fio": "Test Testov"}
 
 <hr>
 
 ##### Рекомендуется использовать Maven, Spring (Rest Controller), Log4j. Остальное на усмотрение разработчика. Желательно выполнить максимальное количество требований :)
 
 <hr>
 
 ### Для пользователей:  
    * В application.properties подключаем свою БД: 
     - spring.datasource.url=jdbc:postgresql://localhost:5432/******
     - spring.datasource.username=YOUR NAME
     - spring.datasource.password=YOUR PASSWORD
     - logging.file.path= YOUR PATH TO FOLDER
     
    * Отправляем POST запрос на URL: {your localhost}/api/user/find-user-by-id
    
    