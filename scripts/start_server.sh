#!/bin/bash

cd /opt/media-center
mvn verify
java -jar target/media-center-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null &