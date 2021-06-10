#!/bin/bash
cd /tmp || exit
echo "Downloading sonar-scanner....."
if [ -d "/tmp/sonar-scanner-cli-4.4.0.2170-linux.zip" ];then
    sudo rm /tmp/sonar-scanner-cli-4.4.0.2170-linux.zip
fi
wget -q https://binaries.sonarsource.com/Distribution/sonar-scanner-cli/sonar-scanner-cli-4.4.0.2170-linux.zip
echo "Download completed."
echo "Unziping downloaded file..."
unzip sonar-scanner-cli-4.4.0.2170-linux.zip
echo "Unzip completed."
rm sonar-scanner-cli-4.4.0.2170-linux.zip
echo "Installing to opt..."
if [ -d "/opt/sonar-scanner-4.4.0.2170-linux" ];then
    sudo rm -rf /opt/sonar-scanner-4.4.0.2170-linux
fi
sudo mv sonar-scanner-4.4.0.2170-linux /opt
echo "Installation completed successfully."
echo "You can use sonar-scanner!"