package com.example.rest;

import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import controller.TDA.list.LinkedList;
import models.Inversionista;

import controller.dao.services.InversionistaServices;
@Path("/inversionistas")
public class inversionistaApi {
   @Path("/misinversionistas/{codigodelproyecto}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public Response getAllInversionista(@PathParam("codigodelproyecto") String codigodelproyecto) throws Exception {
       HashMap map = new HashMap<>();
       InversionistaServices is = new InversionistaServices();
       
       LinkedList<Inversionista> lista = is.listAll().filter(codigodelproyecto);

       if (lista.isEmpty()) {
           map.put("msg", "ERROR");
           map.put("data", "No se encontraron inversionistas");
       } else {
           map.put("msg", "Lista de inversionistas");
           map.put("data", lista.toArray());
       }

       return Response.ok(map).build();
   }

    @Path("/crear/{codigodelproyecto}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(HashMap map, @PathParam("codigodelproyecto") String codigodelproyecto)  throws Exception {
        HashMap res = new HashMap<>();
        try {
            InversionistaServices is = new InversionistaServices();
            Inversionista inversionista = is.getInversionista();
            inversionista.setNombres(map.get("nombres").toString());
            inversionista.setDni(map.get("dni").toString());
            inversionista.setPertenencia(codigodelproyecto);
            is.setInversionista(inversionista);
            is.save();
            res.put("msg", "ok");
            return Response.ok(res).build();
        } catch (Exception e) {
            res.put("msg", "ERROR");
            res.put("data", e.getMessage());   
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }
}
