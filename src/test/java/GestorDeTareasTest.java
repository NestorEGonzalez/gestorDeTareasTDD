import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;


public class GestorDeTareasTest {

    private LocalDate fechaDeVencimiento;
    private Tarea tarea;
    private String campoTitulo;
    private String campoDescripcion;
    LocalDate venc1 = LocalDate.of(2025, 12, 30);
    LocalDate venc2 = LocalDate.of(2025, 12, 31);
    private String campoVencimiento;
    private GestorDeTarea gestor;
    LocalDate fecha3112;
    Tarea tareaConVencMañana;
    Tarea tareaConVencHoy;
    

    @BeforeEach
    void setUp(){
        campoTitulo = "Titulo";
        campoDescripcion = "Descripcion";
        campoVencimiento = "Fecha de vencimiento";
        fechaDeVencimiento = LocalDate.now().plusDays(2);
        tarea = new Tarea("Titulo de prueba", "Descripcion de prueba", fechaDeVencimiento);
        tareaConVencMañana = new Tarea("Titulo","Descripcion",LocalDate.now().plusDays(1));
        tareaConVencHoy = new Tarea("Titulo","Descripcion",LocalDate.now());
        gestor = new GestorDeTarea();
        fecha3112 = LocalDate.of(2025,12,31);
        
    }

    @Test
    void test01_unaTareaConoceSusDatos(){
        assertEquals("Titulo de prueba", tarea.titulo());
        assertEquals("Descripcion de prueba", tarea.descripcion());
        assertEquals(fechaDeVencimiento, tarea.fechaDeVencimiento());
        assertEquals(LocalDate.now(), tarea.fechaDeCreacion());
        assertFalse(tarea.estaVencida());
        assertFalse(tarea.estaCompleta());
    }

    @Test
    void test02_unaTareaPuedeModificarSusDatosExceptoLaFechaDeCreaciónYPuedeCompletarse(){
        tarea.modificarTitulo("Titulo Modificado");
        tarea.modificarDescripcion("Descripcion Modificada");
        tarea.modificarVencimiento(LocalDate.of(2025,1,3));
        assertTrue(tarea.estaVencida());
        tarea.completar();
        assertEquals("Titulo Modificado",tarea.titulo());
        assertEquals("Descripcion Modificada",tarea.descripcion());
        assertFalse(tarea.estaVencida());
        assertTrue(tarea.estaCompleta());
    }

    @Test
    void test03_unTareaConoceSiEsteVencida(){
        assertFalse(tareaConVencMañana.estaVencida());
        assertFalse(tareaConVencHoy.estaVencida());
           
    }

    @Test
    void tes04_noPuedenCrearseTareasConFechaDeVencimientoAnteriorALaFechaDeCreación(){ 
        Exception ex = assertThrows(IllegalArgumentException.class,()-> {
            new Tarea("Titulo", "Descripción", LocalDate.now().plusDays(-1));
        });
        assertEquals(tarea.errorVencimientoAnteriorACreacion(), ex.getMessage());
    }

