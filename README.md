# Сетевой чат
Программа состоит из двух модулей, которые могут быть запущены независимо друг от друга.  
Чтение порта производится из файла settings.txt который необходимо создать вручную в корне диске D: и прописать в нем необходимый порт для подключения.

## Модуль Server
Состоит из двух основных классов: это Server и CliendHandler, дополнительно присутствует вспомогательный класс Logger для ведения лога сообщений.

### Server
Запуск сервера производится путем создания объекта класса в методе Main. Сервер хранит список подключенных пользователей в ArrayList. При получении сообщения от одного из пользователей сервер в цикле перебирает список участников чата и отправляет сообщение каждому из них.

### ClientHandler
Каждый подключившийся к системе пользователь обратаывается в отдельном потоке, реализованном в классе ClientHandler. Этот класс первым сообщением принимает от пользователя его имя и записывает его в соответствующее поле класса, которое далее используется при получении сообщений от пользователя. Далее в цикле считываются сообщения клиента и отправляются другим участникам чата.

### Logger
Класс создаёт файл log.txt в корне диска D. Далее в файл записываются сообщения пользователей с указанием имени, даты и времени.

## Модуль Client
Модуль состоит из 2 классов.

### Client
Класс который подключается к серверу и отправляет на него сообщения. Приём сообщений от сервера реализован в отдельном потоке ServerListener.

### ServerListener
Отдельный поток, который прослушивает сервер.