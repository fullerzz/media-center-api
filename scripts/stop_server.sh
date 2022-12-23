#!/bin/bash

cd /opt/media-center
ps -ef | grep jar
pkill -9 java
echo Jar stopped
