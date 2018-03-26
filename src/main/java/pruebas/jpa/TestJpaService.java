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
        logger.info("Buscando suministro {}", numero);

        MapeoClientesResponse cliente = null;


        ClienteMapping cm = clienteMappingDAO.find(numero);

        logger.info("Rssultado: {}", cm);

        /*
        if (cm != null) {
            cliente = new MapeoClientesResponse();
            cliente.setNumeroCliente(cm.getNumeroCliente());
            cliente.setContrato(cm.getContrato());
            cliente.setNumeroSuministro(cm.getNumeroSuministro());
            cliente.setPod(cm.getPod());
            cliente.setTarifa(cm.getTarifa());
        }
        */

        return Response.ok(cm).build();
    }

    @GET
    @Path("/asyncrequest/{numero}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAsyncRequest(@PathParam("numero")String numero) {
        logger.info("Buscando async request {}", numero);

        pruebas.jpa.model.AsyncRequest ar = asyncRequestDAO.find(numero) ;

        logger.info("Rssultado: {}", ar);

        return Response.ok(ar).build();
    }

    @POST
    @Path("/asyncrequest")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAsyncRequest(@ApiParam pruebas.jpa.AsyncRequest a) {
        pruebas.jpa.model.AsyncRequest s = asyncRequestDAO.add(a.getCasoSFDSC(), a.getProceso(), a.getOperacion());
        return Response.ok(s).build();
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
            /*
            a = asyncRequestDAO.find(numeroOrden);
            a.setEstado(Estados.getEnum(x.getEstado()));
            */
            asyncRequestDAO.setEstado(numeroOrden, Estados.getEnum(x.getEstado()));
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
