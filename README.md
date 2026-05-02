# kotlin-card-shuffle

A tiny games program written in kotlin

## Design

The aim is to showcase how modern languages can express meaning and behavior both concisely and safely. This Kotlin program implements a type-safe card game in under 60 lines of code (around 100 lines including unit tests).
 

**Simplicity**

Succitness is used as a tool to simplify communicating the idea of the app to fellow programmers. Kotlin plays a big part here by providing functional programming primitives (used as a way to model a deck of cards for instance) but also documenting the code with a healthy amount of comments.

**Unit Tests**

With no external libraries and test runners supporting unit tests, I made a simple decision to make the test file executable and use intellij ide to build and run the unit test functions.
Why not junit??

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

## Future work
- update docs
- COMPLETED: learn more kotlin by implementing more card algorithms
- improve readability
- improved run. instead of using docker use something else
- document other interesting card algorithms
- can we make an executable java program?
