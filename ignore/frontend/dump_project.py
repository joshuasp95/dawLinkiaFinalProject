import os

# Ruta del proyecto
project_path = '/home/theguardian/GIT/dawLinkiaFinalProject/frontend'

# Definición de extensiones a excluir (ajustadas para un proyecto React)
excluded_extensions = (
    '.log',
    '.tmp',
    '.env',
    '.zip',
    '.tar.gz',
    '.jpg',
    '.jpeg',
    '.png',
    '.gif',
    'package-lock.json'
)

# Definición de directorios a excluir
excluded_directories = (
    'node_modules',  # Excluir la carpeta de dependencias
    'build',         # Excluir la carpeta de construcción si la hay
    '.git',          # Si quieres excluir el directorio .git también
)

# Archivo de salida
output_file = '/home/theguardian/GIT/dawLinkiaFinalProject/ignore/frontend/project_contents.txt'

with open(output_file, 'w', encoding='utf-8') as outfile:
    # Recorrer el directorio
    for root, dirs, files in os.walk(project_path):
        # Excluir directorios no deseados
        dirs[:] = [d for d in dirs if d not in excluded_directories]
        for file in files:
            # Excluir archivos que tengan las extensiones especificadas
            if not file.endswith(excluded_extensions):
                file_path = os.path.join(root, file)
                # Título del archivo
                outfile.write(f'--- Contenido de {file_path} ---\n')
                try:
                    with open(file_path, 'r', encoding='utf-8') as infile:
                        content = infile.read()
                        # Añadir el contenido del archivo
                        outfile.write(content + '\n\n')
                except Exception as e:
                    outfile.write(f'Error al leer {file_path}: {str(e)}\n\n')

print(f'Contenido de los archivos volcado en {output_file}')
