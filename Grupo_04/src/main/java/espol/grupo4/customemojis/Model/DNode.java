package espol.grupo4.customemojis.Model;
/**
 * DNode / Double Node, creado para tener un nodo doblemente enlazado
 */
public class DNode<E> {
    private E content;
    private DNode<E> next;
    private DNode<E> prev;
    
    public DNode(E content) {
        this.content = content;
        this.next = null;
        this.prev = null;
    }

    // Getters & Setters
    public E getContent() {
        return content;
    }
 
    public void setContent(E content) {
        this.content = content;
    }
    
    public DNode<E> getNext() {
        return next;
    }

    public void setNext(DNode<E> next) {
        this.next = next;
    }
    
    public DNode<E> getPrev() {
        return prev;
    }

    public void setPrev(DNode<E> prev) {
        this.prev = prev;
    }
    @Override
    public String toString(){
        return "["+this.content+"]";
    }
}