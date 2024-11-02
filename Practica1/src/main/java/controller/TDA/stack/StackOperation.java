package controller.TDA.stack;

import controller.TDA.list.ListEmptyException;
import controller.TDA.list.OverFlowException;
import controller.TDA.list.LinkedList;
public class StackOperation  <E> extends LinkedList<E>{
    private Integer top;

    public StackOperation(Integer top ) {
        this.top = top;
    }

    public Boolean verify() {
        return getSize().intValue() <= top.intValue();
    }

    public void push(E dato) throws Exception {
        if (verify()) {
            add(dato, 0);
        } else {
            throw new OverFlowException("Error, desbordamiento");
        }
    }

    public E pop() throws ListEmptyException {
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