    @Test
    void test05_noPuedenModificarseTareasCompletas(){
        tarea.completar();
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            tarea.modificarTitulo("Nuevo Titulo");
        });
        assertEquals(tarea.errorNoSeModificanTareasCompletas(), ex.getMessage());
    }

    @Test
    void test06_LosDatosDeUnaTareaNoPuedenSerNulosAlCrearseOModificarse(){
        Exception tituloNulo = assertThrows(IllegalArgumentException.class, ()-> {
            new Tarea(null, "Descripcion", fechaDeVencimiento);
        });
        Exception descripcionNula = assertThrows(IllegalArgumentException.class, ()->{
            new Tarea("Titulo",null,fechaDeVencimiento);
        });
        Exception vencimientoNulo = assertThrows(IllegalArgumentException.class, ()->{
            new Tarea("Titulo", "Descripcion", null);
        });
        Exception tituloModificadoNulo = assertThrows(IllegalArgumentException.class, ()->{
            tarea.modificarTitulo(null);
        });
        Exception descripionModificadaNula = assertThrows(IllegalArgumentException.class, ()->{
            tarea.modificarDescripcion(null);
        });
        Exception vencimientoModificadoNulo = assertThrows(IllegalArgumentException.class, ()->{
            tarea.modificarVencimiento(null);
        });

        assertEquals(tarea.errorElDatoEstaVacio(campoTitulo), tituloNulo.getMessage());
        assertEquals(tarea.errorElDatoEstaVacio(campoDescripcion), descripcionNula.getMessage());
        assertEquals(tarea.errorElDatoEstaVacio(campoVencimiento), vencimientoNulo.getMessage());
        assertEquals(tarea.errorElDatoEstaVacio(campoTitulo), tituloModificadoNulo.getMessage());
        assertEquals(tarea.errorElDatoEstaVacio(campoDescripcion), descripionModificadaNula.getMessage());
        assertEquals(tarea.errorElDatoEstaVacio(campoVencimiento), vencimientoModificadoNulo.getMessage());
    }

    
    @Test
    void test07_unGestorDeTareasPuedeAgregarTareas(){
        gestor.agregarTarea("Titulo de prueba", "Descripcion de prueba",fecha3112);
        assertEquals(1, gestor.cantidadDeTareas());
        assertEquals(1, gestor.cantidadDeTareasNoVencidas());
        assertEquals(0, gestor.cantidadDeTareasVencidas());
    }
    
    @Test
    void test08_unGestorDeTareasPuedeEliminarTareas(){
        gestor.agregarTarea("Titulo de prueba", "Descripcion de prueba",fecha3112);
        gestor.eliminarTarea(1);
        assertEquals(0, gestor.cantidadDeTareas());
        assertEquals(0, gestor.cantidadDeTareasNoVencidas());
        assertEquals(0, gestor.cantidadDeTareasVencidas());
    
    }

    @Test
    void test09_unGestorDeTareasPuedeDevolverUnaTarea(){
        gestor.agregarTarea("Titulo de prueba", "Descripcion de prueba",fecha3112);
        Tarea tareaObtenida = gestor.obtenerTarea(1);
        assertEquals("Titulo de prueba", tareaObtenida.titulo());
        assertEquals("Descripcion de prueba",tareaObtenida.descripcion());
        assertEquals(fecha3112, tareaObtenida.fechaDeVencimiento());
        assertFalse(tareaObtenida.estaCompleta());
        assertEquals(LocalDate.now(), tareaObtenida.fechaDeCreacion());
        assertFalse(tareaObtenida.estaVencida());
    }
    
    @Test
    void test10_unGestorDeTareasPuedeMostrarLosDatosDeUnaTarea(){
        gestor.agregarTarea("Titulo de prueba", "Descripcion de prueba",fecha3112);
        String datosDeTarea = """
                id: 1
                Titulo: Titulo de prueba
                Descripcion: Descripcion de prueba
                Fecha de creación: 2025-12-17
                Fecha de vencimiento: 2025-12-31
                Completa: No
                Vencida: No

                """;
        assertEquals(datosDeTarea, gestor.mostrarDatosDeTarea(1));
    }

    @Test
    void test11_unGestorDeTareasPuedeModificarSusTareas(){
        gestor.agregarTarea("Titulo", "Descripcion", fechaDeVencimiento);
        gestor.modificarTituloDeTarea(1,"Titulo Nuevo");
        gestor.modificarDescripcionDeTarea(1,"Descripcion Nueva");
        gestor.modificarVencimientoDeTarea(1,fecha3112);
        assertEquals("Titulo Nuevo", gestor.tituloDeTarea(1));
        assertEquals("Descripcion Nueva", gestor.descripcionDeTarea(1));
        assertEquals(fecha3112, gestor.vencimientoDeTarea(1));
    }

    @Test
    void test12_unGestorDeTareasPuedeListarTodasSusTareas(){
        String titulo1 = "Titulo 1";
        String titulo2 = "Titulo 2";
        String descripcion1 = "Descripción 1";
        String descripcion2 = "Descripción 2";
        LocalDate venc1 = LocalDate.of(2025, 12, 30);
        LocalDate venc2 = LocalDate.of(2025, 12, 31);
        gestor.agregarTarea(titulo1, descripcion1, venc1);
        gestor.agregarTarea(titulo2, descripcion2, venc2);
        gestor.completarTarea(1);
        String mensajeEsperado = String.format("%s%s", gestor.mostrarDatosDeTarea(1),gestor.mostrarDatosDeTarea(2));
        assertEquals(mensajeEsperado, gestor.listarTareas());
        
    }
    
   

   

}
