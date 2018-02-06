# Startup
1. bash ./src/main/resources/environment/start.postgres.sh
1. CREATE DATABASE blockhain_archive;
1. Execute ./src/main/resources/migrations/init.sql
1. mvn clean compile spring-boot:run
1. Go to http://localhost:8080
