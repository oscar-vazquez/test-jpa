package pruebas.jpa;

import java.util.Date;
import java.util.List;

public class AsyncRequest {
    public static class Data {
        private String dato;
        private String valor;

        public Data() {}

        public String getDato() {
            return dato;
        }

        public void setDato(String dato) {
            this.dato = dato;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }
    }

    private String casoSFDSC;
    private String proceso;
    private String operacion;
    private String numeroOrden;

    private List<Data> datos;

    public String getCasoSFDSC() {
        return casoSFDSC;
    }

    public void setCasoSFDSC(String casoSFDSC) {
        this.casoSFDSC = casoSFDSC;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public List<Data> getDatos() {
        return datos;
    }

    public void setDatos(List<Data> datos) {
        this.datos = datos;
    }
}
