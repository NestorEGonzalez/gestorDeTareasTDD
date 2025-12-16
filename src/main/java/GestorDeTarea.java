import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


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

    public Integer unoSiCeroSino(Boolean condicion){
        if (condicion){
            return 1;
        }
        return 0;
    }

    public void eliminarTarea() {

    }

    public void eliminarTarea(int i) {
        gestor.remove(i);
        
    }

    public Tarea obtenerTarea(int i) {
        return gestor.get(i);
    }

    public String obtenerTituloDeTarea(int i) {
        return obtenerTarea(i).titulo();
    }

    public String obtenerDescripcionDeTarea(int i) {
        return obtenerTarea(i).descripcion();
    }

    public LocalDate obtenerVencimientoDeTarea(int i) {
        return obtenerTarea(i).fechaDeVencimiento();
    }

    public Boolean obtenerEstadoDeTarea(int i) {
        return obtenerTarea(i).estaCompleta();
    }

    public LocalDate obtenerFechaDeCreacionDeTarea(int i) {
        return obtenerTarea(i).fechaDeCreacion();
    }

    public Boolean laTareaEstaVencida(int i) {
        return obtenerTarea(i).estaVencida();
    }
}