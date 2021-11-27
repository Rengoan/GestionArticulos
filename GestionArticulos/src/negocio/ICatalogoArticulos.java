package negocio;

import dominio.Articulo;

public interface ICatalogoArticulos {
    
    // Pre-Condición: No existe el fichero.
    // Post-Condición: Crear un nuevo recurso.
    String inicializarCatalogo(String nombreRecurso);

    void agregarArticulo(String nombreRecurso, Articulo articulo);
    
    String listarArticulos(String nombreRecurso);
    
    String buscarArticulo(String nombreRecurso, String termino);
    
    double calcularTotalPrecio(String nombreRecurso);
    
    int contadorArticulos(String nombreRecurso);
    
    double maxPrecioArticulo(String nombreRecurso);
    
    void borrarArticulo(String nombreRecurso, String nombreArticulo);
    
    String borrarCatalogo(String nombreRecurso);
    
    String mostrarArticuloPorId(String nombreRecurso, int id);
    
}
