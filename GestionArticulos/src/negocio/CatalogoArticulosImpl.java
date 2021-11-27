package negocio;

import Excepciones.AccesoDatosExcepcion;
import Excepciones.LecturaDatosExcepcion;
import datos.*;
import dominio.Articulo;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CatalogoArticulosImpl implements ICatalogoArticulos{
    
    private final IAccesoDatos datos;

    public CatalogoArticulosImpl() {
        this.datos = new AccesoDatosFicheroImpl();
    }
    
    

    @Override
    public String inicializarCatalogo(String nombreRecurso) {
        if (this.datos.existeRecurso(nombreRecurso)){
            this.datos.borrarRecurso(nombreRecurso);
        }
        try {
            this.datos.crearRecurso(nombreRecurso);
        } catch (AccesoDatosExcepcion ex) {
            ex.printStackTrace(System.out);            
            System.out.println("Excepción intentando inicializar el catálogo");
        }
        return  "Catálogo de artículos inicializado";
    }

    @Override
    public void agregarArticulo(String nombreRecurso, Articulo articulo) {
        
    }

    @Override
    public String listarArticulos(String nombreRecurso) {
        
    }

    @Override
    public String buscarArticulo(String nombreRecurso, String termino) {
        
        try {
            this.datos.buscarArticuloSimple(nombreRecurso, termino);
        } catch (LecturaDatosExcepcion ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción leyendo el recurso.");
        }
        return "";
        
    }

    @Override
    public void borrarArticulo(String nombreRecurso, String nombreArticulo) {
        
        try {
            this.datos.borrarArticulo(nombreRecurso, nombreArticulo);
        } catch (AccesoDatosExcepcion ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción a la hora de borrar un artículo");
        }
        System.out.println("Articulo borrado con éxito");
    }

    @Override
    public String borrarCatalogo(String nombreRecurso) {
        this.datos.borrarRecurso(nombreRecurso);
        return "Catalogo borrado.";
    }
    
    @Override
    public double calcularTotalPrecio(String nombreRecurso) {
        List<Articulo> articulos = new ArrayList<>();
        double max = 0.0; 
        
        try {
            articulos = this.datos.listarRecurso(nombreRecurso);
            
            for (int i=0; i < articulos.size(); i++){
                max += articulos.get(i).getPrecio();
            }
            
            
        } catch (LecturaDatosExcepcion ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción listando el recurso");
        }
        
        return max;
    }

    @Override
    public int contadorArticulos(String nombreRecurso) {
        List<Articulo> articulos = new ArrayList<>();
        
        try {
            articulos = this.datos.listarRecurso(nombreRecurso);                     
        } catch (LecturaDatosExcepcion ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción listando el recurso");
        }
        
        return articulos.size();     
        
    }

    @Override
    public double maxPrecioArticulo(String nombreRecurso) {

        List<Articulo> articulos = new ArrayList<>();
        double max = 0.0; 
        
        try {
            articulos = this.datos.listarRecurso(nombreRecurso);
            
            for (int i=0; i < articulos.size(); i++){
                if (max < articulos.get(i).getPrecio()){
                    max = articulos.get(i).getPrecio();
                }                
            }
        } catch (LecturaDatosExcepcion ex) {
            ex.printStackTrace(System.out);
            System.out.println("Excepción listando el recurso");
        }
        
        return max;
        
    }
    
    @Override
    public String mostrarArticuloPorId(String nombreRecurso, int id){
       
        Articulo articulo = null;
        
        try {
            articulo = this.datos.buscarArticuloPorId(nombreRecurso, id);            
        } catch (LecturaDatosExcepcion ex) {
            ex.printStackTrace(System.out);
        }
        return articulo.toString();
    }
    
}
