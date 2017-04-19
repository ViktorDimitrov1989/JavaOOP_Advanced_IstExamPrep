package collection;

public interface Register<T> {

    Boolean isEmpty();

    T peekEmergency();

    T dequeueEmergency();

    void enqueueEmergency(T emergency);

    Integer count();
}
