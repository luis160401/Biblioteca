import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Biblioteca {
    private List<Libro> libros;
    private List<Usuario> usuarios;
    private List<Prestamo> prestamos;

    public Biblioteca() {
        this.libros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.prestamos = new ArrayList<>();
    }

    // Métodos para gestionar libros
    public void registrarLibro(Libro libro) {
        libros.add(libro);
    }

    public void actualizarLibro(Libro libro) {
        for (Libro l : libros) {
            if (l.getIdLibro() == libro.getIdLibro()) {
                l.setTitulo(libro.getTitulo());
                l.setAutor(libro.getAutor());
                l.setISBN(libro.getISBN());
                l.setCantidadDisponible(libro.getCantidadDisponible());
            }
        }
    }

    public void eliminarLibro(int idLibro) {
        libros.removeIf(libro -> libro.getIdLibro() == idLibro);
    }

    // Métodos para gestionar usuarios
    public void registrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void actualizarUsuario(Usuario usuario) {
        for (Usuario u : usuarios) {
            if (u.getIdUsuario() == usuario.getIdUsuario()) {
                u.setNombre(usuario.getNombre());
                u.setDireccion(usuario.getDireccion());
                u.setContacto(usuario.getContacto());
            }
        }
    }

    public void eliminarUsuario(int idUsuario) {
        usuarios.removeIf(usuario -> usuario.getIdUsuario() == idUsuario);
    }

    // Métodos para realizar préstamos y devoluciones
    public void realizarPrestamo(int idUsuario, int idLibro, Date fechaPrestamo, Date fechaDevolucion) {
        Libro libro = buscarLibroPorId(idLibro);
        if (libro != null && libro.getCantidadDisponible() > 0 && contarPrestamosUsuario(idUsuario) < 5) {
            Prestamo prestamo = new Prestamo(prestamos.size() + 1, idUsuario, idLibro, fechaPrestamo, fechaDevolucion);
            prestamos.add(prestamo);
            libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
        }
    }

    public void registrarDevolucion(int idPrestamo) {
        Prestamo prestamo = buscarPrestamoPorId(idPrestamo);
        if (prestamo != null) {
            Libro libro = buscarLibroPorId(prestamo.getIdLibro());
            if (libro != null) {
                libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
                prestamos.remove(prestamo);
            }
        }
    }

    // Métodos auxiliares
    private Libro buscarLibroPorId(int idLibro) {
        for (Libro libro : libros) {
            if (libro.getIdLibro() == idLibro) {
                return libro;
            }
        }
        return null;
    }

    private Prestamo buscarPrestamoPorId(int idPrestamo) {
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdPrestamo() == idPrestamo) {
                return prestamo;
            }
        }
        return null;
    }

    private int contarPrestamosUsuario(int idUsuario) {
        int count = 0;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getIdUsuario() == idUsuario) {
                count++;
            }
        }
        return count;
    }

    // Métodos para generar reportes y estadísticas
    public List<Prestamo> generarReportePrestamos() {
        return new ArrayList<>(prestamos);
    }

    public List<String> obtenerEstadisticas() {
        List<String> estadisticas = new ArrayList<>();
        estadisticas.add("Libros más populares: " + obtenerLibrosMasPopulares());
        estadisticas.add("Usuarios más activos: " + obtenerUsuariosMasActivos());
        return estadisticas;
    }

    private String obtenerLibrosMasPopulares() {
        // Implementar lógica para obtener libros más populares
        return "Implementar lógica";
    }

    private String obtenerUsuariosMasActivos() {
        // Implementar lógica para obtener usuarios más activos
        return "Implementar lógica";
    }
}
