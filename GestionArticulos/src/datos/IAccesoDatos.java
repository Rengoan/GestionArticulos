package datos;

import Excepciones.AccesoDatosExcepcion;
import Excepciones.EscrituraDatosExcepcion;
import Excepciones.LecturaDatosExcepcion;
import dominio.Articulo;
import java.util.List;


public interface IAccesoDatos {
    
    boolean existeRecurso(String nombreRecurso);
    
    void crearRecurso(String nombreRecurso) throws AccesoDatosExcepcion;
    
    List<Articulo> listarRecurso(String nombreRecurso) throws LecturaDatosExcepcion;
    
    void agregarArticulo(Articulo articulo, String nombreRecurso) throws EscrituraDatosExcepcion;        
    
    int buscarArticuloSimple(String nombreRecurso, String termino) throws LecturaDatosExcepcion;
    
    Articulo buscarArticuloPorId(String nombreRecurso, int id) throws LecturaDatosExcepcion;
    
    void borrarArticulo(String nombreRecurso, String nombreArticulo) throws AccesoDatosExcepcion;
    
    String borrarRecurso(String nombreRecurso);    
    
}
