import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GestorDeTareasTest {

    private LocalDate fechaDeVencimiento;
    private Tarea tarea;
    private String campoTitulo;
    private String campoDescripcion;
    private String campoVencimiento;
    private GestorDeTarea gestor;
    LocalDate fecha3112;

    @BeforeEach
    void setUp(){
        campoTitulo = "Titulo";
        campoDescripcion = "Descripcion";
        campoVencimiento = "Fecha de vencimiento";
        fechaDeVencimiento = LocalDate.of(2025,12,18);
        tarea = new Tarea("Titulo de prueba", "Descripcion de prueba", fechaDeVencimiento);
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
        tarea.completar();
        assertEquals("Titulo Modificado",tarea.titulo());
        assertEquals("Descripcion Modificada",tarea.descripcion());
        assertTrue(tarea.estaVencida());
        assertTrue(tarea.estaCompleta());
    }

    @Test
    void test03_LosDatosDeUnaTareaNoPuedenSerNulosAlCrearseOModificarse(){
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
    void test04_unGestorDeTareasPuedeAgregarTareas(){
        gestor.agregarTarea("Titulo de prueba", "Descripcion de prueba",fecha3112);
        assertEquals(1, gestor.cantidadDeTareas());
        assertEquals(1, gestor.cantidadDeTareasNoVencidas());
        assertEquals(0, gestor.cantidadDeTareasVencidas());
    }
    
    @Test
    void test05_unGestorDeTareasPuedeEliminarTareas(){
        gestor.agregarTarea("Titulo de prueba", "Descripcion de prueba",fecha3112);
        gestor.eliminarTarea(1);
        assertEquals(0, gestor.cantidadDeTareas());
        assertEquals(0, gestor.cantidadDeTareasNoVencidas());
        assertEquals(0, gestor.cantidadDeTareasVencidas());
    
    }

    @Test
    void test06_unGestorDeTareasPuedeDevolverLosDatosUnaTarea(){
        gestor.agregarTarea("Titulo de prueba", "Descripcion de prueba",fecha3112);
        assertNotNull(gestor.obtenerTarea(1));
        assertEquals("Titulo de prueba", gestor.obtenerTituloDeTarea(1));
        assertEquals("Titulo de prueba",gestor.obtenerDescripcionDeTarea(1));
        assertEquals(fecha3112, gestor.obtenerVencimientoDeTarea(1));
        assertFalse(gestor.obtenerEstadoDeTarea(1));
        assertEquals(LocalDate.now(), gestor.obtenerFechaDeCreacionDeTarea(1));
        assertFalse(gestor.laTareaEstaVencida(1));
    }
    /*
    @Test
    void test07_unGestorDeTareasPuedeModificarLosDatosDeUnaTarea(){
        gestor.agregarTarea("Titulo de prueba", "Descripcion de prueba",fecha3112);
        gestor.modificarTitulo(1, "Nuevo Titulo");
        gestor.modificarDescripcion(1, "Nueva descripcion");
        gestor.modificarVencimiento(1, fechaDeVencimiento);
        gestor.marcarComoCompleta(1);
        assertEquals("Nuevo Titulo", gestor.obtenerTarea(1).titulo());
        assertEquals("Nueva descripcion", gestor.);
    }
         */

    

   

}
