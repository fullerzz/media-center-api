#!/bin/bash

cd ../ && mvn verify
touch temp.txt
java -jar target/media-center-0.0.1-SNAPSHOT.jar > /dev/null 2> /dev/null < /dev/null &