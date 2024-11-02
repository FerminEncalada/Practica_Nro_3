package controller.TDA.queue;

public class Queque <E>{
    private QueueOperation<E> queueOperation;
    public Queque(Integer cant) {
        this.queueOperation = new QueueOperation<>(cant);
    }
    public void queque(E dato) throws Exception {
        queueOperation.queue(dato);
    }
    public Integer getSize() {
        return this.queueOperation.getSize();
    }
    
    public void clear() {
        this.queueOperation.reset();
    }

    public Integer getTop() {
        return this.queueOperation.getTop();
    }

    public void print() {
        System.out.println("Cola");
        System.out.println(queueOperation.toString());
        System.out.println("*****");
    }
    
    public E  dequeque()  throws Exception {
        return queueOperation.dequeue();
    }
}
