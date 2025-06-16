package espol.grupo4.customemojis.model;

import java.util.Iterator;

import espol.grupo4.customemojis.model.List;

public class CircularDoubleLinkedList<T> implements List<T> {

    private DNode<T> last;

    public CircularDoubleLinkedList() {
        this.last = null;
    }

    @Override
    public int size() {
        int count = 0;
        if (last != null) {
            for (DNode<T> viajero = last.getNext(); viajero.getNext() != last.getNext(); viajero = viajero.getNext()) {
                count++;
            }
            count++; // Se suma uno ya que en el for no se toma en cuenta el nodo por donde empezó
        }
        return count;
    }

    public int indexOf(final T element) {
        int indexCount = 0;
        boolean found = false;
        for (DNode<T> viajero = this.last.getNext(); !viajero.getContent().equals(element); viajero = viajero
                .getNext()) {
            indexCount++;
            if (indexCount > this.size() - 1) {
                System.out.println("No se encontro el elemento");
                indexCount = -1;
                break;
            }
        }
        if (indexCount != -1) {
            found = true;
        }
        return found ? indexCount : -1;
    }

    @Override
    public T get(final int index) {
        return this.getNode(index).getContent();
    }

    private DNode<T> getNode(final int index) {
        DNode<T> current = last.getNext(); // inicia desde el primero
        if (index >= 0) {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        } else if (index < 0) {
            int absIndex = Math.abs(index);
            for (int i = 0; i < absIndex; i++) {
                current = current.getPrev();
            }
        }
        return current;
    }

    @Override
    public boolean add(final T element, final int index) {
        boolean result = false;
        if (element != null) {
            final DNode<T> newNode = new DNode<>(element);
            if (index < 0 || index > this.size()) {
                System.out.println("[Error] Ingrese un índice válido.");
            } else if (this.isEmpty()) {
                this.last = newNode;
                newNode.setNext(newNode);
                newNode.setPrev(newNode);
                result = true;
            } else if (index == this.size()) {
                this.addLast(element);
                result = true;
            } else {
                final DNode<T> selectedNode = this.getNode(index);
                newNode.setNext(selectedNode);
                newNode.setPrev(selectedNode.getPrev());
                selectedNode.getPrev().setNext(newNode);
                selectedNode.setPrev(newNode);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean add(final T element) {
        this.addLast(element);
        return true;
    }

    @Override
    public boolean addFirst(final T element) {
        return this.add(element, 0);
    }

    @Override
    public boolean addLast(final T element) {
        boolean result = false;
        if (element != null) {
            final DNode<T> newNode = new DNode<>(element);
            if (this.isEmpty()) {
                this.last = newNode;
                newNode.setNext(newNode);
                newNode.setPrev(newNode);
                result = true;
            } else {
                newNode.setPrev(last);
                newNode.setNext(last.getNext());
                last.getNext().setPrev(newNode);
                last.setNext(newNode);
                this.last = newNode;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public String toString() {
        String outString = "";
        if (this.isEmpty()) {
            outString = "The list is empty";
        } else if (last == last.getNext()) {
            outString = "<-[" + last.getContent().toString() + "]->";
        } else {
            for (final T element : this) {
                if (!outString.equals("")) {
                    outString = outString + " <-> " + "[" + element + "]";
                } else {
                    outString = "<-" + "[" + element + "]";
                }
            }
            outString = outString + "->";
        }
        return outString;
    }

    @Override
    public T remove(final int index) {
        T content = null;
        if (this.size() == 1) {
            content = last.getContent();
            last = null; // Deja la lista vacía.
        } else {
            final DNode<T> selectedNode = this.getNode(index); // getNode se encarga de agarrar los nodos asi sea con
                                                               // indice negativo
            content = selectedNode.getContent();
            selectedNode.getPrev().setNext(selectedNode.getNext());
            if (index == this.size()) {
                this.last = selectedNode.getPrev();
            }
            selectedNode.getNext().setPrev(selectedNode.getPrev());
        }
        return content;
    }

    @Override
    public Iterator<T> iterator() {
        // default access modifier on field 'cursor'
        return new Iterator<>() {
            int cursor = 0; // package-private

            @Override
            public boolean hasNext() {
                return cursor != size();
            }

            @Override
            public T next() {
                final int returnIndex = cursor;
                cursor++;
                return get(returnIndex);
            }
        };
    }
}