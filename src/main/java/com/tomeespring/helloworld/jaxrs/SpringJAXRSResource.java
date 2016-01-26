package com.tomeespring.helloworld.jaxrs;

import com.tomeespring.helloworld.cdi.PojoService;
import com.tomeespring.helloworld.ejb.SpringAsynchronous;
import com.tomeespring.helloworld.ejb.SpringQueueService;
import com.tomeespring.helloworld.ejb.SpringSingletonService;
import com.tomeespring.helloworld.ejb.SpringTransactionService;
import com.tomeespring.helloworld.jaxb.SpringJAXBHelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@Scope(value = "singleton")
@Path("/helloworld")
public class SpringJAXRSResource {

    private static final Logger logger = Logger.getLogger(SpringJAXRSResource.class.getName());

    @Autowired
    private SpringSingletonService springSingletonService;

    @Autowired
    private PojoService pojoService;

    @Autowired
    private SpringTransactionService springTransactionService;

    @Autowired
    private SpringQueueService springQueueService;

    @Autowired
    private SpringAsynchronous springAsynchronous;

    @GET
    @Path("/ping/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String ping(@PathParam("name") String name) {
        return "pong " + name;
    }

    @POST
    @Path("/jaxrs")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response jaxRs(SpringJAXBHelloWorld springJaxbHelloWorld) {

        logger.log(Level.FINE,"----------------REST JAXRS Service [{0}]!!!----------------", springJaxbHelloWorld);
        return Response.status(Response.Status.OK).entity(springJaxbHelloWorld).build();
    }

    @GET
    @Path("/async")
    @Produces(MediaType.TEXT_PLAIN)
    public String startAsync() {

        springAsynchronous.loop();
        return "ASYNC TASK KICKED OFF!!!";
    }

    @GET
    @Path("/singleton")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getSingleton() {

        springSingletonService.getSalutation();
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/singleton/{salutation}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postSingleton(@PathParam("salutation") String salutation) {

        springSingletonService.setSalutation(salutation);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/pojo")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getPojo() {

        pojoService.getGreeting();
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/pojo/{salutation}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response postPojo(@PathParam("salutation") String salutation) {

        pojoService.setGreeting(salutation);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/addSalutation")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addSalutation(SpringJAXBHelloWorld springJaxbHelloWorld) {

        springTransactionService.addSalutation(springJaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/updateSalutation")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response updateSalutation(SpringJAXBHelloWorld springJaxbHelloWorld) {

        springTransactionService.updateSalutation(springJaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/getSalutation/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getSalutation(@PathParam("id") Long id) {

        SpringJAXBHelloWorld springJaxbHelloWorld = springTransactionService.getSalutation(id);
        return Response.status(Response.Status.OK).entity(springJaxbHelloWorld).build();
    }

    @POST
    @Path("/addTechnology")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addTechnology(SpringJAXBHelloWorld springJaxbHelloWorld) {

        springTransactionService.addTechnology(springJaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/addTechnologyCascadePersist")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response addTechnologyCascadePersist(SpringJAXBHelloWorld springJaxbHelloWorld) {

        springTransactionService.addTechnologyCascadePersist(springJaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }

    @POST
    @Path("/updateTechnology")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response updateTechnology(SpringJAXBHelloWorld springJaxbHelloWorld) {

        springTransactionService.updateTechnology(springJaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }


    @POST
    @Path("/updateTechnologyCascadeUpdate")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response updateTechnologyCascadeUpdate(SpringJAXBHelloWorld springJaxbHelloWorld) {

        springTransactionService.updateTechnologyCascadeUpdate(springJaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }


    @GET
    @Path("/getTechnology/{id}")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getTechnology(@PathParam("id") Long id) {

        SpringJAXBHelloWorld springJaxbHelloWorld = springTransactionService.getTechnology(id);
        return Response.status(Response.Status.OK).entity(springJaxbHelloWorld).build();
    }

    @GET
    @Path("/getAll")
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response getAll() {

        List<SpringJAXBHelloWorld> greetingList = springTransactionService.findAll();

        GenericEntity<List<SpringJAXBHelloWorld>> list = new GenericEntity<List<SpringJAXBHelloWorld>>(greetingList) {};
        return Response.ok(list).build();
    }

    @POST
    @Path("/jms")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
    public Response jmsSend(SpringJAXBHelloWorld springJaxbHelloWorld) {

        springQueueService.send(springJaxbHelloWorld);
        return Response.status(Response.Status.OK).build();
    }


}

