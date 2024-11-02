package controller.TDA.list;

// Datos genéricos T E, K , V
// T significa que puede tomar cualquier tipo de dato: Integer, String, ObjectModel, etc.
public class Node<E> {
    private E info;
    // Clase recursiva porque se apunta a sí misma
    private Node<E> next;

    public E getInfo() {
        return info;
    }

    // Apunta al siguiente
    public void setInfo(E info) {
        this.info = info;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    // Constructor que inicializa el nodo con un valor y sin siguiente
    public Node(E info) {
        this.info = info;
        this.next = null;
    }

    // Constructor que inicializa el nodo con un valor y el siguiente nodo
    public Node(E info, Node<E> next) {
        this.info = info;
        this.next = next;
    }
}
