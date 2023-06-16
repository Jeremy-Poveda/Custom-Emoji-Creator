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
    public T get(int index) {
        if(index < 0 || index >= size()){
            System.out.println("[Error] Ingrese un índice válido.");
            return null;
        } else {
            DNode<T> viajero = last.getNext();
            T content = viajero.getContent();
            while(index != 0){
                index--;
                content = viajero.getNext().getContent();
                viajero = viajero.getNext();
            }
            return content;
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
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor!=size();
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
