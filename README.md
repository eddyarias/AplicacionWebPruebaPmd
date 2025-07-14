# AplicacionWebPruebaPmd

Una aplicación web Java simple para demostrar fallos de seguridad detectables por PMD.

## Descripción
Esta es una aplicación web simple desarrollada en Java que permite gestionar mensajes con funcionalidad de encriptación. La aplicación está **deliberadamente diseñada** para contener fallos de seguridad específicos que serán detectados por las reglas de PMD.

## Fallos de Seguridad Incluidos

### 1. HardCodedCryptoKey
- **Ubicación**: `ServicioEncriptacion.java`
- **Problema**: Clave de encriptación codificada directamente en el código fuente
- **Línea**: `private static final String CLAVE_SECRETA = "MiClaveSecreta123456";`

### 2. InsecureCryptoIv
- **Ubicación**: `ServicioEncriptacion.java`
- **Problema**: Vector de inicialización (IV) codificado directamente y fijo
- **Línea**: Array `IV_FIJO` con valores hardcodeados

## Funcionalidades
- ✅ Agregar mensajes de texto
- ✅ Encriptar mensajes usando AES
- ✅ Mostrar mensajes encriptados y desencriptados
- ⚠️ Contiene fallos de seguridad deliberados

## Tecnologías Utilizadas
- Java 11
- Maven
- Servlets & JSP
- Bootstrap CSS (básico)
- PMD para análisis estático

## Cómo Ejecutar

### Prerrequisitos
- JDK 11 o superior
- Maven 3.6+
- Servidor web (Tomcat, Jetty, etc.)

### Compilación
```bash
mvnd clean compile
```

### Generar WAR
```bash
mvnd clean package
```

### Ejecutar WAR
```bash
mvnd tomcat7:run
```

## Estructura del Proyecto
```
src/
├── main/
│   ├── java/
│   │   └── com/ejemplo/mensajes/
│   │       ├── ServicioEncriptacion.java  (⚠️ Contiene fallos)
│   │       ├── Mensaje.java
│   │       └── MensajesServlet.java
│   └── webapp/
│       ├── WEB-INF/
│       │   ├── views/
│       │   │   └── mensajes.jsp
│       │   └── web.xml
│       └── index.html
└── pom.xml
```

## ⚠️ Advertencia de Seguridad
Esta aplicación contiene vulnerabilidades de seguridad **DELIBERADAS** para fines educativos y de testing con PMD. 

**NO USAR EN PRODUCCIÓN**