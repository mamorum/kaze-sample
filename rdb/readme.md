# Kaze Sample RDB
This is a [Kaze](https://github.com/mamorum/kaze) sample application accessing [H2 Database](http://www.h2database.com/html/main.html).

## Dependencies for DB Access
- [HikariCP](https://github.com/brettwooldridge/HikariCP)
- [sql2o](https://github.com/aaberg/sql2o)
- [Flyway](https://github.com/flyway/flyway)

## Build System
- [Maven](https://maven.apache.org/)
- [Poml](https://github.com/mamorum/poml)


## Starting App
### Step1. Clone
```
> git clone https://github.com/mamorum/kaze-sample.git
```

### Step2. Compile
```
> cd kaze-sample
> cd rdb
> mvn compile
```

### Step3. Run
```
> mvn exec:java -Dexec.mainClass=kaze.sample.rdb.Main
```

### Step4. Check App
We can check app, after opening `http://localhost:8080/`.


## H2 Database
### Console
We can open `http://localhost:8080/h2db`, after starting app. Login parameters are following.

- JDBC URL: jdbc:h2:~/h2db/memo
- User Name: sa

### Database files
Database files (`memo*.db`) are created in `~/h2db/`.
