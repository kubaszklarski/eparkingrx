@echo off

set swagger=..\docs
set workspace=..\workspace\eparkingrx
set project=swagger-codegen
set target=..\..\swagger-codegen\modules\swagger-codegen-cli\target

mvn -f  %workspace%\RegisterAccount\pom.xml clean install
mvn -f  %workspace%\RetrieveAccount\pom.xml clean install
mvn -f  %workspace%\DisableAccount\pom.xml clean install
mvn -f  %workspace%\UpdateAccount\pom.xml clean install
mvn -f  %workspace%\RegisterPayment\pom.xml clean install
mvn -f  %workspace%\CheckBalance\pom.xml clean install
mvn -f  %workspace%\ChargeFee\pom.xml clean install
mvn -f  %workspace%\SetRate\pom.xml clean install
mvn -f  %workspace%\StartParking\pom.xml clean install
mvn -f  %workspace%\EndParking\pom.xml clean install
