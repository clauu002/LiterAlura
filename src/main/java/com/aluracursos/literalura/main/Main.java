package com.aluracursos.literalura.main;

import com.aluracursos.literalura.models.Author;
import com.aluracursos.literalura.models.DatosLibro;
import com.aluracursos.literalura.models.Libro;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.services.ConvierteDatos;
import com.aluracursos.literalura.services.RequestAPI;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private RequestAPI requestAPI = new RequestAPI();
    private Scanner scanner = new Scanner(System.in);
    private String urlBase = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AuthorRepository authorRepository;
    private List<Libro> libros;
    private List<Author> autores;

    public Main(LibroRepository libroRepository, AuthorRepository authorRepository) {
        this.libroRepository = libroRepository;
        this.authorRepository = authorRepository;
    }

    // Mostrar el menu en consola
    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ----------------------------------------------------------------
                        Aplicacion de ***LiterAlura*** - Busca Libros y Autores por fecha ☆
                    ----------------------------------------------------------------
                    
                    Selecciona la opcion de tu preferencia: 
                    
                    1 - Buscar libro por nombre
                    2 - Historial de libros buscados
                    3 - Por nombre de Autor
                    4 - Escribe el año en que vivian los autores
                    5 - Libros por idioma
                    
                    0 - Salir               
                    """;

            try {
                System.out.println(menu);
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {

                System.out.println("Ingresa una opcion valida");
            }

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    consultarLibros();
                    break;
                case 3:
                    consultarAutores();
                    break;
                case 4:
                    consultarAutoresPorAno();
                    break;
                case 5:
                    consultarLibrosLenguaje();
                    break;
                case 0:
                    System.out.println("Hasta luego");
                    break;
                default:
                    System.out.println("Ingresa una opcion valida");
            }
        }
    }

    // Extrae los datos de un libro
    private DatosLibro getDatosLibro() {
        System.out.println("Ingrese el nombre del libro");
        var busqueda = scanner.nextLine().toLowerCase().replace(" ", "%20");
        var json = requestAPI.getData(urlBase +
                "?search=" +
                busqueda);

        DatosLibro datosLibro = convierteDatos.obtenerDatos(json, DatosLibro.class);
        return datosLibro;
    }

    // Busca un libro y guarda infromacion en la BD en sus tablas correspondientes
    private void buscarLibro() {
        DatosLibro datosLibro = getDatosLibro();

        try {
            // Obtener el primer libro y autor de los resultados
            Libro libro = new Libro(datosLibro.resultados().get(0));
            Author author = new Author(datosLibro.resultados().get(0).autorList().get(0));

            // Verificar si el libro ya está registrado en la base de datos
            Libro libroExistente = libroRepository.findByTitulo(libro.getTitulo());
            if (libroExistente != null) {
                // Si el libro ya existe, lanzar un error y detener el proceso
                System.out.println("Usted ya buscó este libro, no se puede registrar dos veces en la base de datos.");
                return; // Detener la ejecución y no registrar el libro ni el autor
            }

            // Si el libro no existe, continuar con el registro
            System.out.println("""
                libro[
                    titulo: %s
                    autor: %s
                    lenguaje: %s
                    descargas: %s
                ]
                """.formatted(libro.getTitulo(),
                    libro.getAutor(),
                    libro.getLenguaje(),
                    libro.getDescargas().toString()));

            // Guardar el libro y el autor en la base de datos
            libroRepository.save(libro);
            authorRepository.save(author);

        } catch (Exception e) {
            System.out.println("No se encontró ese libro.");
        }
    }


    // Trae los libros guardados en la BD
    private void consultarLibros() {
        libros = libroRepository.findAll();
        libros.stream().forEach(l -> {
            System.out.println("""    
                        Titulo: %s
                        Author: %s
                        Lenguaje: %s
                        Descargas: %s
                    """.formatted(l.getTitulo(),
                    l.getAutor(),
                    l.getLenguaje(),
                    l.getDescargas().toString()));
        });
    }

    // Trae todos los autores de los libros consultados en la BD
// Trae todos los autores de los libros consultados en la BD
    private void consultarAutores() {
        try {
            autores = authorRepository.findAll();

            // Verificar si hay autores en la base de datos
            if (autores.isEmpty()) {
                System.out.println("No se encontraron autores en la base de datos.");
            } else {
                autores.forEach(a -> {
                    // Verificar si los datos de los autores son nulos
                    String autorNombre = a.getAutor() != null ? a.getAutor() : "Desconocido";
                    String nacimiento = a.getNacimiento() != null ? a.getNacimiento().toString() : "Fecha no disponible";
                    String defuncion = a.getDefuncion() != null ? a.getDefuncion().toString() : "Fecha no disponible";

                    System.out.println("""
                                Autor: %s
                                Año de nacimiento: %s
                                Año de fallecimiento: %s
                            """.formatted(autorNombre, nacimiento, defuncion));
                });
            }
        } catch (Exception e) {
            System.out.println("Ocurrió un error al consultar los autores: " + e.getMessage());
        }
    }


    // Trae a los autores apartir de cierto año
    public void consultarAutoresPorAno() {
        System.out.println("Ingresa el año a partir del cual buscar:");
        var anoBusqueda = scanner.nextInt();
        scanner.nextLine();

        List<Author> authors = authorRepository.autorPorFecha(anoBusqueda);
        authors.forEach(a -> {
            System.out.println("""
                    Nombre: %s
                    Fecha de nacimiento: %s
                    Fecha de defuncion: %s
                    """.formatted(a.getAutor(), a.getNacimiento().toString(), a.getDefuncion().toString()));
        });
    }


    private void consultarLibrosLenguaje() {
        System.out.println("""
                ****************************************************************    
                    Selecciona el lenguaje de los libros que deseas consultar
                ****************************************************************
                1 - Inglés
                2 - Español
                3 - Francés
                4 - Alemán
                """);

        try {
            var opcion2 = scanner.nextInt();
            scanner.nextLine();

            switch (opcion2) {
                case 1:
                    libros = libroRepository.findByLenguaje("en");
                    break;
                case 2:
                    libros = libroRepository.findByLenguaje("es");
                    break;
                case 3:
                    libros = libroRepository.findByLenguaje("fr");
                    break;
                case 4:
                    libros = libroRepository.findByLenguaje("de");
                    break;
                default:
                    System.out.println("Ingresa una opción válida");
                    return; // Salir del método si se ingresa una opción inválida
            }

            if (libros.isEmpty()) {
                System.out.println("No se encontraron libros para el idioma seleccionado.");
            } else {
                libros.forEach(l -> {
                    System.out.println("""
                                Titulo: %s
                                Autor: %s
                                Lenguaje: %s
                                Descargas: %s
                            """.formatted(l.getTitulo(),
                            l.getAutor(),
                            l.getLenguaje(),
                            l.getDescargas().toString()));
                });
            }

        } catch (Exception e) {
            System.out.println("Ingresa un valor válido");
        }
    }

}

