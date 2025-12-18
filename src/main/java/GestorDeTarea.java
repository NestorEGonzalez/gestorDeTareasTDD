import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class GestorDeTarea {

    private Map<Integer, Tarea> gestor = new HashMap<>();
    private Integer numeroDeTareaActual = 1;
    private static final String DATOS_TAREA = "id: %d%n%s%n";
    private static final String ERROR_TAREA_INEXISTENTE = "La tarea soliitada no existe.";

    public void agregarTarea(String titulo, String descripcion, LocalDate vencimiento){
        Tarea tarea = new Tarea(titulo, descripcion, vencimiento);
        gestor.put(numeroDeTareaActual, tarea);
        numeroDeTareaActual++;

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
    
    public void eliminarTarea(int i) {
        comprobarTareaExistente(i);
        gestor.remove(i);
        
    }

    public Tarea obtenerTarea(int i) {
       comprobarTareaExistente(i);
       return gestor.get(i);
    }

    private void comprobarTareaExistente(Integer i){
        if (gestor.get(i) == null) {
            throw new IllegalArgumentException(ERROR_TAREA_INEXISTENTE);
            
        }
    }

    
    public String mostrarDatosDeTarea(int i) {
        return String.format(DATOS_TAREA,i,obtenerTarea(i).mostrarDatos());
    }


    public void modificarTituloDeTarea(int i, String tituloNuevo) {
        obtenerTarea(i).modificarTitulo(tituloNuevo);

    }

    public void modificarDescripcionDeTarea(int i, String descripcionNueva ) {
        obtenerTarea(i).modificarDescripcion(descripcionNueva);
    }

    public void modificarVencimientoDeTarea(int i, LocalDate vencimientoNuevo) {
        obtenerTarea(i).modificarVencimiento(vencimientoNuevo);
    }

    public String tituloDeTarea(int i) {
        return obtenerTarea(i).titulo();
    }

    public String descripcionDeTarea(int i) {
        comprobarTareaExistente(i);
        return gestor.get(i).descripcion();
    }

    public LocalDate vencimientoDeTarea(int i) {
        return obtenerTarea(i).fechaDeVencimiento();
    }

    public void completarTarea(int i) {
        obtenerTarea(i).completar();
        
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