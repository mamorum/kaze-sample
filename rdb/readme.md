# Kaze Sample RDB
This is a [Kaze](https://github.com/mamorum/kaze) sample application accessing PostgreSQL.

## Dependencies for DB Access
- [HikariCP](https://github.com/brettwooldridge/HikariCP)
- [sql2o](https://github.com/aaberg/sql2o)
- [Flyway](https://github.com/flyway/flyway)

## Build System
- [Maven](https://maven.apache.org/)
- [Poml](https://github.com/mamorum/poml)

## DB Config
- [hikari.properties](src/main/resources/db/hikari.properties)

Default config accesses local database named `kaze`.


## Starting App
### Step1. Start DB
Start PostgreSQL.

### Step2. Clone
```
> git clone https://github.com/mamorum/kaze-sample.git
```

### Step3. Compile
```
> cd kaze-sample
> cd rdb
> mvn compile
```

### Step4. Run
```
> mvn exec:java -Dexec.mainClass=kaze.sample.rdb.Main
```

### Step5. Check App
Open `http://localhost:8080/`.
