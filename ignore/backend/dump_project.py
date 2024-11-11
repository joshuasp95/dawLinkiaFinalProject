import os

# Ruta del proyecto
project_path = '/home/theguardian/GIT/dawLinkiaFinalProject/backend/dawFinalProject'

# Definición de extensiones a excluir
excluded_extensions = (
    '.class',
    '.jar',
    '.war',
    '.zip',
    '.tar.gz',
    '.env',
    '.log',
    '.tmp',
    '.cmd',
    'mvnw'
)

# Definición de directorios a excluir
excluded_directories = (
    'target',
    'generated-sources',
    'generated-test-sources',
    '.git',  # Si quieres excluir el directorio .git también
    '.mvn',  # Para excluir el directorio de Maven
)


# Archivo de salida
output_file = '/home/theguardian/GIT/dawLinkiaFinalProject/ignore/backend/project_contents.txt'

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
