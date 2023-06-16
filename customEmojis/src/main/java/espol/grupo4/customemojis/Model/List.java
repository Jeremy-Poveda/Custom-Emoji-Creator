package espol.grupo4.customemojis.Model;

public interface List<T> extends Iterable<T> {
    public int size();
    public boolean addLast(T element);
    public boolean isEmpty();
    public T get(int index);
    
}
