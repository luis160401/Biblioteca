import java.util.Date;
import java.util.Scanner;

public class BibliotecaApp {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion;

        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    registrarLibro();
                    break;
                case 2:
                    actualizarLibro();
                    break;
                case 3:
                    eliminarLibro();
                    break;
                case 4:
                    registrarUsuario();
                    break;
                case 5:
                    actualizarUsuario();
                    break;
                case 6:
                    eliminarUsuario();
                    break;
                case 7:
                    realizarPrestamo();
                    break;
                case 8:
                    registrarDevolucion();
                    break;
                case 9:
                    generarReportePrestamos();
                    break;
                case 10:
                    mostrarEstadisticas();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("\nSistema de Gestión de Biblioteca");
        System.out.println("1. Registrar libro");
        System.out.println("2. Actualizar libro");
        System.out.println("3. Eliminar libro");
        System.out.println("4. Registrar usuario");
        System.out.println("5. Actualizar usuario");
        System.out.println("6. Eliminar usuario");
        System.out.println("7. Realizar préstamo");
        System.out.println("8. Registrar devolución");
        System.out.println("9. Generar reporte de préstamos");
        System.out.println("10. Mostrar estadísticas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void registrarLibro() {
        System.out.print("Ingrese ID del libro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("Ingrese cantidad disponible: ");
        int cantidad = scanner.nextInt();

        Libro libro = new Libro(id, titulo, autor, isbn, cantidad);
        biblioteca.registrarLibro(libro);
        System.out.println("Libro registrado exitosamente.");
    }

    private static void actualizarLibro() {
        System.out.print("Ingrese ID del libro a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nuevo título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese nuevo autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese nuevo ISBN del libro: ");
        String isbn = scanner.nextLine();
        System.out.print("Ingrese nueva cantidad disponible: ");
        int cantidad = scanner.nextInt();

        Libro libro = new Libro(id, titulo, autor, isbn, cantidad);
        biblioteca.actualizarLibro(libro);
        System.out.println("Libro actualizado exitosamente.");
    }

    private static void eliminarLibro() {
        System.out.print("Ingrese ID del libro a eliminar: ");
        int id = scanner.nextInt();
        biblioteca.eliminarLibro(id);
        System.out.println("Libro eliminado exitosamente.");
    }

    private static void registrarUsuario() {
        System.out.print("Ingrese ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese dirección del usuario: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese contacto del usuario: ");
        String contacto = scanner.nextLine();

        Usuario usuario = new Usuario(id, nombre, direccion, contacto);
        biblioteca.registrarUsuario(usuario);
        System.out.println("Usuario registrado exitosamente.");
    }

    private static void actualizarUsuario() {
        System.out.print("Ingrese ID del usuario a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese nuevo nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese nueva dirección del usuario: ");
        String direccion = scanner.nextLine();
        System.out.print("Ingrese nuevo contacto del usuario: ");
        String contacto = scanner.nextLine();

        Usuario usuario = new Usuario(id, nombre, direccion, contacto);
        biblioteca.actualizarUsuario(usuario);
        System.out.println("Usuario actualizado exitosamente.");
    }

    private static void eliminarUsuario() {
        System.out.print("Ingrese ID del usuario a eliminar: ");
        int id = scanner.nextInt();
        biblioteca.eliminarUsuario(id);
        System.out.println("Usuario eliminado exitosamente.");
    }

    private static void realizarPrestamo() {
        System.out.print("Ingrese ID del usuario: ");
        int idUsuario = scanner.nextInt();
        System.out.print("Ingrese ID del libro: ");
        int idLibro = scanner.nextInt();
        Date fechaPrestamo = new Date();
        System.out.print("Ingrese días de préstamo: ");
        int diasPrestamo = scanner.nextInt();
        Date fechaDevolucion = new Date(fechaPrestamo.getTime() + (long) diasPrestamo * 24 * 60 * 60 * 1000);

        biblioteca.realizarPrestamo(idUsuario, idLibro, fechaPrestamo, fechaDevolucion);
        System.out.println("Préstamo realizado exitosamente.");
    }

    private static void registrarDevolucion() {
        System.out.print("Ingrese ID del préstamo a devolver: ");
        int idPrestamo = scanner.nextInt();
        biblioteca.registrarDevolucion(idPrestamo);
        System.out.println("Devolución registrada exitosamente.");
    }

    private static void generarReportePrestamos() {
        System.out.println("Reporte de préstamos:");
        for (Prestamo prestamo : biblioteca.generarReportePrestamos()) {
            System.out.println("ID Préstamo: " + prestamo.getIdPrestamo() + ", ID Usuario: " + prestamo.getIdUsuario()
                    + ", ID Libro: " + prestamo.getIdLibro() + ", Fecha Préstamo: " + prestamo.getFechaPrestamo()
                    + ", Fecha Devolución: " + prestamo.getFechaDevolucion());
        }
    }

    private static void mostrarEstadisticas() {
        System.out.println("Estadísticas:");
        for (String estadistica : biblioteca.obtenerEstadisticas()) {
            System.out.println(estadistica);
        }
    }
}
