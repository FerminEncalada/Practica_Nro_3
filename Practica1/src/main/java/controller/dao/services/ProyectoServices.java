package controller.dao.services;

import controller.dao.ProyectoDao;
import controller.TDA.list.LinkedList;
import models.Proyecto;
import controller.TDA.list.ListEmptyException;
public class ProyectoServices {
    private ProyectoDao obj;
    public ProyectoServices() {
        obj = new ProyectoDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }

    public LinkedList<Proyecto> listAll(){
        return obj.getListAll();
    }

    public Proyecto getProyecto(){
        return obj.getProyecto();
    }

    public void setProyecto(Proyecto proyecto) {
        obj.setProyecto(proyecto);
    }

    public Proyecto get(Integer id) throws Exception {
        return obj.get(id);
    }

    public LinkedList<Proyecto> ordenarQuicksort(Integer type_order, String atributo) {
        return obj.ordenarQuicksort(type_order, atributo);
    }
    public LinkedList<Proyecto> ordenarMergeSort(Integer type_order, String atributo) {
        return obj.ordenarMergeSort(type_order, atributo);
    }
    public LinkedList<Proyecto> ordenarShellSort(Integer type_order, String atributo) {
        return obj.ordenarShellSort(type_order, atributo);
    }

    public LinkedList<Proyecto> ProyectosLineal(String criterio, String valor) throws ListEmptyException {
        return obj.buscarBinario(criterio, valor);
    }

    public LinkedList<Proyecto> buscarProyectosBinario(String criterio, String valor) throws ListEmptyException {
        return obj.buscarLineal(criterio, valor);
    }

}

