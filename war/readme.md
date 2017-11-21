## Create War
```
> mvn package
```


## Run Embedded Tomcat
```
> mvn exec:java -Dexec.mainClass=kaze.sample.tomcat.Main -Dexec.classpathScope=compile
```


# Run Jetty Plugin
```
> mvn compile
> mvn jetty:run
```