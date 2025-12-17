import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class GestorDeTarea {

    private Map<Integer, Tarea> gestor = new HashMap<>();
    private Integer numeroDeTareaActual = 1;
    private Integer totalDeTareas = 0;

    public void agregarTarea(String titulo, String descripcion, LocalDate vencimiento){
        Tarea tarea = new Tarea(titulo, descripcion, vencimiento);
        gestor.put(numeroDeTareaActual, tarea);
        numeroDeTareaActual++;
        totalDeTareas++;
    }

    public int cantidadDeTareas() {
        return gestor.size();
    }

    public int cantidadDeTareasNoVencidas() {
        return (int) gestor.values().stream().filter(tarea -> !tarea.estaVencida()).count();
    }

    public int cantidadDeTareasVencidas() {
        return  (int) gestor.values().stream().filter(Tarea::estaVencida).count();
        
    }
    
    public void eliminarTarea() {

    }

    public void eliminarTarea(int i) {
        gestor.remove(i);
        
    }

    public Tarea obtenerTarea(int i) {
        return gestor.get(i);
    }
    
    public String mostrarDatosDeTarea(int i) {
        Tarea tareaAMostrar = gestor.get(i);
        String mensaje = "id: "+i+"\n"+tareaAMostrar.mostrarDatos()+"\n";
        return mensaje;
    }

    public void modificarTituloDeTarea(int i, String tituloNuevo) {
        gestor.get(i).modificarTitulo(tituloNuevo);

    }

    public void modificarDescripcionDeTarea(int i, String descripcionNueva ) {
        gestor.get(i).modificarDescripcion(descripcionNueva);
    }

    public void modificarVencimientoDeTarea(int i, LocalDate vencimientoNuevo) {
        gestor.get(i).modificarVencimiento(vencimientoNuevo);
    }

    public String tituloDeTarea(int i) {
        return gestor.get(i).titulo();
    }

    public Object descripcionDeTarea(int i) {
        return gestor.get(i).descripcion();
    }

    public Object vencimientoDeTarea(int i) {
        return gestor.get(i).fechaDeVencimiento();
    }

    public void completarTarea(int i) {
        gestor.get(i).completar();
        
    }

    public String listarTareas() {
        String mensaje ="";
        for (Integer i : obtenerClavesOrdenadas()) {
            mensaje += mostrarDatosDeTarea(i);
        }        
        return mensaje;
        
    }

    private ArrayList<Integer> obtenerClavesOrdenadas(){
        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (Integer i : gestor.keySet()) {
            lista.add(i);
        }
        Collections.sort(lista);
        return lista;

    }
}