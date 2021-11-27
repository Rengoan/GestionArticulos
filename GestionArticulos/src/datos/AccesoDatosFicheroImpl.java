package datos;

import dominio.Articulo;
import Excepciones.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AccesoDatosFicheroImpl implements IAccesoDatos{

    @Override
    public boolean existeRecurso(String nombreRecurso) {
      File archivo = new File(nombreRecurso);
     
      return archivo.exists();
      
    }

    @Override
    public void crearRecurso(String nombreRecurso) throws AccesoDatosExcepcion {
        File archivo = new File(nombreRecurso);
        
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoDatosExcepcion("Excepción intentando crear el archivo");
        }
        
    }

    @Override
    public List<Articulo> listarRecurso(String nombreRecurso) throws LecturaDatosExcepcion {
        File archivo = new File(nombreRecurso);
        
        Articulo articuloN = null;
        String[] articulo = new String[5]; 
        List<Articulo> articulos = new ArrayList<>();
        
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = null;
            while((lectura = entrada.readLine()) != null){
                // lectura = "idArticulo;nombre;descripcion;precio;fecha"
                
                articulo = lectura.split(";"); // {idArticulo, nombre, descripcion, precio, fecha}
                articuloN = new Articulo(Integer.parseInt(articulo[0]), 
                        articulo[1], articulo[2], 
                        Double.parseDouble(articulo[3]), formatoFecha.parse(articulo[4]));
                articulos.add(articuloN);
            }
            entrada.close();            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
            
        } catch (IOException ex){
            ex.printStackTrace(System.out);
            throw new LecturaDatosExcepcion("Excepción leyendo el fichero...");            
        } catch (ParseException ex){
            ex.printStackTrace(System.out);
        }
        return articulos;                
    }

    @Override
    public void agregarArticulo(Articulo articulo, String nombreRecurso) 
            throws EscrituraDatosExcepcion {        
        File archivo = new File(nombreRecurso);
        
        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo, true));
            String articuloTxt = articulo.getIdArticulo() + ";" +
                    articulo.getNombre() + ";" + articulo.getDescripcion() + ";" +
                    articulo.getPrecio() + ";" + articulo.getFecha();
            salida.println(articuloTxt);
            salida.close();
            // Formato de cada linea del fichero es: idArticulo;nombre;descripcion;precio;fecha
            // sin embargo tengo un Objeto Articulo
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new EscrituraDatosExcepcion("Excepción escribiendo un nuevo "
                    + "artículo en el Recurso");
        }
        
    }

    // 1. Busqueda en todos los campos.
    // 2. Búsqueda especificando el campo por el que se quiere buscar.
    // 3. Búsqueda que contenga el termino.
    @Override
    public int buscarArticuloSimple(String nombreRecurso, String termino) throws LecturaDatosExcepcion {
        File archivo = new File(nombreRecurso);
        String[] articulo = new String[5];
        int contador = 0;
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = null;
            
            while((lectura = entrada.readLine()) != null){
                articulo = lectura.split(";"); // {idArticulo(0), nombre(1), descripcion(2), precio(3), fecha(4)}
                if (termino.equalsIgnoreCase(articulo[1])){ 
                    break;
                }
                contador++;
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex){
            ex.printStackTrace(System.out);
            throw new LecturaDatosExcepcion("Excepcion leyendo el recurso al "
                    + "buscar el articulo con nombre " + termino);
        }
        return contador;
    }

    @Override
    public Articulo buscarArticuloPorId(String nombreRecurso, int id) throws LecturaDatosExcepcion{
        
        File archivo = new File(nombreRecurso);
        String[] articuloTxt = new String[5];
        Articulo articulo = null;
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
                
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = null;
            
            while((lectura = entrada.readLine()) != null){
                articuloTxt = lectura.split(";"); // {idArticulo(0), nombre(1), descripcion(2), precio(3), fecha(4)}
                if (id == Integer.parseInt(articuloTxt[0])){ 
                    articulo = new Articulo(Integer.parseInt(articuloTxt[0]), 
                        articuloTxt[1], articuloTxt[2], 
                        Double.parseDouble(articuloTxt[3]), 
                        formatoFecha.parse(articuloTxt[4])); 
                    break;
                }
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex){
            ex.printStackTrace(System.out);
            throw new LecturaDatosExcepcion("Excepcion leyendo el recurso al "
                    + "buscar el articulo con nombre " + id);
        } catch (ParseException ex){
            ex.printStackTrace(System.out);
        }
        return articulo;
    }

    @Override
    public void borrarArticulo(String nombreRecurso, String nombreArticulo) throws AccesoDatosExcepcion {
        
        File archivoOri = new File(nombreRecurso);
        File archivoBackup = new File("temp.txt");
        
        String[] articulo = new String[5];
        
        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivoOri));
            PrintWriter salida = new PrintWriter(new FileWriter(archivoBackup));            
            String lectura = null; 
            
            while((lectura = entrada.readLine()) != null){
            
                articulo = lectura.split(";");
                if (articulo[1] != nombreArticulo){
                    salida.println(articulo);
                }                
                
            }
            entrada.close();
            salida.close();
            
            if (existeRecurso(nombreRecurso)){
                borrarRecurso(nombreRecurso);
            }
            
            archivoBackup.renameTo(archivoOri);
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex){
            ex.printStackTrace(System.out);
        }
        
    }

    @Override
    public String borrarRecurso(String nombreRecurso) {
        File archivo = new File(nombreRecurso);
        String msg = "";
        if (existeRecurso(nombreRecurso)){
            archivo.delete();
            msg = "Recurso borrado con éxito";
        }
        else {
            msg = "No se ha podido borrar el archivo ya que no existe";
        }
        return msg;
    }
    
}
