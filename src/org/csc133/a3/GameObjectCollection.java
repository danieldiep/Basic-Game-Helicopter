package org.csc133.a3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GameObjectCollection implements Collection<GameObject> {

    private ArrayList<GameObject> gameObjectArrayList = new ArrayList<GameObject>();

    @Override
    public int size() {
        return gameObjectArrayList.size();
    }

    @Override
    public boolean isEmpty() {
        return gameObjectArrayList.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return gameObjectArrayList.contains(o);
    }

    @Override
    public boolean add(GameObject o) {
        return gameObjectArrayList.add(o);
    }

    @Override
    public boolean remove(Object o) {
        return gameObjectArrayList.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return this.gameObjectArrayList.containsAll(c);
    }
    @Override
    public boolean addAll(Collection<? extends GameObject> c) {
        return this.gameObjectArrayList.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return this.gameObjectArrayList.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return this.gameObjectArrayList.retainAll(c);
    }

    @Override
    public Iterator<GameObject> iterator() {
        return gameObjectArrayList.iterator();
    }
    @Override
    public Object[] toArray() {
        return gameObjectArrayList.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }
    @Override
    public void clear() {
        gameObjectArrayList  = new ArrayList<>();
    }
    public GameObject get(int num) {
        return gameObjectArrayList.get(num);
    }
    public PlayerHelicopter playerHelicopter() {
        for (GameObject o : gameObjectArrayList) {
            if ( o instanceof  PlayerHelicopter) {
                return (PlayerHelicopter)o;
            }
        }
        return null;
    }
}
