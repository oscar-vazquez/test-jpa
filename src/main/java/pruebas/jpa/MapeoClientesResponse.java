package pruebas.jpa;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MapeoClientesResponse {
    private int numeroCliente;
    private int tarifa;
    private int numeroSuministro;
    private int contrato;
    private int pod;

    public int getNumeroCliente() {
        return numeroCliente;
    }

    public void setNumeroCliente(int numeroCliente) {
        this.numeroCliente = numeroCliente;
    }

    public int getTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public int getNumeroSuministro() {
        return numeroSuministro;
    }

    public void setNumeroSuministro(int numeroSuministro) {
        this.numeroSuministro = numeroSuministro;
    }

    public int getContrato() {
        return contrato;
    }

    public void setContrato(int contrato) {
        this.contrato = contrato;
    }

    public int getPod() {
        return pod;
    }

    public void setPod(int pod) {
        this.pod = pod;
    }

}
