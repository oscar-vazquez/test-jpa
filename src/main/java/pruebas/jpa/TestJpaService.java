package pruebas.jpa;

import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pruebas.jpa.model.*;
import pruebas.jpa.model.asyncrequest.Estados;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

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
    @Path("/asyncrequest/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAsyncRequest(@ApiParam pruebas.jpa.AsyncRequest a) {
        pruebas.jpa.model.AsyncRequest x = asyncRequestDAO.add(a.getCasoSFDSC(), a.getProceso(), a.getOperacion());
        return Response.ok(x).build();
    }

    @POST
    @Path("/asyncrequest/xtra")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addExtraInfo(@ApiParam pruebas.jpa.AsyncRequest a) {
        Map<String, String> data = new HashMap<>();
        for (AsyncRequest.Data d : a.getDatos()) {
            data.put(d.getDato(), d.getValor());
        }
        pruebas.jpa.model.AsyncRequest x = asyncRequestDAO.addExtraData(a.getNumeroOrden(), data);
        return Response.ok(x).build();
    }

    @PUT
    @Path("/asyncrequest/{numeroOrden}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEstadoAsyncRequest(@PathParam("numeroOrden") String numeroOrden, @ApiParam pruebas.jpa.AsyncRequest x) {
        pruebas.jpa.model.AsyncRequest a = asyncRequestDAO.moveToNextState(numeroOrden);
        return Response.ok(a).build();
    }

    public void setClienteMappingDAO(ClienteMappingDAO clienteMappingDAO) {
        this.clienteMappingDAO = clienteMappingDAO;
    }

    public void setAsyncRequestDAO(AsyncRequestDAO asyncRequestDAO) {
        this.asyncRequestDAO = asyncRequestDAO;
    }
}
