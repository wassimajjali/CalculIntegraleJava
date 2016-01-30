#!/bin/bash
sudo apt-get install openjdk-7-jre

java -cp .:./*:./ext/* -Xmx100m -Xms80m integrale.UseJFrame