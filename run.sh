#!/bin/bash
export JAVA_HOME=/home/vscode/.jdk/jdk-21.0.8
export PATH=$JAVA_HOME/bin:$PATH
./gradlew bootRun
