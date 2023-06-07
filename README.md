# Simple-KKBOX

note: 

* **必須先啟動server，再啟動client，否則會無法連線。**
* **server與client必須在同一個網域和端口下，才能連線。**
* **斷開連線後，必須重新啟動server，才能再次連線。**

## Server use method

Start using bat: double click`start_server.bat`

```bat

javac Server_KKBOX.java
pause
java Server_KKBOX 9756 #9756為port number(可自行更改)
pause

```

## Client use method

Start using bat: double click`start_client.bat`

```bat
javac -classpath .;jaco-mp3-player-0.9.4.jar Client_KKBOX.java
pause
java -classpath .;jaco-mp3-player-0.9.4.jar Client_KKBOX 127.0.0.1 9756 #127.0.0.1為server ip(使用不同電腦連線時，可用ipconfig查詢並修改)，9756為port number(可自行更改)
pause
```

## Use method (connection screen)

**connection screen** (server screen)

![image](KKBOX\image\Server_Waiting_for_connection_screen.png)

**connection screen** (client screen)

![image](KKBOX\image\Client_Waiting_for_connection_screen.png)

## Use method (use command)

**command** (server screen)
![image](KKBOX\image\Server_command.png)

**command** (client screen)
![image](KKBOX\image\Client_command.png)