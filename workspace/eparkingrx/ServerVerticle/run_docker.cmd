ECHO OFF
CALL docker run -t -i -e JAVA_OPTS="-Dhazelcast.local.publicAddress=192.168.1.200:5702" -p 5702:5701 -p 4002:4000 eparkingrx/serververticle