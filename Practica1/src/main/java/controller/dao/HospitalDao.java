package controller.dao;

import javax.ws.rs.core.Link;

import com.google.gson.JsonArray;

import models.Hospital;
import controller.dao.implement.AdapterDao;
import controller.TDA.list.LinkedList;
import controller.TDA.list.ListEmptyException;
import controller.TDA.graph.GraphLabelNoDirect;
import controller.TDA.graph.algoritmos.BellmanFord;
import controller.TDA.graph.algoritmos.Floyd;

public class HospitalDao extends AdapterDao<Hospital> {
    private Hospital hospital;
    private LinkedList<Hospital> listAll;
    private GraphLabelNoDirect<String> graph;
    private LinkedList<String> vertexName;
    private String name = "HospitalGrafo.json";

    public GraphLabelNoDirect<String> createGraph() {
        if (vertexName == null) {
            vertexName = new LinkedList<>();
        }


        LinkedList<Hospital> list = this.getListAll();
        if (!list.isEmpty()) {
            if (graph == null) {
                graph = new GraphLabelNoDirect<>(list.getSize(), String.class);
            }


            Hospital[] hospital = list.toArray();
            for (int i = 0; i < hospital.length; i++) {
                vertexName.add(hospital[i].getNombre());
            }
            this.graph.drawGraph();
        }
        return this.graph;
    }


    public void saveGraph() throws Exception {
        if (graph != null) {
            this.graph.saveGraphLabel(name);
        }
    }


    public JsonArray obtainWeights() throws Exception {
        if (graph == null) {
            return this.graph.obtainWeights();
        }
        return new JsonArray();
    }

    public GraphLabelNoDirect<String> obtenerGrafo() throws Exception {
        if (graph == null) {
            createGraph();
        }

        if (graph.existsFile(name)) {
            graph.cargarModelosDesdeDao();
            graph.loadGraph(name);
            System.out.println("modelo asociado al grafo: " + name);
        } else {
            throw new Exception("No existe el archivo");
        }
                return graph;

    }


    public HospitalDao() {
        super(Hospital.class);
    }

    public Hospital getHospital() {
        if (hospital == null) {
            hospital = new Hospital();
        }
        return this.hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public LinkedList<Hospital> getListAll() {
        if (listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public boolean save() throws Exception {
        Integer idHospital = getListAll().getSize() + 1;
        hospital.setIdHospital(idHospital);
        this.persist(this.hospital);
        this.listAll = listAll();   
        return true;
    }

    public boolean update() throws Exception {

        this.merge(getHospital(), getHospital().getIdHospital() - 1);
        this.listAll = listAll();
        return true;
    }

    public boolean delete(Integer id) throws Exception {
        for(int i = 0; i < getListAll().getSize(); i++) {
            Hospital pro = getListAll().get(i);
            if (pro.getIdHospital().equals(id)) {
                getListAll().delete(i);
                return true;
            }
        }
        throw new ListEmptyException("Hospital no encontrado con ID: " + id);
    }


}
