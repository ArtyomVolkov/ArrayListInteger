package volkov;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class ArrayListInteger implements Collection<Integer> {

	private int size;
	private int items[];
	private static final int INITIAL_CAPACITY = 5;

	public ArrayListInteger() {
		this(INITIAL_CAPACITY);
	}

	public ArrayListInteger(int initCapacity) {
		if (initCapacity < 0) {
			throw new IllegalArgumentException(
					"Size array must be more or equals zero: " + initCapacity);
		}
		this.items = new int[initCapacity];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object obj) {
		int value = toInt(obj);
		for (int i = 0; i < size; i++) {
			if (value == items[i]) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new MyIterator();
	}

	@Override
	public boolean add(Integer element) {
		if (element == null) {
			throw new NullPointerException(
					"This collection does not permit null elements");
		}
		if (size >= items.length) {
			allocationArray();
		}
		items[size++] = element;
		return true;
	}

	private void allocationArray() {
		int[] temp = new int[items.length * 2];
		temp = Arrays.copyOf(items, temp.length);
		this.items = temp;
	}

	private int toInt(Object obj) {
		if (!(obj instanceof Integer)) {
			throw new ClassCastException("The object is not integer value: "
					+ obj);
		}
		return (int) obj;
	}

	private void removeByIndex(int index) {
		if (index < 0 && index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		int numMoved = size - index - 1;
		System.arraycopy(items, index + 1, items, index, numMoved);
		size--;
	}

	@Override
	public boolean remove(Object element) {

		int value = toInt(element);
		for (int i = 0; i < size; i++) {
			if (items[i] == value) {
				removeByIndex(i);
				return true;
			}
		}
		return false;
	}

	@Override
	public Object[] toArray() {
		Integer[] array = new Integer[size];
		for (int i = 0; i < size; i++) {
			array[i] = items[i];
		}
		return array;
	}

	@Override
	public boolean containsAll(Collection<?> c) {

		Iterator<?> it = c.iterator();
		while (it.hasNext()) {
			if (!contains(it.next())) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		for (Integer value : c) {
			add(value);
		}
		return !c.isEmpty();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean removed = false;
		for (Object object : c) {
			while (contains(object)) {
				remove(object);
				removed = true;
			}
		}
		return removed;

	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean retain = false;
		for (int i = 0; i < size;) {
			if (!c.contains(items[i])) {
				remove(items[i]);
				retain = true;
			} else {
				i++;
			}
		}
		return retain;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException("The method is not supported");
	}

	private class MyIterator implements Iterator<Integer> {
		private int cursor;

		@Override
		public boolean hasNext() {
			return cursor != size;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new ArrayIndexOutOfBoundsException(cursor);
			}
			return items[cursor++];
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException(
					"The method is not supported");
		}
	}
}
