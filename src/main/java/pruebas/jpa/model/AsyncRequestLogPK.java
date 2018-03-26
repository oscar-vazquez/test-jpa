package pruebas.jpa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AsyncRequestLogPK implements Serializable {
    @Column(name = "numero_orden", length = 15, nullable = false, insertable = false, updatable = false, unique = true)
    protected String numeroOrden;

    @Column(name = "seqno", nullable = false, unique = true)
    protected Integer sequence;

    public AsyncRequestLogPK() { }

    /*
    String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }
*/

    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.numeroOrden.hashCode();
        hash = hash * prime + this.sequence.hashCode();

        return hash;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AsyncRequestLogPK)) {
            return false;
        }
        AsyncRequestLogPK castOther = (AsyncRequestLogPK)other;
        return this.numeroOrden.equals(castOther.numeroOrden) && this.sequence.equals(castOther.sequence);
    }
}
