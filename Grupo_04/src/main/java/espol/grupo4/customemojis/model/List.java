package espol.grupo4.customemojis.model;

public interface List<T> extends Iterable<T> {
    public int size();

    public T get(int index);

    public boolean add(T element, int index);

    public boolean add(T element);

    public boolean addFirst(T element);

    public boolean addLast(T element);

    public T remove(int index);

    public boolean isEmpty();
}
