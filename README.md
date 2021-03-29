![Torneo de Frescas](tp.png?raw=true "Torneo de Frescas")


# Investigación de conceptos

## ¿Qué es Maven?

Maven es una herramienta de software para la gestión y construcción de proyectos Java creada por Jason van Zyl, de Sonatype, en 2002.

## POM: Definición y uso

POM son las siglas de "Project Object Model". Es una representación XML de un proyecto de Maven almacenado en un archivo llamado pom.xml.
Contiene contiene archivos de configuración, así como los desarrolladores involucrados y los roles que desempeñan, el sistema de seguimiento de defectos, la organización y las licencias, la URL de donde vive el proyecto, las dependencias del proyecto y todas las otras pequeñas piezas que entran en juego.

## Diferencia entre Archetype y ArfifacId

Archetype
Un Archetype es un patrón o modelo sobre el que se pueden desarrollar todas aquellas tareas que son de un mismo tipo mientras que un ArtifacId es una parte del Archetype e indica el nombre del mismo.

## Goals de Maven:

- **Clean**: Borra los archivos previamente compilados (.class) y los recursos del projecto (como los .propoerties).

- **Package**: Toma el código compilado y lo empaqueta en un formato para ser distribuido (como JAR).

- **Install**: Instala el package en el repositorio local, para ser usado como dependencia en otros projectos locales.

- **Compile**: Compila el código fuente del projecto.

## Scopes de Maven:

- **compile**: Es el scope predeterminado. Las dependencias con este scope están disponibles en todos los classpaths del projecto. Además, esas dependencias se propagan a projectos dependientes

- **provide**: Es parecido a **compile**. Las dependencias con este marcadas con este scope deberán ser provistas en tiempo de ejecución por el JDK o un contenedor.

- **runtime**: Las dependencias con este scope son requeridas en tiempo de ejecución pero no son necesarias para compilar el projecto

- **test**: Las dependencias con este scope no son requeridas para el uso normal de la aplicación, son sólo utilizadas para testear

- **system**: Es parecido a **provide**. La diferencia es que system requiere que le indiquemos la ruta a un jar en el sistema. Este scope está deprecado.