# Kaze Sample RDB
This is a [Kaze](https://github.com/mamorum/kaze) sample application accessing PostgreSQL.

## Dependencies for DB Access
- [HikariCP](https://github.com/brettwooldridge/HikariCP)
- [sql2o](https://github.com/aaberg/sql2o)
- [Flyway](https://github.com/flyway/flyway)

## Build System
- [Maven](https://maven.apache.org/)
- [Poml](https://github.com/mamorum/poml)


## Starting App
### Step1. Start DB
Start PostgreSQL.

### Step2. Clone App
```
> git clone https://github.com/mamorum/kaze-sample.git
```

### Step3. Run App
```
> cd kaze-sample
> cd rdb
> mvn compile
> mvn exec:java
```

### Step4. Checking
Open `http://localhost:8080/`.
