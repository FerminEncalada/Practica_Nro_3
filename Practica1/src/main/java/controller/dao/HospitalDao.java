package controller.dao;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import models.Hospital;
import controller.dao.implement.AdapterDao;
import controller.TDA.list.LinkedList;
import controller.TDA.list.ListEmptyException;
import controller.TDA.graph.GraphLabelNoDirect;
import controller.TDA.graph.algoritmos.BellmanFord;
import controller.TDA.graph.algoritmos.DFS;
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
                this.graph.labelsVertices(i + 1, hospital[i].getNombre());
                vertexName.add(hospital[i].getNombre());
            }
            this.graph.drawGraph();
        }
        return this.graph;
    }


    public void saveGraph() throws Exception {
            this.graph.saveGraphLabel(name);
        
    }

    public JsonObject getGraphData() throws Exception {
        if (graph == null) {
            createGraph();
        }
    
        if (graph.existsFile(name)) {
            graph.cargarModelosDesdeDao();
            graph.loadGraph(name);
    
            // Asegúrate de que el tipo de dato sea correcto
            JsonObject graphData = graph.getVisGraphData(); // Aquí debe devolver el formato correcto
            return graphData; // Se asume que graphData es un JsonObject que contiene datos correctos
        } else {
            throw new Exception("No existe el archivo");
        }
    }


    public JsonArray obtainWeights() throws Exception {
        if (graph == null) {
            createGraph();
        }

        if (graph.existsFile(name)) {
            graph.cargarModelosDesdeDao();
            graph.loadGraph(name);
            JsonArray graphData = graph.obtainWeights();
            return graphData;
        } else {
            throw new Exception("No existe el archivo");
        }
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

    public GraphLabelNoDirect<String> adyacencias() throws Exception {
        if (graph == null) {
            createGraph();
        }

        if (graph.existsFile(name)) {
            graph.cargarModelosDesdeDao();
            graph.loadGraphWithRandomEdges(name);
        } else {
            throw new Exception("No existe el archivo");
        }
        saveGraph();
        System.out.println("Grafo con adyacencias creado" + graph);
        return graph;

    }

    public String caminoCorto(int origen, int destino, int algoritmo) throws Exception {
        if (graph == null) {
            throw new Exception("Grafo no existe");
        }
    
        System.out.println("Calculando camino corto desde " + origen + " hasta " + destino);
    
        long startTime = System.nanoTime(); // Iniciar medición de tiempo
    
        String recorrido = "";
    
        if (algoritmo == 1) { // Usar algoritmo de Bellman-Ford
            BellmanFord bellmanFord = new BellmanFord(graph, origen, destino);
            recorrido = bellmanFord.caminoCorto();
        } else { // Usar algoritmo de Floyd-Warshall
            Floyd floydWarshall = new Floyd(graph, origen, destino);
            recorrido = floydWarshall.caminoCorto();
        }
    
        long endTime = System.nanoTime(); // Finalizar medición de tiempo
        long durationNano = endTime - startTime; // Duración en nanosegundos
    
        System.out.println("Tiempo de ejecución: " + durationNano + " ns");
    
        return recorrido;
    }
    

    public String dFS(int origen) throws Exception {
        if (graph == null) {
            throw new Exception("El grafo no existe");
        }
    
        // Crear la instancia de BFS con el grafo y el nodo de origen
        DFS bfsAlgoritmo = new DFS(graph, origen);
    
        // Llamamos al método de recorrerGrafo() de la clase BFS para obtener el recorrido
        String recorrido = bfsAlgoritmo.recorrerGrafo();
        return recorrido;
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
