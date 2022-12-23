#!/bin/bash

cd /opt/media-center
ps -ef | grep jar
pkill -9 jar
