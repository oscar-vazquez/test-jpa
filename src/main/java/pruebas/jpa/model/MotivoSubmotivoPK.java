package pruebas.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MotivoSubmotivoPK implements Serializable {
    @Column(name = "motivo_origen")
    private String motivoOrigen;

    @Column(name = "submotivo_origen")
    private String submotivoOrigen;

    @Column
    private String sistema;

    public MotivoSubmotivoPK(String motivo, String submotivo, MotivoSubmotivo.Sistema sistema) {
        this.motivoOrigen = motivo;
        this.submotivoOrigen = submotivo;
        this.sistema = sistema.toString();
    }

    public String getMotivoOrigen() {
        return motivoOrigen;
    }

    public void setMotivoOrigen(String motivoOrigen) {
        this.motivoOrigen = motivoOrigen;
    }

    public String getSubmotivoOrigen() {
        return submotivoOrigen;
    }

    public void setSubmotivoOrigen(String submotivoOrigen) {
        this.submotivoOrigen = submotivoOrigen;
    }

    public MotivoSubmotivo.Sistema getSistema() {
        return MotivoSubmotivo.Sistema.get(sistema);
    }

    public void setSistema(MotivoSubmotivo.Sistema sistema) {
        this.sistema = sistema.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MotivoSubmotivoPK that = (MotivoSubmotivoPK) o;
        return Objects.equals(motivoOrigen, that.motivoOrigen) &&
                Objects.equals(submotivoOrigen, that.submotivoOrigen) &&
                Objects.equals(sistema, that.sistema);
    }

    @Override
    public int hashCode() {
        return Objects.hash(motivoOrigen, submotivoOrigen, sistema);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MotivoSubmotivoPK{");
        sb.append("motivoOrigen='").append(motivoOrigen).append('\'');
        sb.append(", submotivoOrigen='").append(submotivoOrigen).append('\'');
        sb.append(", sistema='").append(sistema).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
