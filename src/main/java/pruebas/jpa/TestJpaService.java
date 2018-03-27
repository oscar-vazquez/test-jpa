package pruebas.jpa;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pruebas.jpa.model.AsyncRequestDAO;
import pruebas.jpa.model.ClienteMapping;
import pruebas.jpa.model.ClienteMappingDAO;
import pruebas.jpa.model.asyncrequest.Estados;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class TestJpaService {
    private static final Logger logger = LoggerFactory.getLogger(TestJpaService.class);

    private ClienteMappingDAO clienteMappingDAO;
    private AsyncRequestDAO asyncRequestDAO;

    @GET
    @Path("/cliente/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCliente(@PathParam("numero")int numero) {
        ClienteMapping cm = clienteMappingDAO.find(numero);
        return Response.ok(cm).build();
    }

    @GET
    @Path("/asyncrequest/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAsyncRequest(@PathParam("numero")String numero) {
        pruebas.jpa.model.AsyncRequest ar = asyncRequestDAO.find(numero) ;
        return Response.ok(ar).build();
    }

    @POST
    @Path("/asyncrequest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAsyncRequest(@ApiParam pruebas.jpa.AsyncRequest a) {
        pruebas.jpa.model.AsyncRequest x = null;
        if (a.getNumeroOrden() != null) {
            x = asyncRequestDAO.find(a.getNumeroOrden());
        }
        if (x == null) {
            x = asyncRequestDAO.add(a.getCasoSFDSC(), a.getProceso(), a.getOperacion());
        }
        if (a.getDatos() != null) {
            for (AsyncRequest.Data d : a.getDatos()) {
                asyncRequestDAO.addExtraData(x, d.getDato(), d.getValor());

            }
        }
        return Response.ok(x).build();
    }

    @PUT
    @Path("/asyncrequest/{numeroOrden}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEstadoAsyncRequest(@PathParam("numeroOrden") String numeroOrden, @ApiParam pruebas.jpa.AsyncRequest x) {
        pruebas.jpa.model.AsyncRequest a = null;
        if (x == null) {
            asyncRequestDAO.moveToNextState(numeroOrden);
            a = asyncRequestDAO.find(numeroOrden);
        }
        else {
        }
        return Response.ok(a).build();
    }

    public void setClienteMappingDAO(ClienteMappingDAO clienteMappingDAO) {
        this.clienteMappingDAO = clienteMappingDAO;
    }

    public void setAsyncRequestDAO(AsyncRequestDAO asyncRequestDAO) {
        this.asyncRequestDAO = asyncRequestDAO;
    }
}
