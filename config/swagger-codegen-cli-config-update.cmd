@echo off

set swagger=..\docs
set workspace=..\workspace\eparkingrx
set project=swagger-codegen
set target=..\..\swagger-codegen\modules\swagger-codegen-cli\target

rmdir /S /Q %workspace%\RegisterAccount\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\RetrieveAccount\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\DisableAccount\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\RegisterPayment\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\CheckBalance\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\ChargeFee\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\SetRate\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\StartParking\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\EndParking\src\main\java\io\swagger\server\api\model
rmdir /S /Q %workspace%\ValidateAccount\src\main\java\io\swagger\server\api\model

java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\RegisterAccount -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\RetrieveAccount -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\DisableAccount -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\ValidateAccount -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\RegisterPayment -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\CheckBalance -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\ChargeFee -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\SetRate -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\StartParking -Dmodels
java -jar %target%\swagger-codegen-cli.jar generate -l java-vertx -c swagger-codegen-cli-config.json -i %swagger%\swagger.json -o %workspace%\EndParking -Dmodels
