package controller.dao;

import controller.dao.implement.AdapterDao;

import java.util.Arrays;

import controller.TDA.list.LinkedList;
import controller.TDA.list.ListEmptyException;
import models.Proyecto;

public class ProyectoDao extends AdapterDao<Proyecto> {
    private Proyecto proyecto;
    private LinkedList<Proyecto> listAll;

    public ProyectoDao(){
        super(Proyecto.class);
    }

    public Proyecto getProyecto() {
        if(proyecto == null) {
           proyecto = new Proyecto();
        }
        return this.proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LinkedList<Proyecto> getListAll() {
        if(this.listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll; 
    }

    public Boolean save() throws Exception {
        Integer id =  getListAll().getSize()+1;
        getProyecto().setIdProyecto(id);
        persist(getProyecto());
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getProyecto(), getProyecto().getIdProyecto() - 1);
        this.listAll = listAll();
        return true;
    }
    
    public LinkedList<Proyecto> ordenarQuicksort(Integer type_order, String atributo) {
        LinkedList<Proyecto> lista = getListAll();
        if (!lista.isEmpty()) {
            Proyecto[] arreglo = lista.toArray();
            lista.reset();
            quickSort(arreglo, 0, arreglo.length - 1, type_order, atributo);
            lista.toList(arreglo);
        }
        return lista;
    }
    
    private void quickSort(Proyecto[] arr, int low, int high, Integer type_order, String atributo) {
        if (low < high) {
            int pi = partition(arr, low, high, type_order, atributo);
            quickSort(arr, low, pi - 1, type_order, atributo);  // Lado izquierdo
            quickSort(arr, pi + 1, high, type_order, atributo); // Lado derecho
        }
    }
    
    private int partition(Proyecto[] arr, int low, int high, Integer type_order, String atributo) {
        Proyecto pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparar(arr[j], pivot, type_order, atributo)) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private boolean comparar(Proyecto a, Proyecto b, Integer type_order, String atributo) {
        if (atributo.equalsIgnoreCase("nombreProyecto")) {
            return type_order == 1 
                ? a.getNombreProyecto().compareTo(b.getNombreProyecto()) <= 0
                : a.getNombreProyecto().compareTo(b.getNombreProyecto()) >= 0;
        } else if (atributo.equalsIgnoreCase("inversion")) {
            return type_order == 1 
                ? a.getInversion() <= b.getInversion()
                : a.getInversion() >= b.getInversion();
        } else if (atributo.equalsIgnoreCase("codigodelproyecto")) {
            return type_order == 1 
                ? a.getCodigodelproyecto().compareTo(b.getCodigodelproyecto()) <= 0
                : a.getCodigodelproyecto().compareTo(b.getCodigodelproyecto()) >= 0;
        } else {
            throw new IllegalArgumentException("Atributo no válido: " + atributo);
        }
    }
    

    private void swap(Proyecto[] arr, int i, int j) {
        Proyecto temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public LinkedList<Proyecto> ordenarMergeSort(Integer type_order, String atributo) {
        LinkedList<Proyecto> lista = getListAll();
        if (!lista.isEmpty()) {
            Proyecto[] arreglo = lista.toArray();
            lista.reset();
            mergeSort(arreglo, 0, arreglo.length - 1, type_order, atributo);
            lista.toList(arreglo);
        }
        return lista;
    }
    
    private void mergeSort(Proyecto[] arr, int left, int right, Integer type_order, String atributo) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, type_order, atributo);
            mergeSort(arr, mid + 1, right, type_order, atributo);
            merge(arr, left, mid, right, type_order, atributo);
        }
    }
    
    private void merge(Proyecto[] arr, int left, int mid, int right, Integer type_order, String atributo) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
    
        Proyecto[] leftArr = new Proyecto[n1];
        Proyecto[] rightArr = new Proyecto[n2];
    
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
    
        int i = 0, j = 0, k = left;
    
        while (i < n1 && j < n2) {
            if (comparar(leftArr[i], rightArr[j], type_order, atributo)) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
    
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    public LinkedList<Proyecto> ordenarShellSort(Integer type_order, String atributo) {
        LinkedList<Proyecto> lista = getListAll();
        if (!lista.isEmpty()) {
            Proyecto[] arreglo = lista.toArray();
            lista.reset();
            shellSort(arreglo, type_order, atributo);
            lista.toList(arreglo);
        }
        return lista;
    }
    
    private void shellSort(Proyecto[] arr, Integer type_order, String atributo) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Proyecto temp = arr[i];
                int j;
                for (j = i; j >= gap && comparar(arr[j - gap], temp, type_order, atributo); j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }


    public LinkedList<Proyecto> buscarBinario(String criterio, String valor) throws ListEmptyException {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = getListAll(); // Obtener todos los proyectos
    
        if (listita == null || listita.isEmpty()) {
            throw new ListEmptyException("Error: La lista está vacía.");
        }
    
        Proyecto[] proyectos = listita.toArray(); // Convertir la lista enlazada a un arreglo
        Arrays.sort(proyectos, (p1, p2) -> { // Ordenar según el criterio
            switch (criterio.toLowerCase()) {
                case "nombreproyecto":
                    return p1.getNombreProyecto().compareToIgnoreCase(p2.getNombreProyecto());
                case "inversion":
                    return Float.compare(p1.getInversion(), p2.getInversion());
                case "codigodelproyecto":
                    return p1.getCodigodelproyecto().compareToIgnoreCase(p2.getCodigodelproyecto());
                default:
                    throw new IllegalArgumentException("Criterio de ordenación no válido: " + criterio);
            }
        });
    
        int low = 0, high = proyectos.length - 1;
    
        while (low <= high) {
            int mid = (low + high) / 2;
            Proyecto midProyecto = proyectos[mid];
            boolean match = false;
    
            // Comparar según el criterio
            switch (criterio.toLowerCase()) {
                case "nombreproyecto":
                    match = midProyecto.getNombreProyecto().toLowerCase().contains(valor.toLowerCase());
                    break;
                case "inversion":
                    try {
                        float inversion = Float.parseFloat(valor);
                        match = (midProyecto.getInversion() == inversion || 
                                 String.valueOf(midProyecto.getInversion()).startsWith(valor));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("El valor para 'inversion' debe ser un número válido.");
                    }
                    break;
                case "codigodelproyecto":
                    match = midProyecto.getCodigodelproyecto().toLowerCase().contains(valor.toLowerCase());
                    break;
                default:
                    throw new IllegalArgumentException("Criterio de búsqueda no válido: " + criterio);
            }
    
            // Si coincide, agregarlo a la lista
            if (match) {
                lista.add(midProyecto);
    
                // Buscar hacia la izquierda
                int left = mid - 1;
                while (left >= 0) {
                    Proyecto leftProyecto = proyectos[left];
                    boolean leftMatch = false;
                    switch (criterio.toLowerCase()) {
                        case "nombreproyecto":
                            leftMatch = leftProyecto.getNombreProyecto().toLowerCase().contains(valor.toLowerCase());
                            break;
                        case "inversion":
                            leftMatch = String.valueOf(leftProyecto.getInversion()).startsWith(valor);
                            break;
                        case "codigodelproyecto":
                            leftMatch = leftProyecto.getCodigodelproyecto().toLowerCase().contains(valor.toLowerCase());
                            break;
                    }
                    if (leftMatch) {
                        lista.add(leftProyecto);
                    } else {
                        break;
                    }
                    left--;
                }
    
                // Buscar hacia la derecha
                int right = mid + 1;
                while (right < proyectos.length) {
                    Proyecto rightProyecto = proyectos[right];
                    boolean rightMatch = false;
                    switch (criterio.toLowerCase()) {
                        case "nombreproyecto":
                            rightMatch = rightProyecto.getNombreProyecto().toLowerCase().contains(valor.toLowerCase());
                            break;
                        case "inversion":
                            rightMatch = String.valueOf(rightProyecto.getInversion()).startsWith(valor);
                            break;
                        case "codigodelproyecto":
                            rightMatch = rightProyecto.getCodigodelproyecto().toLowerCase().contains(valor.toLowerCase());
                            break;
                    }
                    if (rightMatch) {
                        lista.add(rightProyecto);
                    } else {
                        break;
                    }
                    right++;
                }
            }
    
            // Ajustar los límites
            switch (criterio.toLowerCase()) {
                case "nombreproyecto":
                    if (midProyecto.getNombreProyecto().compareToIgnoreCase(valor) < 0) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                    break;
                case "inversion":
                    try {
                        float inversion = Float.parseFloat(valor);
                        if (midProyecto.getInversion() < inversion) {
                            low = mid + 1;
                        } else {
                            high = mid - 1;
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("El valor para 'inversion' debe ser un número válido.");
                    }
                    break;
                case "codigodelproyecto":
                    if (midProyecto.getCodigodelproyecto().compareToIgnoreCase(valor) < 0) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                    break;
            }
        }
    
        return lista;
    }
    

    public LinkedList<Proyecto> buscarLineal(String criterio, String valor) throws ListEmptyException {
        LinkedList<Proyecto> lista = new LinkedList<>();
        LinkedList<Proyecto> listita = getListAll(); // Obtener todos los proyectos
    
        if (listita == null || listita.isEmpty()) {
            throw new ListEmptyException("Error: La lista está vacía.");
        }
    
        Proyecto[] proyectos = listita.toArray(); // Convertir la lista enlazada a un arreglo
    
        for (Proyecto proyecto : proyectos) {
            boolean match = false;
    
            // Comparar según el criterio
            switch (criterio.toLowerCase()) {
                case "nombreproyecto":
                    match = proyecto.getNombreProyecto().toLowerCase().contains(valor.toLowerCase());
                    break;
                case "inversion":
                    try {
                        float inversion = Float.parseFloat(valor);
                        match = (proyecto.getInversion() == inversion || 
                                 String.valueOf(proyecto.getInversion()).startsWith(valor));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("El valor para 'inversion' debe ser un número válido.");
                    }
                    break;
                case "codigodelproyecto":
                    match = proyecto.getCodigodelproyecto().toLowerCase().contains(valor.toLowerCase());
                    break;
                default:
                    throw new IllegalArgumentException("Criterio de búsqueda no válido: " + criterio);
            }
    
            if (match) {
                lista.add(proyecto); // Agregar todos los proyectos coincidentes
            }
        }
    
        return lista;
    }
    
    


}
