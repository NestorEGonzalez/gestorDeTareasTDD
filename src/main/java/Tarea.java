import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public class Tarea {
    private String titulo;
    private String descripcion;
    private final LocalDate fechaDeCreacion;
    private LocalDate fechaDeVencimiento;
    private Boolean completa;
    private final String campoTitulo = "Titulo";
    private final String campoDescripcion = "Descripcion";
    private final String campoVencimiento = "Fecha de vencimiento";
    //DateTimeFormatter formatoDeFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Tarea(String titulo, String descripcion, LocalDate vencimiento){
        verficiarDatosNulos(titulo, descripcion, vencimiento);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaDeVencimiento = vencimiento;
        this.fechaDeCreacion = LocalDate.now();
        this.completa = false;
        verificarFechaDeVencimiento(vencimiento);
    }

    public String titulo(){
        return titulo;
    }

    public String descripcion(){
        return descripcion;
    }

    public LocalDate fechaDeVencimiento(){
        return fechaDeVencimiento;
    }

    public LocalDate fechaDeCreacion(){
        return fechaDeCreacion;
    }

    public Boolean estaVencida(){
        if (completa) {
            return !completa;
        }
        
        return fechaDeVencimiento.isBefore(LocalDate.now());
    }

    public Boolean estaCompleta(){
        return completa;
    }

    public void modificarTitulo(String tituloNuevo){
        verificarCadena(tituloNuevo,campoTitulo);
        verificarSiEstaCompleta();
        this.titulo = tituloNuevo;
        
    }

    public void modificarDescripcion(String descripcionNueva){
        verificarCadena(descripcionNueva, campoDescripcion);
        verificarSiEstaCompleta();
        this.descripcion = descripcionNueva;
        
    }

    public void modificarVencimiento(LocalDate vencimientoNuevo){
        verificarFecha(vencimientoNuevo,campoVencimiento);
        verificarSiEstaCompleta();
        this.fechaDeVencimiento = vencimientoNuevo;
        
    }

    public void completar() {
        this.completa = true;
    }

    public void verficiarDatosNulos(String titulo, String descripcion, LocalDate vencimiento){
        verificarCadena(titulo, campoTitulo);
        verificarCadena(descripcion, campoDescripcion);
        verificarFecha(vencimiento, campoVencimiento);

    }

    public void verificarCadena(String cadena, String campo){
        if (cadena == null || cadena.trim().isEmpty()){
            throw new IllegalArgumentException(errorElDatoEstaVacio(campo));
        }
    }

    public void verificarFecha(LocalDate fecha, String campo){
        if (fecha == null ){
            throw new IllegalArgumentException(errorElDatoEstaVacio(campo));
        }
    }

    public String errorElDatoEstaVacio(String campo){
        return "Error, en el "+campo+" ha ingresado un dato/formato invalido, o esta vacío o es nulo.";
    }

    public String mostrarDatos() {
        String datos = String.format("Titulo: %s%nDescripcion: %s%nFecha de creación: %s%nFecha de vencimiento: %s%nCompleta: %s%nVencida: %s%n", titulo,descripcion, fechaDeCreacion.toString(),fechaDeVencimiento.toString(),esVerdadero(completa),esVerdadero(estaVencida()));
        return datos;
        
   }

    private String esVerdadero(Boolean condicion){
        if (condicion) {
            return "Si";
        }
        return "No";
    }

    public void verificarFechaDeVencimiento(LocalDate vencimiento){
        if (vencimiento.isBefore(fechaDeCreacion)) {
            throw new IllegalArgumentException(errorVencimientoAnteriorACreacion());
        }
    }

    public String errorVencimientoAnteriorACreacion(){
        return "La fecha de vencimiento no puede ser anterior a la fecha actual.";
    }

    public void verificarSiEstaCompleta(){
        if (completa) {
            throw new IllegalArgumentException(errorNoSeModificanTareasCompletas());
        }
    }
    

    public String errorNoSeModificanTareasCompletas() {
        return "No pueden modificarse una tarea marcada como completa.";
    }



}


