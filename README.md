<h1> Hola, este es mi proyecto LiterAlura </h1>

<svg width="100%" height="100">
  <rect width="100%" height="100%" fill="#FFD700" />
  <text x="50%" y="50%" dominant-baseline="middle" text-anchor="middle" fill="#000">
    ¡Bienvenido a mi README!
  </text>
</svg>

![image](https://github.com/user-attachments/assets/6ada741b-5c3c-47a7-9957-615d1966d74c)

<div style="background-color: #f0f0f0; padding: 10px; border-radius: 10px;">

**Descripción:**

El siguiente proyecto es la respuesta al Challenge LiterAlura, donde se desarrolló el back-end de lo que funcionaría como una base de datos de libros.  
El objetivo es crear un Catálogo de Libros que ofrezca interacción textual (vía consola) con los usuarios, proporcionando al menos 5 opciones de interacción. Los libros se buscarán a través de una API específica.

**Funcionalidades:**

<li> Menú interactivo en consola que permite al usuario seleccionar entre 5 opciones disponibles. </li>

<img width="1391" alt="image" src="https://github.com/user-attachments/assets/8795b7f7-9680-4f95-8b5e-013a25e23f86" />

<li>Consulta por título del libro en la API para obtener el primer resultado disponible y añadirlo a la base de datos.  
Los libros incluyen los siguientes atributos: Título, Autor, Idiomas y Número de Descargas. </li>

<img width="1361" alt="image" src="https://github.com/user-attachments/assets/0b40a7c0-dad5-45f2-81c1-b3b588690f52" />

Los datos se anexan a la base de datos de PostgreSQL tras realizar la búsqueda.  
<img width="1307" alt="image" src="https://github.com/user-attachments/assets/58f21346-ee9c-4d37-b28a-d84a4019a16f" />

<li>Listado de todos los libros:</li>
Esta funcionalidad permite presentar en la consola un listado de todos los libros buscados y almacenados.


<img width="1298" alt="image" src="https://github.com/user-attachments/assets/4ad94c6e-cc9b-43d4-978a-ecb5a28ef03f" />

![image](https://github.com/user-attachments/assets/a048a092-2a50-4c7b-9e49-56ea904e4828)


<li>Listado de autores:  
Cada libro incluye información relacionada con sus autores.  
Los datos del JSON incluyen una lista de autores por libro, con las siguientes características: Nombre, Año de Nacimiento y Año de Fallecimiento.</li>

<img width="1392" alt="image" src="https://github.com/user-attachments/assets/64116c3c-af9e-4ff5-bc53-c2abf9af977d" />

La información se añade a la base de datos de PostgreSQL desde la opción 1.  
<img width="1298" alt="image" src="https://github.com/user-attachments/assets/f707c2d0-5588-4180-a120-cda87339e748" />

<li>Consulta de autores vivos en un año específico:</li>

<img width="1388" alt="image" src="https://github.com/user-attachments/assets/616d9e31-b68c-487a-914f-a26616e15fa4" />

<li>Consulta de la cantidad de libros en un idioma específico:</li>

<img width="1390" alt="image" src="https://github.com/user-attachments/assets/ffb68acd-ece5-4f43-ac33-926537311775" />

</ol>

<h1> Datos adicionales para el funcionamiento: </h1>

![image](https://github.com/user-attachments/assets/353a5859-196b-482e-8009-c24e1f231c03)

Se deben tener todas las dependencias necesarias instaladas, crear la base de datos `literalura` en PostgreSQL y configurarla con las variables de entorno correspondientes.  
Es necesario usar la versión 17 de Java en el proyecto:

![image](https://github.com/user-attachments/assets/2dc6c01f-12d5-4a5c-aaa8-2ca713fda36e)

Además, el proyecto se puede descargar como un archivo ZIP, descomprimirlo y abrir la carpeta en el IDE IntelliJ. Tras realizar las configuraciones necesarias, debería conectarse sin problemas.

<img src="https://i.pinimg.com/originals/f8/c6/00/f8c60055f247f3da9e5c450f226a6446.gif" alt="">

<h1> ¡Con esto debería ser suficiente para que funcione, saludos! </h1>


Tecnologías utilizadas:

<p align="left">
  <img src="https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/IntelliJ%20IDEA-000000?style=for-the-badge&logo=intellij-idea&logoColor=white" alt="IntelliJ IDEA">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Gutendex%20API-00897B?style=for-the-badge&logo=readme&logoColor=white" alt="Gutendex API">
  <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white" alt="Postman">
  <img src="https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white" alt="Maven">
  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="GitHub">
  <img src="https://img.shields.io/badge/JSON-000000?style=for-the-badge&logo=json&logoColor=white" alt="JSON">
</p>


</div>
