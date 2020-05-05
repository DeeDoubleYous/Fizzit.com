#!/bin/sh

# Database information to be set.
DBNAME=DATABASE_NAME
DBUSER=DATABASE_USER
DBPASS=DATABASE_PASSWORD
DBURL=DATABASE_URL
DBPORT=DATABASE_PORT

echo Moving to parent directory 
cd ..

echo Downloading Glassfish Ver.5.1
curl -o glassfish.zip http://mirrors.xmission.com/eclipse/glassfish/glassfish-5.1.0.zip 

echo Unzipping Glassfish.zip 
unzip glassfish.zip 

echo Cleaning up the zip file
rm -r glassfish.zip 

echo Download MySql Connector 
curl -o mysql.zip https://cdn.mysql.com/archives/mysql-connector-java-8.0/mysql-connector-java-8.0.15.zip 

echo Unzipping mysql.zip 
unzip mysql.zip 

echo Move MySql Connector to Glassfish lib directory 
mv ./mysql-connector-java-8.0.15/mysql-connector-java-8.0.15.jar ./glassfish5/glassfish/domains/domain1/lib/ 

echo Cleaning up MySql files
rm -r mysql.zip 
rm -r mysql-connector-java-8.0.15

echo Start Glassfish server in order to configure connection pool 
./glassfish5/bin/asadmin start-domain 

echo Configure a connection pool on Glassfish for Fizzit
./glassfish5/bin/asadmin create-jdbc-connection-pool --ping --restype javax.sql.DataSource --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --property user=$DBUSER:password=$DBPASS:DatabaseName=$DBNAME:ServerName=$DBURL:port=$DBPORT:useSSL=false fizzit_pool

echo Configure JDBC resource jdbc/__default to use the new connection pool
./glassfish5/bin/asadmin set resources.jdbc-resource.jdbc/__default.pool-name=fizzit_pool

echo Stop Glassfish server 
./glassfish5/bin/asadmin stop-domain 

echo Moving back to Fizzit directory to compile WAR file 
cd ./Fizzit.com 

echo Using Maven to compile war file
mvn clean package 

echo Moving WAR file to Glassfish autodeploy folder 
cp ./target/Fizzit.com.war ../glassfish5/glassfish/domains/domain1/autodeploy 

echo Glassfish should be ready with Fizzit.com ready for deployment once the server is started again. 
echo To start the Glassfish server - use the command "glassfish5/bin/asadmin start-domain" 
echo When the Glassfish server has started then Fizzit.com should be deployed and viewable at http://localhost:8080/Fizzit.com 
echo Then to stop the server - use the command "glassfish5/bin/asadmin stop-domain"