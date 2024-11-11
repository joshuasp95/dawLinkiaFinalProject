# Etapa de construcción
FROM node:18-alpine AS builder

# Establecer el entorno de producción
ENV NODE_ENV production

# Establece la variable de entorno
ARG REACT_APP_API_URL
ENV REACT_APP_API_URL $REACT_APP_API_URL

# Después de establecer ENV REACT_APP_API_URL
RUN echo "REACT_APP_API_URL is set to $REACT_APP_API_URL"

# Directorio de trabajo
WORKDIR /app

# Copiar package.json y package-lock.json
COPY package*.json ./

# Instalar dependencias
RUN npm install

# Copiar el resto de la aplicación
COPY . .

# Construir la aplicación
RUN npm run build

# Etapa de producción
FROM nginx:alpine

# Copiar los archivos construidos desde la etapa de construcción
COPY --from=builder /app/build /usr/share/nginx/html

# Copiar el archivo de configuración de Nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Exponer el puerto 80
EXPOSE 80

# Comando para ejecutar nginx
CMD ["nginx", "-g", "daemon off;"]
