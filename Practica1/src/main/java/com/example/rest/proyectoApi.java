package com.example.rest;

import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import controller.dao.services.ProyectoServices;
@Path("/proyectos")
public class proyectoApi {
    @Path("/misproyectos")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProyecto() {
        HashMap<String, Object> map = new HashMap<>();
        ProyectoServices ps = new ProyectoServices();
        map.put("msg", "Lista de proyectos");
        map.put("data", ps.listAll().toArray());
        if (ps.listAll().isEmpty()) {
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();		
    }

@Path("/crear")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response save(HashMap<String, Object> map) {
    HashMap<String, Object> res = new HashMap<>();

    ProyectoServices ps = new ProyectoServices();
    try {
        ps.getProyecto().setNombreProyecto(map.get("nombreProyecto").toString());
        ps.getProyecto().setInversion(Float.parseFloat(map.get("inversion").toString()));
        ps.getProyecto().setTiempoVida(Integer.parseInt(map.get("tiempoVida").toString()));
        ps.getProyecto().setFechaInicio(map.get("fechaInicio").toString());
        ps.getProyecto().setFechaFin(map.get("fechaFin").toString());
        ps.getProyecto().setElectricidadGenerada(Float.parseFloat(map.get("electricidadGenerada").toString()));
        ps.getProyecto().setCostoTotal(Float.parseFloat(map.get("costoTotal").toString()));
        ps.getProyecto().setCodigodelproyecto(map.get("codigodelproyecto").toString());
        
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


@Path("/misproyectos/{id}")
@GET	
@Produces(MediaType.APPLICATION_JSON)
public Response getmisproyectos(@PathParam("id") Integer id) {
    HashMap<String, Object> map = new HashMap<>();
    ProyectoServices ps = new ProyectoServices();
    try {
        ps.setProyecto(ps.get(id));
    } catch (Exception e) {
    }
    map.put("msg", "Proyecto");
    map.put("data", ps.getProyecto());
    if(ps.getProyecto().getIdProyecto() == null) {
        map.put("data", "No existe el proyecto");
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
        ProyectoServices ps = new ProyectoServices();
        ps.setProyecto(ps.get(Integer.parseInt(map.get("idProyecto").toString())));
        ps.getProyecto().setNombreProyecto(map.get("nombreProyecto").toString());
        ps.getProyecto().setInversion(Float.parseFloat(map.get("inversion").toString()));
        ps.getProyecto().setTiempoVida(Integer.parseInt(map.get("tiempoVida").toString()));
        ps.getProyecto().setFechaInicio(map.get("fechaInicio").toString());
        ps.getProyecto().setFechaFin(map.get("fechaFin").toString());
        ps.getProyecto().setElectricidadGenerada(Float.parseFloat(map.get("electricidadGenerada").toString()));
        ps.getProyecto().setCostoTotal(Float.parseFloat(map.get("costoTotal").toString()));
        ps.getProyecto().setCodigodelproyecto(map.get("codigodelproyecto").toString());

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