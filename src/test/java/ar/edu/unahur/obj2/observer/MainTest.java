package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.Exepcion.NivelAlertaIncorrecto;
import ar.edu.unahur.obj2.observer.Observador.Entidad;
import ar.edu.unahur.obj2.observer.Observador.IEntidad;
import ar.edu.unahur.obj2.observer.Riesgo.ComportamientoRiesgo;
import ar.edu.unahur.obj2.observer.Riesgo.Promedio;
import ar.edu.unahur.obj2.observer.Sujeto.CentralMonitoreo;
import ar.edu.unahur.obj2.observer.Sujeto.ISistema;

public class MainTest {
    private ISistema central1;
    private IEntidad entidad1;
    private IEntidad entidad2;

    @BeforeEach
    void setUp(){
        central1 = new CentralMonitoreo();
        entidad1 = new Entidad("Hospital");
        entidad2 = new Entidad("Bomberos");
        central1.registrarEntidad(entidad1);
        central1.registrarEntidad(entidad2);
    }

    @Test
    void dadoElSetUp_alAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo() {
        central1.emitirAlerta("Calor", 6);
        central1.emitirAlerta("Lluvia", 8);

        // verifico que ambas hayan recibido la alerta
        assertEquals("Calor", entidad1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, entidad1.getAlertasRecibidas().getFirst().getNivel());

        assertEquals("Lluvia", entidad1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, entidad1.getAlertasRecibidas().getLast().getNivel());

        assertEquals("Calor", entidad2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, entidad2.getAlertasRecibidas().getFirst().getNivel());

        assertEquals("Lluvia", entidad2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, entidad2.getAlertasRecibidas().getLast().getNivel());

        // verifico el riesgo de cada una de ellas -> 10.
        assertEquals(10, entidad1.riesgo());
        assertEquals(10, entidad2.riesgo());
    }

    @Test
    void dadoElSetUp_alCambiarElComportamientoYAgregarAlertas_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo() {
        ComportamientoRiesgo promedio = new Promedio();
        entidad1.setComportamiento(promedio);
        central1.emitirAlerta("Calor", 6);
        central1.emitirAlerta("Lluvia", 8);

        // se verifica que cada una haya recibido las alertas
        assertEquals("Calor", entidad1.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, entidad1.getAlertasRecibidas().getFirst().getNivel());

        assertEquals("Lluvia", entidad1.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, entidad1.getAlertasRecibidas().getLast().getNivel());

        assertEquals("Calor", entidad2.getAlertasRecibidas().getFirst().getTipo());
        assertEquals(6, entidad2.getAlertasRecibidas().getFirst().getNivel());

        assertEquals("Lluvia", entidad2.getAlertasRecibidas().getLast().getTipo());
        assertEquals(8, entidad2.getAlertasRecibidas().getLast().getNivel());

        assertEquals(7, entidad1.riesgo());
        assertEquals(10, entidad2.riesgo());
    }

    @Test
    void dadoElSetUp_alAgregarAlertasQuitarUnaEntidadYAgregaNuevaAlerta_SeVerificaCorretamenteLasAlertasRecibidasYElRiesgo() {
        central1.emitirAlerta("Calor", 6);
        central1.emitirAlerta("Lluvia", 8);
        central1.eliminarEntidad(entidad1);
        central1.emitirAlerta("Granizo", 7);

        // se verifica que la entidad tiene 2 alertas
        assertEquals(2, entidad1.getAlertasRecibidas().size());
        // se verifica que el riesgo es de 10
        assertEquals(10, entidad1.riesgo());
        // se verifica que la entidad2 tiene 3 alertas
        assertEquals(3, entidad2.getAlertasRecibidas().size());
        // se verifica que el riesgo es de 7
        assertEquals(7, entidad2.riesgo());
        // se verifica que la cant de alertas enviadas -> 5.
        assertEquals(5, central1.cantidadDeAlertas());
    }

    @Test
    void Exepcion() {
        assertThrows(NivelAlertaIncorrecto.class, ()-> central1.emitirAlerta("calor", 0));
    }
}
