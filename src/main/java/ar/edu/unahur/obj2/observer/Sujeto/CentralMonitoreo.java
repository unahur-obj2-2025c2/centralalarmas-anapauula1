package ar.edu.unahur.obj2.observer.Sujeto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.edu.unahur.obj2.observer.Alerta;
import ar.edu.unahur.obj2.observer.Exepcion.NivelAlertaIncorrecto;
import ar.edu.unahur.obj2.observer.Observador.IEntidad;

public class CentralMonitoreo implements ISistema{
    List<IEntidad> entidadesRegistradas = new ArrayList<>();
    private Map<Alerta, Integer> registro = new HashMap<>();

    @Override
    public void emitirAlerta(String Tipo,Integer nivel){
        Alerta alerta = new Alerta(Tipo, nivel); 

        if(alerta.getNivel() >= 1 && alerta.getNivel() <=10){
            registro.put(alerta, entidadesRegistradas.size());
            notificarALasEntidades(alerta);
        }
        else{
            throw new NivelAlertaIncorrecto("Nivel de alerta incorrecto");
        }
    }
    public void notificarALasEntidades(Alerta alerta){
        entidadesRegistradas.forEach(e -> e.recibeAlerta(alerta));
    }
    @Override
    public void eliminarEntidad(IEntidad unaEntidad) {
        entidadesRegistradas.remove(unaEntidad);
    }
    @Override
    public void registrarEntidad(IEntidad unaEntidad) {
        entidadesRegistradas.add(unaEntidad);
    }
    @Override
    public Integer cantidadDeAlertas(){
        return registro.values().stream().mapToInt(Integer::intValue).sum();
    }
}
