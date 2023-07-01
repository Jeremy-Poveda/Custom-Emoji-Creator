package espol.grupo4.customemojis.Model;

import java.util.Iterator;

public class CircularDoubleLinkedList<T> implements List<T> {

    private DNode<T> last;

    public CircularDoubleLinkedList() {
        this.last = null;
    }

    @Override
    public int size() {
        int count = 0;
        if (last == null) {
            return count;
        } else {
            for (DNode<T> viajero = last.getNext(); viajero.getNext() != last.getNext(); viajero = viajero.getNext()) {
                count++;
            }
        }
        return count+1; // Se suma uno ya que en el for no se toma en cuenta el nodo por donde empezó
    }
    
    @Override
    public T get(int index) {
        return this.getNode(index).getContent();
    }
    
    private DNode<T> getNode(int index){
        DNode<T> current = last.getNext(); // inicia desde el primero
        if(index >= 0){
            for(int i = 0; i < index; i++){
                current = current.getNext();
            }
        } else if (index < 0){
            index = Math.abs(index);
            for(int i = 0; i < index; i++){
                current = current.getPrev();
            }
        }
        return current;
    }
    
    @Override
    public boolean add(T element, int index){
        if (element == null) {
            return false;
        }
        DNode<T> newNode = new DNode(element);
        if(index < 0 || index > this.size()){
            System.out.println("[Error] Ingrese un índice válido.");
            return false;
        } 
        if(this.isEmpty()){
            this.last = newNode;
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
            return true;
        } else if (index == this.size()){
            this.addLast(element);
            return true;
        } else {
            DNode<T> selectedNode = this.getNode(index);
            newNode.setNext(selectedNode);
            newNode.setPrev(selectedNode.getPrev());
            selectedNode.getPrev().setNext(newNode);
            selectedNode.setPrev(newNode);
            return true;
        }
    }
    
    @Override
    public boolean add(T element) {
        this.addLast(element);
        return true;
    }
    
    @Override
    public boolean addFirst(T element){
        return this.add(element, 0);
    }
    
    @Override
    public boolean addLast(T element) {
        if (element == null) {
            return false;
        }
        DNode<T> newNode = new DNode<>(element);
        if (this.isEmpty()) {
            this.last = newNode;
            newNode.setNext(newNode);
            newNode.setPrev(newNode);
            return true;
        } else {
            newNode.setPrev(last);
            newNode.setNext(last.getNext());
            last.getNext().setPrev(newNode);
            last.setNext(newNode);
            this.last = newNode;          
            return true;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public String toString() {
        String outString = "";
        if (this.isEmpty()) {
            return "The list is empty";
        } else if (last == last.getNext()) {
            return "<-["+last.getContent().toString()+"]->";
        } else {
            for (T element : this) {
                if(!outString.equals("")){
                    outString = outString + " <-> " +"["+element+"]";
                }else{
                    outString = "<-" + "["+element+"]";
                }    
            }
        }
        return outString+"->";
    }
    
    @Override
    public T remove(int index){
        T content;
        if(index < 0 || index >= this.size() ){
            System.out.println("[Error] Ingrese un índice válido.");
            return null;
        }
        if (this.size() == 1){
            content = last.getContent();
            last = null; // Deja la lista vacía.
        } else {
            DNode<T> selectedNode = this.getNode(index);
            content = selectedNode.getContent();
            selectedNode.getPrev().setNext(selectedNode.getNext());
            selectedNode.getNext().setPrev(selectedNode.getPrev()); 
        }
        return content;
    }
      
    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor != size();
            }
            @Override
            public T next() {
                int returnIndex = cursor;
                cursor++;
                return get(returnIndex);
            }
        };
        return it;
    }
}