package pruebas.jpa;

import java.util.Date;

public class AsyncRequest {
    private String casoSFDSC;

    private String proceso;

    private String operacion;

    private Date recibido;

    private String estado;

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

    public Date getRecibido() {
        return recibido;
    }

    public void setRecibido(Date recibido) {
        this.recibido = recibido;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
