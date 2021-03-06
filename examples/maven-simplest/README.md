# Vert.x Jersey Simplest Maven project

This project is the `vertx-jersey` version of the Vert.x Simplest Maven project found [here](https://github.com/vert-x3/vertx-examples/tree/master/maven-simplest).

This project shows a very simple hello world Vert.x Jersey project using Maven, which has a simple HTTP server which simply returns "Hello World!" to every request.

In this example Vert.x is used embedded. I.e. we use the Vert.x APIs directly in our own classes rather than deploying the code in verticles.

You can run or debug the example in your IDE by just right clicking the main class and run as.. or debug as…​

The pom.xml uses the Maven shade plugin to assemble the application and all it’s dependencies into a single "fat" jar.

To run with maven

```
mvn compile exec:java
```

To build a "fat jar"

```
mvn package
```

To run the fat jar:

```
java -jar target/maven-simplest-*-fat.jar
```

(You can take that jar and run it anywhere there is a Java 8+ JDK. It contains all the dependencies it needs so you don’t need to install Vert.x on the target machine).

Now point your browser at http://localhost:8080
