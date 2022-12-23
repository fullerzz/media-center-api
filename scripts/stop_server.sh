#!/bin/bash

cd /opt/media-center
ps -ef | grep java
sudo pkill -9 java
ps -ef | grep java
echo Jar stopped
