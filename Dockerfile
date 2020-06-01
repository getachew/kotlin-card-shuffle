FROM zenika/kotlin
WORKDIR /app
ADD . .
RUN kotlinc app.kt -include-runtime -d app.jar
CMD java -jar app.jar
