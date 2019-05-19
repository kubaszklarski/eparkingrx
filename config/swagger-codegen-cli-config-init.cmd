@echo off

set swagger=..\docs
set workspace=..\workspace\eparkingrx
set project=swagger-codegen
set target=..\..\swagger-codegen\modules\swagger-codegen-cli\target

rmdir /S /Q %workspace%\RegisterAccount
rmdir /S /Q %workspace%\RetrieveAccount
rmdir /S /Q %workspace%\DisableAccount
rmdir /S /Q %workspace%\UpdateAccount
rmdir /S /Q %workspace%\RegisterPayment
rmdir /S /Q %workspace%\CheckBalance
rmdir /S /Q %workspace%\ChargeFee
rmdir /S /Q %workspace%\SetRate
rmdir /S /Q %workspace%\StartParking
rmdir /S /Q %workspace%\EndParking

java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\RegisterAccount -Dapis=RegisterAccount -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\RetrieveAccount -Dapis=RetrieveAccount -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\DisableAccount -Dapis=DisableAccount -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\UpdateAccount -Dapis=UpdateAccount -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\RegisterPayment -Dapis=RegisterPayment -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\CheckBalance -Dapis=CheckBalance -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\ChargeFee -Dapis=ChargeFee -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\SetRate -Dapis=SetRate -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\StartParking -Dapis=StartParking -Dmodels -DsupportingFiles
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\EndParking -Dapis=EndParking -Dmodels -DsupportingFiles
