package ar.edu.unahur.obj2.observer.Observador;

import java.util.ArrayList;
import java.util.List;


import ar.edu.unahur.obj2.observer.Alerta;
import ar.edu.unahur.obj2.observer.Riesgo.ComportamientoRiesgo;
import ar.edu.unahur.obj2.observer.Riesgo.Critico;

public class Entidad implements IEntidad{
    private final String nombre;
    private ComportamientoRiesgo comportamiento = new Critico();
    List<Alerta> alertasRecibidas = new ArrayList();
   
    public Entidad(String nombre) {
        this.nombre = nombre;
    }
    public Entidad(String nombre, List<Alerta> alertasRecibidas) {
        this.nombre = nombre;
        this.alertasRecibidas = alertasRecibidas;
    }
    @Override
    public String nombre() {
        return this.nombre;
    }
    @Override
    public void recibeAlerta(Alerta unaAlerta) {
        alertasRecibidas.add(unaAlerta);
    }

    public ComportamientoRiesgo getComportamiento() {
        return comportamiento;
    }
    public void setComportamiento(ComportamientoRiesgo comportamiento) {
        this.comportamiento = comportamiento;
    }
    @Override
    public Integer riesgo(){
        if(alertasRecibidas.isEmpty()){
            return 0;
        }
        else{
            return comportamiento.doRiesgo(this);
        }
    }
    @Override
    public Alerta ultimaAlerta() {
        return alertasRecibidas.getLast();
    }
    @Override
    public Integer promedioDeAlertas() {
       return sumaTotal() / alertasRecibidas.size();
    }
    public Integer sumaTotal(){
        return alertasRecibidas.stream().mapToInt(a -> a.getNivel()).sum();
    }
    @Override
    public Integer nivelesDeAlertasCriticas() {
        return alertasRecibidas.stream().filter(a -> a.esCritica()).mapToInt(a -> a.getNivel()).sum();
    }
    public String getNombre() {
        return nombre;
    }
    @Override
    public List<Alerta> getAlertasRecibidas() {
        return alertasRecibidas;
    }
}
