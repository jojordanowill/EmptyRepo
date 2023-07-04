public class MyArrayList<Type extends Comparable<Type>> implements Comparable<MyArrayList<Character>> {
    public long comparisons;

    protected Type[] list;
    protected int capacity;

    protected int size;

    public MyArrayList() {
        this.size = 0;
        this.capacity = 16;
        this.list = (Type[]) new Comparable[capacity];

    }


    //once the capacity is reached, it is double to account for more items
    protected void resize() {
        capacity = capacity * 2;
        final Type[] resizedList = (Type[]) new Comparable[capacity];
        System.arraycopy(list, 0, resizedList, 0, size);
        list = resizedList;
    }

    //puts items into list at any index
    public void insert(Type item, int index) {
        // Check for duplicates
//        for (int i = 0; i < size; i++) {
//            if (list[i].compareTo(item) == 0) {
//                return;
//            }
//        }

        if (size == capacity) {
            resize();
        }

        for (int i = size - 1; i >= index; i--) {
            list[i + 1] = list[i];
        }
        list[index] = item;
        size++;
    }
    //tells you the size of the list, its updated with insert and remove
    public int size () {

        return this.size;
    }


    //takes away item and specified index
    public Type remove(int index) {
        if (index > size || index < 0) {
            return null;
        }

        final Type removed = list[index];

        for (int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }

        list[size - 1] = null;
        size--;

        return removed;
    }

    //tells you if the list has the item you're looking for
    public boolean contains(Type item) {
        for (int i = 0; i < size; i++) {
            comparisons++;
            if (list[i] != null && list[i].compareTo(item) == 0)
                return true;
        }
        return false;
    }

    //tells you if the item is present, and if not, it gives you -1
    public int indexOf(Type item) {
        for (int i = 0; i < size; i++) {
            if (list[i] != null && list[i].compareTo(item) == 0) {
                return i;
            }
        }
        return -1;
    }


    //gets whatever is at the index you ask for,if not null
    public Type get(int index) {
        if(index > capacity - 1 || index < 0){
            return null;
        }
        return list[index];
    }

    //sets any index to any item you give it
    public void set(int index, Type item) {
        if (index > capacity - 1 || index < 0){
            return;
        }
        list[index] = item;
    }

    //checks if list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    //sorts the list into ascending order
    public void sort() {
        boolean valueChanged = false;
        while (true) {
            valueChanged = false;
            for (int i = 0; i < size; i++) {
                Type nextItem = get(i + 1);
                Type currentItem = get(i);
                if (nextItem != null && currentItem.compareTo(nextItem) > 0) {
                    Type tempItem = nextItem;
                    set(i + 1, currentItem);
                    set(i, tempItem);
                    valueChanged = true;
                }
            }
            if (!valueChanged) {
                break;
            }
        }
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(list[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public int compareTo(MyArrayList<Character> o) {
        return 0;
    }
}
