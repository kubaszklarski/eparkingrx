ECHO OFF
CALL docker run -t -i -e JAVA_OPTS="-Dhazelcast.local.publicAddress=192.168.1.200:5701" -p 8080:8080 -p 5701:5701 -p 4001:4000 eparkingrx/clientverticle