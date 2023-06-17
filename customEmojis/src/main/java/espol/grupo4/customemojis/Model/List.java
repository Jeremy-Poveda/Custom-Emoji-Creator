package espol.grupo4.customemojis.Model;

public interface List<T> extends Iterable<T> {
    public int size();
    public T get(int index);
    public boolean add(T element, int index);
    public boolean addFirst(T element);
    public boolean addLast(T element);
    public T remove(int index);
    public boolean isEmpty();
}
