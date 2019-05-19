@echo off

set swagger=..\docs
set workspace=..\workspace\eparkingrx
set project=swagger-codegen
set target=..\..\swagger-codegen\modules\swagger-codegen-cli\target

rmdir /S /Q %workspace%\RegisterAccount\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\RetrieveAccount\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\DisableAccount\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\UpdateAccount\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\RegisterPayment\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\CheckBalance\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\ChargeFee\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\SetRate\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\StartParking\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\EndParking\src\main\java\io\swagger\server\api\model

java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\RegisterAccount -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\RetrieveAccount -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\DisableAccount -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\UpdateAccount -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\RegisterPayment -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\CheckBalance -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\ChargeFee -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\SetRate -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\StartParking -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.yaml -o %workspace%\EndParking -Dmodels
