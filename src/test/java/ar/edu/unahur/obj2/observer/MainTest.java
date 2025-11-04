package ar.edu.unahur.obj2.observer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unahur.obj2.observer.Observador.Entidad;
import ar.edu.unahur.obj2.observer.Observador.IEntidad;
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
    
    
}
