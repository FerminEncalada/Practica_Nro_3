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

import controller.TDA.list.LinkedList;
import controller.dao.services.ProyectoServices;
import models.Proyecto;
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


@Path("/ordenarproyectos/{metodo}/{type_order}/{atributo}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response ordenarProyectos(@PathParam("metodo") String metodo, @PathParam("type_order") Integer type_order, @PathParam("atributo") String atributo) {
    HashMap<String, Object> map = new HashMap<>();
    ProyectoServices ps = new ProyectoServices();
    
    try {
        LinkedList<Proyecto> lista;

        if("metodo1".equalsIgnoreCase(metodo)) {
            lista = ps.ordenarQuicksort(type_order, atributo);
        } else if("metodo2".equalsIgnoreCase(metodo)) {
            lista = ps.ordenarMergeSort(type_order, atributo);
        } else if("metodo3".equalsIgnoreCase(metodo)) {
            lista = ps.ordenarShellSort(type_order, atributo);
        } else {
            map.put("msg", "ERROR");
            map.put("data", "Algoritmo no válido: " + metodo);
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        }

        map.put("msg", "Lista de proyectos ordenados");
        map.put("data", lista.toArray());
        return Response.ok(map).build();
    } catch (Exception e) {
        map.put("msg", "ERROR");
        map.put("data", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();
    }
}


@Path("/metodosdebusquedad/{busquedad}/{criterio}/{valor}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response buscarproyecto(@PathParam("busquedad") String busquedad ,@PathParam("criterio") String criterio,@PathParam("valor") String valor ) {
    HashMap<String, Object> map = new HashMap<>();
    ProyectoServices ps = new ProyectoServices();

    try {
        LinkedList<Proyecto> proyectobuscar;

        if("busquedadbinaria".equalsIgnoreCase(busquedad)) {
            proyectobuscar = ps.buscarProyectosBinario(criterio, valor);
        } else if ("busquedadlineal".equalsIgnoreCase(busquedad)) {
            proyectobuscar = ps.ProyectosLineal(criterio, valor);
        } else {
            map.put("msg", "ERROR");
            map.put("data", "No es vaidad la busquedad ");
            return Response.status(Response.Status.BAD_REQUEST).entity(map).build();
        } 

        if (proyectobuscar != null && !proyectobuscar.isEmpty()) {
            map.put("msg", "Proyecto encontrado");
            map.put("data", proyectobuscar);
            return Response.ok(map).build();
        } else {
            map.put("msg", "Proyecto no encontrado");
            return Response.status(Response.Status.NOT_FOUND).entity(map).build();
        }
        } catch(Exception e){
            map.put("msg","ERROR");
            map.put("data", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(map).build();

    }
}


}