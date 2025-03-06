/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.modulo3;

/**
 *
 * @author gerar
 */
import java.util.*;

// Clase Libro
class Libro {
    private String titulo;
    private String autor;
    private int anioPublicacion;
    private String genero;
    private boolean disponible;

    public Libro(String titulo, String autor, int anioPublicacion, String genero) {
        this.titulo = titulo;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.genero = genero;
        this.disponible = true;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean isDisponible() { return disponible; }

    public void prestar() {
        if (disponible) {
            disponible = false;
            System.out.println("Libro prestado con exito.");
        } else {
            System.out.println("El libro ya esta prestado.");
        }
    }

    public void devolver() {
        disponible = true;
        System.out.println("Libro devuelto con exito.");
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo + ", Autor: " + autor + ", Ano: " + anioPublicacion + ", Genero: " + genero + ", Disponible: " + (disponible ? "Si" : "No");
    }
}

// Clase Prestamo
class Prestamo {
    private String tituloLibro;
    private String usuario;
    private Date fechaPrestamo;

    public Prestamo(String tituloLibro, String usuario) {
        this.tituloLibro = tituloLibro;
        this.usuario = usuario;
        this.fechaPrestamo = new Date();
    }

    @Override
    public String toString() {
        return "Libro: " + tituloLibro + ", Usuario: " + usuario + ", Fecha: " + fechaPrestamo;
    }
}

// Clase Biblioteca
class Biblioteca {
    private List<Libro> libros;
    private List<Prestamo> historialPrestamos;

    public Biblioteca() {
        libros = new ArrayList<>();
        historialPrestamos = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        libros.add(libro);
        System.out.println("Libro agregado con exito.");
    }

    public void buscarPorTitulo(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println(libro);
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    public void buscarPorAutor(String autor) {
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                System.out.println(libro);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros de este autor.");
        }
    }

    public void prestarLibro(String titulo, String usuario) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                if (libro.isDisponible()) {
                    libro.prestar();
                    historialPrestamos.add(new Prestamo(titulo, usuario));
                    return;
                } else {
                    System.out.println("El libro ya esta prestado.");
                    return;
                }
            }
        }
        System.out.println("Libro no encontrado.");
    }

    public void devolverLibro(String titulo) {
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                libro.devolver();
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }

    public void mostrarLibrosDisponibles() {
        for (Libro libro : libros) {
            if (libro.isDisponible()) {
                System.out.println(libro);
            }
        }
    }

    public void mostrarHistorialPrestamos() {
        if (historialPrestamos.isEmpty()) {
            System.out.println("No hay prestamos registrados.");
        } else {
            for (Prestamo prestamo : historialPrestamos) {
                System.out.println(prestamo);
            }
        }
    }
}

// Clase Modulo3
public class Modulo3 {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Biblioteca Virtual ---");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar libro por titulo");
            System.out.println("3. Buscar libro por autor");
            System.out.println("4. Prestar libro");
            System.out.println("5. Devolver libro");
            System.out.println("6. Mostrar libros disponibles");
            System.out.println("7. Mostrar historial de prestamos");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese titulo: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese autor: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese ano de publicacion: ");
                    int anio = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Ingrese genero: ");
                    String genero = scanner.nextLine();
                    biblioteca.agregarLibro(new Libro(titulo, autor, anio, genero));
                    break;
                case 2:
                    System.out.print("Ingrese titulo: ");
                    biblioteca.buscarPorTitulo(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Ingrese autor: ");
                    biblioteca.buscarPorAutor(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Ingrese titulo: ");
                    String tituloPrestamo = scanner.nextLine();
                    System.out.print("Ingrese nombre del usuario: ");
                    String usuario = scanner.nextLine();
                    biblioteca.prestarLibro(tituloPrestamo, usuario);
                    break;
                case 5:
                    System.out.print("Ingrese titulo: ");
                    biblioteca.devolverLibro(scanner.nextLine());
                    break;
                case 6:
                    biblioteca.mostrarLibrosDisponibles();
                    break;
                case 7:
                    biblioteca.mostrarHistorialPrestamos();
                    break;
                case 8:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opcion no valida.");
            }
        }
    }
}

