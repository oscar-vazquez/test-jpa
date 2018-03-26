package pruebas.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mapeo_clientes")
public class ClienteMapping {
    @Id
    @Column(name = "numero_suministro")
    private int numeroSuministro;

    @Column(name = "numero_cliente")
    private int numeroCliente;

    @Column(name = "tarifa")
    private int tarifa;

    @Column(name = "contrato")
    private int contrato;

    @Column(name = "pod")
    private int pod;


    @SuppressWarnings("unused")
    public boolean isT1() {
        return tarifa == 1;
    }

    @SuppressWarnings("unused")
    public int getNumeroCliente() {
        return numeroCliente;
    }

    @SuppressWarnings("unused")
    public int getTarifa() {
        return tarifa;
    }

    @SuppressWarnings("unused")
    public int getNumeroSuministro() {
        return numeroSuministro;
    }

    @SuppressWarnings("unused")
    public int getContrato() {
        return contrato;
    }

    @SuppressWarnings("unused")
    public int getPod() {
        return pod;
    }


    @Override
    public String toString() {
        return "ClienteMapping{" + "numeroSuministro=" + numeroSuministro +
               ", numeroCliente=" + numeroCliente +
               ", tarifa=" + tarifa +
               ", contrato=" + contrato +
               ", pod=" + pod +
               '}';
    }
}
