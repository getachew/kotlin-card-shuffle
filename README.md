# kotlin-card-shuffle

A tiny program written in kotlin

## Design

The goal is to demonstrate the power of modern languages to succinctly and safely model meaning & process. This kotlin program models a type safe card game in less than 60 lines of code (100 lines of code including unit tests). 

**Simplicity**

Succitness is used as a tool to simplify communicating the idea of the app to fellow programmers. Kotlin plays a big part here by providing functional programming primitives (used as a way to model a deck of cards for instance) but also documenting the code with a healthy amount of comments.

**Unit Tests**

With no external libraries and test runners supporting unit tests, I made a simple decision to make the test file executable and use intellij ide to build and run the unit test functions.

**Build**

I am also demonstrating the idea of using newer technologies on tried and tested technology by leveraging Kotlin and docker to build and run code across platforms. 

## Running the program

If you have Kotlin installed, build the code into a jar by running:

`kotlinc app.kt -include-runtime -d app.jar`

If not, you can use docker to build the jar:

`docker container run -v `pwd`:/app --rm zenika/kotlin kotlinc /app/app.kt -include-runtime -d /app/app.jar`

Then execute the program by running: 

`java -jar app.jar`

## Using 