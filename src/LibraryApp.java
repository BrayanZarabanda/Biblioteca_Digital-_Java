import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.sena.app.models.Book;

public class LibraryApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        


        System.out.println("🚀 Biblioteca Digital - Versión 1.0"); 

        int option;
        do {
            mostrarMenu();
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                    crearLibro();
                    break;
                case 2:
                    leerLibros();
                    break;
                case 3:
                    actualizarLibro();;
                    break;
                case 4:
                    eliminarLibro();;
                    break;
                case 5:
                    buscarLibro();
                    break;

                case 6:
                    estadisticasBiblioteca();
                    break;
                case 7:
                    mostrarTablaLibros(library);
                    break;
                case 0:
                    System.out.println("¡Gracias por usar la biblioteca!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);

        scanner.close();
    }
private static void mostrarMenu() {
    System.out.println("\\n═══════════════════════════════════════");
    System.out.println("           BIBLIOTECA DIGITAL");
    System.out.println("═══════════════════════════════════════");
    System.out.println("1. ➕ Crear nuevo libro");
    System.out.println("2. 📚 Leer libros");
    System.out.println("3. ✏️ Actualizar libro");
    System.out.println("4. 🗑️ Eliminar libro");
    System.out.println("5. 🔍 Buscar libro");
    System.out.println("6. 📊 Estadísticas de la biblioteca");
    System.out.println("7. 📖 Mostrar tabla de libros");    
    System.out.println("0. 🚪 Salir");
    System.out.println("═══════════════════════════════════════");
    System.out.print("Seleccione una opción: ");
}


private static void crearLibro() {
    System.out.println("\\n--- ➕ CREAR NUEVO LIBRO ---");

    System.out.println("Ingrese el título del libro:");
    String title = scanner.nextLine();

    System.out.println(" fecha de edición (formato YYYY-MM-DD):");
    String edititionDate = scanner.nextLine();

    System.out.println("Editorial:");
    String editorial = scanner.nextLine();

    System.out.println("ISBN:");
    String isbn = scanner.nextLine();

    Book newBook = new Book(title, edititionDate, editorial, isbn);
    library.add(newBook);

    System.out.println("✅ Libro agregado exitosamente!");

    //-----------------------

    System.out.println("Autores (separados por comas):");
    String authorsInput = scanner.nextLine();
    String[] authors = authorsInput.split(",");
    for (String author : authors) {
        newBook.getAuthors().add(author.trim());
    }

    //--------------------------------------------

    System.out.print("¿Está leído? (true/false): ");
    boolean isReaded = scanner.nextBoolean();
    newBook.setReaded(isReaded);

    if (isReaded) {
        System.out.print("Horas de lectura: ");
        int timeReaded = scanner.nextInt();
        newBook.setTimeReaded(timeReaded);
    }
    //--------------------------------------------
}

// ...existing code...
private static void leerLibros() {
    System.out.println("\\n--- 📚 BIBLIOTECA COMPLETA ---");

    if (library.isEmpty()) {
        System.out.println("❌ No hay libros en la biblioteca.");
        return;
    }

    for (Book book : library) {
        System.out.println(book.toString());
    }
}

// ...existing code...
private static void mostrarTablaLibros(List<Book> libros) {
    System.out.println("┌──────────────────────────────────────┬─────────────┬──────────────────┬─────────────────┬──────────────────────────────┬────────┬─────────────┐");
    System.out.println("│ Título                               │ Fecha Ed.   │ Editorial        │ ISBN            │ Autores                      │ Leído  │ Hrs. Lectura │");
    System.out.println("├──────────────────────────────────────┼─────────────┼──────────────────┼─────────────────┼──────────────────────────────┼────────┼─────────────┤");

    for (Book book : libros) {
        System.out.printf("│ %-36s │ %-11s │ %-16s │ %-15s │ %-28s │ %-6s │ %-11s │%n",
                book.getTitle(),
                book.getEdititionDate(),
                book.getEditorial(),
                book.getIsbn(),
                String.join(", ", book.getAuthors()),
                book.isReaded() ? "Sí" : "No",
                book.getTimeReaded() > 0 ? book.getTimeReaded() + " hrs" : "N/A");
    }

    System.out.println("└──────────────────────────────────────┴─────────────┴──────────────────┴─────────────────┴──────────────────────────────┴────────┴─────────────┘");
}

//--------------------------

private static void mostrarLibrosConIndices(){
    System.out.println("📚 Libros disponibles:");
    for (int i = 0; i < library.size(); i++) {
        System.out.println((i + 1) + ". " + library.get(i).getTitle());
    }
}

private static void actualizarLibro() {
    System.out.println("\\n--- ✏️ ACTUALIZAR LIBRO ---");

    if (library.isEmpty()) {
        System.out.println("❌ No hay libros para actualizar.");
        return;
    }

    mostrarLibrosConIndices();

    System.out.print("Seleccione el número del libro a actualizar: ");
    int indice = scanner.nextInt();
    scanner.nextLine();

    if (indice < 1 || indice > library.size()) {
        System.out.println("❌ Índice no válido.");
        return;
    }

    Book ibro = library.get(indice - 1);
}
//------------------------------------
private static void eliminarLibro() {
    System.out.println("\\n--- 🗑️ ELIMINAR LIBRO ---");

    if (library.isEmpty()) {
        System.out.println("❌ No hay libros para eliminar.");
        return;
    }

    mostrarLibrosConIndices();

    System.out.print("Seleccione el número del libro a eliminar: ");
    int indice = scanner.nextInt();
    scanner.nextLine();

    if (indice < 1 || indice > library.size()) {
        System.out.println("❌ Índice no válido.");
        return;
    }

    Book libro = library.get(indice - 1);
    System.out.println("¿Está seguro de que desea eliminar el libro '" + libro.getTitle() + "'? (sí/no)");
    String confirmacion = scanner.nextLine();

    if (confirmacion.equalsIgnoreCase("s") || confirmacion.equalsIgnoreCase("si")) {
        library.remove(indice - 1);
        System.out.println("✅ Libro eliminado exitosamente!");
    } else {
        System.out.println("❌ Eliminación cancelada.");
    }
}

// --------------------------------------------------

private static void buscarLibro() {
    System.out.println("\\n--- 🔍 BUSCAR LIBRO ---");
    System.out.println("Buscar por:");
    System.out.println("1. Título");
    System.out.println("2. Autor");
    System.out.println("3. ISBN");
    System.out.print("Opción: ");

    int opcion = scanner.nextInt();
    scanner.nextLine();

    System.out.print("Término de búsqueda: ");

    String termino = scanner.nextLine().toLowerCase();

    List<Book> resultados = new ArrayList<>();

    for (Book libro : library) {
        switch (opcion) {
            case 1: // Título
                if (libro.getTitle().toLowerCase().contains(termino)) {
                    resultados.add(libro);
                }
                break;
            case 2: // Autor
                if (libro.getAuthors().stream().anyMatch(a -> a.toLowerCase().contains(termino))) {
                    resultados.add(libro);
                }
                break;
            case 3: // ISBN
                if (libro.getIsbn().toLowerCase().contains(termino)) {
                    resultados.add(libro);
                }
                break;
            default:
                System.out.println("❌ Opción no válida.");
                return;
        }
    }

    if (resultados.isEmpty()) {
        System.out.println("🔍 No se encontraron resultados.");
    } else {
        System.out.println("🔍 Resultados de búsqueda:");
        for (Book libro : resultados) {
            System.out.println(libro.toString());
        }
    }
}

// ...existing code...
private static void estadisticasBiblioteca() {
    System.out.println("\\n--- 📊 ESTADÍSTICAS DE LA BIBLIOTECA ---");

    if (library.isEmpty()) {
        System.out.println("❌ No hay libros en la biblioteca.");
        return;
    }

    int totalLibros = library.size();
    int librosLeidos = 0;
    int totalHoras = 0;

    for (Book libro : library) {
        if (libro.isReaded()) {
            librosLeidos++;
            totalHoras += libro.getTimeReaded();
        }
    }

    System.out.println("Total de libros: " + totalLibros);
    System.out.println("Libros leídos: " + librosLeidos);
    System.out.println("Horas totales de lectura: " + totalHoras);
    System.out.println("Promedio de horas por libro leído: " + (librosLeidos > 0 ? (double) totalHoras / librosLeidos : 0.0));
}

private static void initialize1Library() {
    Book book1 = new Book("Effective Java", "2018-01-01", "Addison-Wesley", "978-0134686097");
    book1.getAuthors().add("Joshua Bloch");
    book1.setReaded(true);
    book1.setTimeReaded(10);
    library.add(book1);

    Book book2 = new Book("Clean Code", "2008-08-01", "Prentice Hall", "978-0132350884");
    book2.getAuthors().add("Robert C. Martin");
    book2.setReaded(false);
    book2.setTimeReaded(0);
    library.add(book2);
}


}