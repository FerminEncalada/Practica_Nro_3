package com.example.rest;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.dao.services.HospitalServices;

@Path("/prueba") // Cambiado para que coincida con la URL que mostramos
public class MyResource {
}