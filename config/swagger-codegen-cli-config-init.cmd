@echo off

set swagger=..\docs
set workspace=..\workspace\eparkingrx
set project=swagger-codegen
set target=..\..\swagger-codegen\modules\swagger-codegen-cli\target

rmdir /S /Q %workspace%\SwaggerService
rmdir /S /Q %workspace%\ChargeFee
rmdir /S /Q %workspace%\CheckBalance
rmdir /S /Q %workspace%\DisableAccount
rmdir /S /Q %workspace%\EndParking
rmdir /S /Q %workspace%\RegisterAccount
rmdir /S /Q %workspace%\RegisterPayment
rmdir /S /Q %workspace%\RetrieveAccount
rmdir /S /Q %workspace%\SetRate
rmdir /S /Q %workspace%\StartParking
rmdir /S /Q %workspace%\ValidateAccount

java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\SwaggerService -Dapis -Dmodels -DsupportingFiles --artifact-id=SwaggerService --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\RegisterAccount -Dapis=RegisterAccount -Dmodels -DsupportingFiles --artifact-id=RegisterAccount --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\RetrieveAccount -Dapis=RetrieveAccount -Dmodels -DsupportingFiles --artifact-id=RetrieveAccount --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\DisableAccount -Dapis=DisableAccount -Dmodels -DsupportingFiles --artifact-id=DisableAccount --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\ValidateAccount -Dapis=ValidateAccount -Dmodels -DsupportingFiles --artifact-id=ValidateAccount --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\RegisterPayment -Dapis=RegisterPayment -Dmodels -DsupportingFiles --artifact-id=RegisterPayment --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\CheckBalance -Dapis=CheckBalance -Dmodels -DsupportingFiles --artifact-id=CheckBalance --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\ChargeFee -Dapis=ChargeFee -Dmodels -DsupportingFiles --artifact-id=ChargeFee --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\SetRate -Dapis=SetRate -Dmodels -DsupportingFiles --artifact-id=SetRate --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\StartParking -Dapis=StartParking -Dmodels -DsupportingFiles --artifact-id=StartParking --artifact-version=1.0.0
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\EndParking -Dapis=EndParking -Dmodels -DsupportingFiles --artifact-id=EndParking --artifact-version=1.0.0
