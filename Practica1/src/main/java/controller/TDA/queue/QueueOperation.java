package controller.TDA.queue;

import controller.TDA.list.ListEmptyException;
import controller.TDA.list.OverFlowException;
import controller.TDA.list.LinkedList;
public class QueueOperation <E> extends LinkedList<E>{
    private Integer top;

    public QueueOperation(Integer top ) {
        this.top = top;
    }

    public Boolean verify() {
        return getSize().intValue() <= top.intValue();
    }

    public void queue(E dato) throws Exception {
        if (verify()) {
            add(dato,getSize());
        } else {
            throw new OverFlowException("Error, desbordamiento");
        }
    }

    public E dequeue() throws ListEmptyException {
        if (isEmpty()) {
            throw new ListEmptyException("Error, lista vacia");
        } else {
            return deleteFirst();
        }
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }
}
    