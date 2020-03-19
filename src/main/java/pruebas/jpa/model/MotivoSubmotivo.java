package pruebas.jpa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "conversion_motivos")
public class MotivoSubmotivo {
    public enum Sistema {
        Candela("C"),
        MAC("M");

        private String value;

        Sistema(String value) {
            this.value = value;
        }

        public static Sistema get(String codigo) {
            for (Sistema s : Sistema.values()) {
                if (s.value.equals(codigo)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("No enum constant " + Sistema.class.getName().replace("$", ".") + "." + codigo);
        }

        @Override
        public String toString() {
            return value;
        }
    }

    @EmbeddedId
    private MotivoSubmotivoPK primaryKey;

    @Column(name = "motivo_destino")
    private String motivoDestino;

    @Column(name = "submotivo_destino")
    private String submotivoDestino;


    public String getMotivoOrigen() {
        return primaryKey.getMotivoOrigen();
    }

    public String getSubmotivoOrigen() {
        return primaryKey.getSubmotivoOrigen();
    }

    public Sistema getSistema() {
        return primaryKey.getSistema();
    }

    public String getMotivoDestino() {
        return motivoDestino;
    }

    public String getSubmotivoDestino() {
        return submotivoDestino;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MotivoSubmotivo{");
        sb.append("primaryKey=").append(primaryKey);
        sb.append(", motivoDestino='").append(motivoDestino).append('\'');
        sb.append(", submotivoDestino='").append(submotivoDestino).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Embeddable
    static
    class MotivoSubmotivoPK implements Serializable {
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
}
