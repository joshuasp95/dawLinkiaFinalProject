#!/bin/bash

# Define el directorio del proyecto
PROJECT_DIR=~/dawLinkiaFinalProject

# Navega al directorio del proyecto
cd $PROJECT_DIR || exit

# Detén los contenedores actuales
docker-compose down

# Actualiza el código desde la rama main
git checkout main
git pull origin main

# Reconstruye y levanta los contenedores en segundo plano
docker-compose up --build -d

# Opcional: Limpiar imágenes y contenedores no utilizados
docker system prune -f
