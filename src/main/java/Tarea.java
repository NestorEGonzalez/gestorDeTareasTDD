import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;

public class Tarea {
    private String titulo;
    private String descripcion;
    private LocalDate fechaDeVencimiento;
    private boolean completa;
    private final LocalDate fechaDeCreacion;
    private static final String campoTitulo = "Titulo";
    private static final String campoDescripcion = "Descripcion";
    private static final String campoVencimiento = "Fecha de vencimiento";
    private static final String ERROR_CAMPO_VACIO = "Error, en el campo %s ha ingresado un formato inválido o el mismo está vacío o es nulo.";
    private static final String ERROR_VENC_ANT_CREACION = "La fecha de vencimiento no puede ser anterior a la fecha actual.";
    private static final String ERROR_MODIFICAR_COMPLETA= "No pueden modificarse una tarea marcada como completa.";
    

    public Tarea(String titulo, String descripcion, LocalDate vencimiento){
        verficiarDatosNulos(titulo, descripcion, vencimiento);
        verificarFechaDeVencimiento(vencimiento);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaDeVencimiento = vencimiento;
        this.fechaDeCreacion = LocalDate.now();
        this.completa = false;
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

    public boolean estaVencida(){
        if (completa) {
            return false;
        }
        return fechaDeVencimiento.isBefore(LocalDate.now());
       
    }

    public boolean estaCompleta(){
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
            throw new IllegalArgumentException(String.format(ERROR_CAMPO_VACIO, campo));
        }
    }

    public void verificarFecha(LocalDate fecha, String campo){
        if (fecha == null ){
            throw new IllegalArgumentException(String.format(ERROR_CAMPO_VACIO, campo));
        }
    }

    public String mostrarDatos() {
        String datos = String.format("Titulo: %s%nDescripcion: %s%nFecha de creación: %s%nFecha de vencimiento: %s%nCompleta: %s%nVencida: %s%n", titulo,descripcion, fechaDeCreacion.toString(),fechaDeVencimiento.toString(),esVerdadero(completa),esVerdadero(estaVencida()));
        return datos;
        
   }

    private String esVerdadero(boolean condicion){
        if (condicion) {
            return "Si";
        }
        return "No";
    }

    public void verificarFechaDeVencimiento(LocalDate vencimiento){
        if (vencimiento.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException(ERROR_VENC_ANT_CREACION);
        }
    }

    public void verificarSiEstaCompleta(){
        if (completa) {
            throw new IllegalArgumentException(ERROR_MODIFICAR_COMPLETA);
        }
    }
    
}


