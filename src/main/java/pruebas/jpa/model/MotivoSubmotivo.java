package pruebas.jpa.model;

import javax.persistence.*;

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
}
