package pruebas.jpa.model;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import pruebas.jpa.model.asyncrequest.Estados;

import javax.persistence.*;
import java.util.*;

@Entity
@EntityListeners(AsyncRequest.EntityListener.class)
@Table(name = "async_request")
@DynamicUpdate
public class AsyncRequest {
    @Id
    @GenericGenerator(name = "async_request_id", strategy = "pruebas.jpa.model.asyncrequest.IdGenerator")
    @GeneratedValue(generator = "async_request_id")
    @Column(name = "numero_orden", unique = true, nullable = false, length = 15)
    private String numeroOrden;

    @Column(name = "caso_sfdc", nullable = false, length = 15)
    private String casoSFDSC;

    @Column(name = "proceso", nullable = false)
    private String proceso;

    @Column(name = "operacion", nullable = false)
    private String operacion;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "recibido", nullable = false)
    private Date recibido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "estado_dttm", nullable = false)
    private Date LastModified;

    @Column(name = "estado", nullable = false)
    private String strEstado;

    @Transient
    private Estados estado;

    @Version
    @Column(name = "version")
    private int version;

    @OneToMany(mappedBy = "id.numeroOrden", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @OrderBy("id.sequence")
    private List<AsyncRequestLog> log = new ArrayList<>();


    @OneToMany(mappedBy = "id.numeroOrden", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AsyncRequestXtra> datosExtra = new HashSet<>();


    @SuppressWarnings("unused")
    AsyncRequest() {
    }

    AsyncRequest(String casoSFDC, String proceso, String operacion) {
        init();
        this.casoSFDSC = casoSFDC;
        this.proceso = proceso;
        this.operacion = operacion;
    }

    private void init() {
        this.recibido = new Date();
        this.LastModified = new Date();
        this.version = 1;
        this.estado = Estados.getInitialStare();
        this.strEstado = this.estado.toString();
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    @SuppressWarnings("unused")
    public String getCasoSFDSC() {
        return casoSFDSC;
    }

    @SuppressWarnings("unused")
    public String getProceso() {
        return proceso;
    }

    @SuppressWarnings("unused")
    public String getOperacion() {
        return operacion;
    }

    @SuppressWarnings("unused")
    public Date getRecibido() {
        return recibido;
    }

    @SuppressWarnings("unused")
    public Date getLastModified() {
        return LastModified;
    }

    public Estados getEstado() {
        if (estado == null) {
            estado = Estados.getEnum(strEstado);
        }
        return estado;
    }

    void setEstado(Estados estado) {
        this.estado = estado;
        this.strEstado = estado.toString();
    }

    public List<AsyncRequestLog> getLog() {
        return log;
    }

    public Set<AsyncRequestXtra> getDatosExtra() {
        return datosExtra;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AsyncRequest{");
        sb.append("numeroOrden='").append(numeroOrden).append('\'');
        sb.append(", casoSFDSC='").append(casoSFDSC).append('\'');
        sb.append(", proceso='").append(proceso).append('\'');
        sb.append(", operacion='").append(operacion).append('\'');
        sb.append(", recibido=").append(recibido);
        sb.append(", strEstado='").append(strEstado).append('\'');
        sb.append(", version=").append(version);
        sb.append('}');
        return sb.toString();
    }


    static public class EntityListener {
        @PrePersist
        @PreUpdate
        public void setDate(final AsyncRequest entity) {
            entity.LastModified = new Date();
        }

        @PostLoad
        public void setEstado(final AsyncRequest entity) {
            entity.estado = Estados.getEnum(entity.strEstado);
        }
    }

}
