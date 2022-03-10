# Blackjack Online in Java
This project consists of a TCP JSON sever and a graphical client that connects to said server to play blackjack. 
The game is played online while connected to the server and multiple people can join the same session.
In addition, multiple games can run on the server simultaneously. The application features a basic ui to interface
with the server to make it easy to play blackjack this way.

## Running and compiling
The projekt can be run by using the provided jars or by compiling + running it with gradle.
There are gradle executables in both of the project folders, `BlackJackGame` & `BlackJack-Server`.

**Note: The server must be running otherwise the client application will crash if it fails to connect.**

#### Gradle commands in Windows
```shell
gradlew.bat <command>
```

#### Gradle commands in Linux/Unix
```shell
./gradlew <command>
```

### Useful gradle commands

#### compile and run
```shell
./gradlew run
```

#### compile to jar
```shell
./gradlew jar
```

#### run tests
```shell
./gradlew test
```

#### clean build files
```shell
./gradlew clean
```

#### generate JavaDocs
```shell
./gradlew javadoc
```
