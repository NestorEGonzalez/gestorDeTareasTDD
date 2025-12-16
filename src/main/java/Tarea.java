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
        return fechaDeVencimiento.isBefore(LocalDate.now());
    }

    public Boolean estaCompleta(){
        return completa;
    }

    public void modificarTitulo(String tituloNuevo){
        verificarCadena(tituloNuevo,campoTitulo);
        this.titulo = tituloNuevo;
    }

    public void modificarDescripcion(String descripcionNueva){
        verificarCadena(descripcionNueva, campoDescripcion);
        this.descripcion = descripcionNueva;
    }

    public void modificarVencimiento(LocalDate vencimientoNuevo){
        verificarFeha(vencimientoNuevo,campoVencimiento);
        this.fechaDeVencimiento = vencimientoNuevo;
    }

    public void completar() {
        this.completa = true;
    }

    public void verficiarDatosNulos(String titulo, String descripcion, LocalDate vencimiento){
        verificarCadena(titulo, campoTitulo);
        verificarCadena(descripcion, campoDescripcion);
        verificarFeha(vencimiento, campoVencimiento);

    }

    public void verificarCadena(String cadena, String campo){
        if (cadena == null || cadena.trim().isEmpty()){
            throw new IllegalArgumentException(errorElDatoEstaVacio(campo));
        }
    }

    public void verificarFeha(LocalDate fecha, String campo){
        if (fecha == null || fecha.toString().trim().isEmpty()){
            throw new IllegalArgumentException(errorElDatoEstaVacio(campo));
        }
    }

    public String errorElDatoEstaVacio(String campo){
        return "Error, en el "+campo+" ha ingresado un dato/formato invalido, o esta vacío o es nulo.";
    }


}


