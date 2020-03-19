package pruebas.jpa;

import org.jboss.logging.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pruebas.jpa.model.*;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/")
public class TestJpaService {
    private static final Logger logger = LoggerFactory.getLogger(TestJpaService.class);

    private ClienteMappingDAO clienteMappingDAO;
    private AsyncRequestDAO asyncRequestDAO;
    private MotivoSubmotivoDAO motivoSubmotivoDAO;

    @GET
    @Path("/motivo/{param}")
    public Response getMotivo(@PathParam("param") String param) {
        logger.debug("parm = {}", param);
        String[] x = param.split("-");
        logger.debug("split: {}", String.join(", ",  x));
        logger.debug("dao: {}", motivoSubmotivoDAO);
        MotivoSubmotivo m = motivoSubmotivoDAO.find(x[0], x[1], x[2]);
        if (m == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("NO EXISTE") .build();
        }
        return Response.ok(m.toString()).build();
    }

    @GET
    @Path("/cliente/{numero}")
    public Response getCliente(@PathParam("numero")int numero) {
        ClienteMapping cm = clienteMappingDAO.find(numero);
        return Response.ok(cm).build();
    }

    @GET
    @Path("/asyncrequest/{numero}")
    public Response getAsyncRequest(@PathParam("numero")String numero) {
        pruebas.jpa.model.AsyncRequest ar = asyncRequestDAO.find(numero) ;
        return Response.ok(ar).build();
    }

    @POST
    @Path("/asyncrequest/add")
    public Response addAsyncRequest(pruebas.jpa.AsyncRequest a) {
        pruebas.jpa.model.AsyncRequest x = asyncRequestDAO.add(a.getCasoSFDSC(), a.getProceso(), a.getOperacion());
        return Response.ok(x).build();
    }

    @POST
    @Path("/asyncrequest/xtra")
    public Response addExtraInfo(pruebas.jpa.AsyncRequest a) {
        Map<String, String> data = new HashMap<>();
        for (AsyncRequest.Data d : a.getDatos()) {
            data.put(d.getDato(), d.getValor());
        }
        pruebas.jpa.model.AsyncRequest x = asyncRequestDAO.addExtraData(a.getNumeroOrden(), data);
        return Response.ok(x).build();
    }

    @PUT
    @Path("/asyncrequest/{numeroOrden}")
    public Response updateEstadoAsyncRequest(@PathParam("numeroOrden") String numeroOrden, pruebas.jpa.AsyncRequest x) {
        pruebas.jpa.model.AsyncRequest a = asyncRequestDAO.moveToNextState(numeroOrden);
        return Response.ok(a).build();
    }

    public void setClienteMappingDAO(ClienteMappingDAO clienteMappingDAO) {
        this.clienteMappingDAO = clienteMappingDAO;
    }

    public void setAsyncRequestDAO(AsyncRequestDAO asyncRequestDAO) {
        this.asyncRequestDAO = asyncRequestDAO;
    }

    public void setMotivoSubmotivoDAO(MotivoSubmotivoDAO motivoSubmotivoDAO) {
        this.motivoSubmotivoDAO = motivoSubmotivoDAO;
    }
}
