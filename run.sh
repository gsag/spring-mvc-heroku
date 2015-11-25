#!/usr/bin/env bash
echo "==============================="
echo "Executando Servidor..."
echo "==============================="
echo ""
mvn clean install tomcat7:run-war -Dspring.profiles.active=development
