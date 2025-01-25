package com.example.rest;


import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonArray;

import controller.TDA.list.LinkedList;
import controller.dao.services.HospitalServices;
import models.Hospital;
@Path("/Hospital")
public class HospitalApi {
    @Path("/mishospitales")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHospital() {
        HashMap<String, Object> map = new HashMap<>();
        HospitalServices ps = new HospitalServices();
        map.put("msg", "Lista de hospitales");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();		
    }

@Path("/save")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response save(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();

    HospitalServices ps = new HospitalServices();
    try {
        ps.getHospital().setNombre(map.get("nombre").toString());
        ps.getHospital().setDireccion(map.get("direccion").toString());
        ps.getHospital().setCapacidad(Integer.parseInt(map.get("capacidad").toString()));
        ps.getHospital().setLongitud(Double.parseDouble(map.get("longitud").toString()));
        ps.getHospital().setLatitud(Double.parseDouble(map.get("latitud").toString()));
        
        ps.save(); 
            res.put("msg", "ok");
            res.put("data", "Guardado con éxito");
            return Response.ok(res).build();
        } catch (Exception ex) {
            res.put("msg", "ERROR");
            res.put("data", ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
         }
}


@Path("/mishospitales/{id}")
@GET	
@Produces(MediaType.APPLICATION_JSON)
public Response getmishospitales(@PathParam("id") Integer id) {
    HashMap<String, Object> map = new HashMap<>();
    HospitalServices ps = new HospitalServices();
    try {
        ps.setHospital(ps.get(id));
    } catch (Exception e) {
    }
    map.put("msg", "Hospital");
    map.put("data", ps.getHospital());
    if(ps.getHospital().getIdHospital() == null) {
        map.put("data", "No existe el hospital");
        return Response.status(Response.Status.NOT_FOUND).entity(map).build();
    }
        return Response.ok(map).build();
    }

@Path("/edicion")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response update(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();    
    try {
        HospitalServices ps = new HospitalServices();
        ps.setHospital(ps.get(Integer.parseInt(map.get("idHospital").toString())));
        ps.getHospital().setNombre(map.get("nombre").toString());
        ps.getHospital().setDireccion(map.get("direccion").toString());
        ps.getHospital().setCapacidad(Integer.parseInt(map.get("capacidad").toString()));
        ps.getHospital().setLongitud(Double.parseDouble(map.get("longitud").toString()));
        ps.getHospital().setLatitud(Double.parseDouble(map.get("latitud").toString()));

        ps.update();
        res.put("msg", "ok");
        res.put("data", "Editado con éxito");
        return Response.ok(res).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            res.put("msg", "ERROR");
            res.put("data", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
        }
    }

}