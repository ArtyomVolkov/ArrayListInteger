package volkov;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class ArrayListIntegerTest {

	@Test
	public void sizeTest() {
		ArrayListInteger container = new ArrayListInteger();
		assertEquals(0, container.size());
		container.add(1);
		container.add(4);
		container.add(0);
		assertEquals(3, container.size());
		container.remove(4);
		assertEquals(2, container.size());
	}

	@Test
	public void isEmptyTest() {
		ArrayListInteger container = new ArrayListInteger();
		assertEquals(0, container.size());
		container.add(0);
		assertEquals(1, container.size());
		container.add(3);
		container.add(9);
		assertEquals(3, container.size());
		container.clear();
		assertEquals(0, container.size());
	}

	@Test
	public void clearTest() {
		ArrayListInteger container = new ArrayListInteger();
		container.add(-1);
		container.add(25);
		container.add(-4);
		container.clear();
		assertEquals(0, container.size());
	}

	@Test
	public void containsTest() {
		ArrayListInteger container = new ArrayListInteger();
		assertFalse(container.contains(0));
		container.add(3);
		container.add(12);
		container.add(8);
		assertFalse(container.contains(16));
		assertFalse(container.contains(-9));
		assertTrue(container.contains(8));
		assertTrue(container.contains(3));
		assertTrue(container.contains(12));
	}

	@Test
	public void addTest() {
		ArrayListInteger container = new ArrayListInteger();
		assertEquals(0, container.size());
		container.add(123);
		assertEquals(1, container.size());
		assertFalse(container.contains(122));
		assertFalse(container.contains(0));
		assertTrue(container.contains(123));
	}

	@Test
	public void removeTest() {
		ArrayListInteger container = new ArrayListInteger();
		container.add(32);
		container.add(98);
		container.add(-1234);
		assertFalse(container.remove(0));
		assertFalse(container.remove(45));
		assertFalse(container.remove(-1243));
		assertTrue(container.remove(98));
		assertEquals(2, container.size());
		assertTrue(container.remove(32));
		assertTrue(container.remove(-1234));
		assertEquals(0, container.size());
	}

	@Test
	public void toArrayTest() {
		ArrayListInteger container = new ArrayListInteger();
		Integer[] arrayTest = { 2, 2, 3, 5, 7 };
		container.add(2);
		container.add(2);
		container.add(3);
		container.add(5);
		container.add(7);
		Integer[] collection = (Integer[]) container.toArray();
		assertEquals(arrayTest.length, collection.length);
		for (int i = 0; i < collection.length; i++) {
			assertEquals(collection[i], arrayTest[i]);
		}
	}

	@Test
	public void containsAllTest() {
		ArrayListInteger container = new ArrayListInteger();
		container.add(26);
		container.add(12);
		container.add(34);
		container.add(52);
		container.add(71);

		ArrayList<Integer> alist = new ArrayList<>();
		alist.add(71);
		alist.add(52);
		assertTrue(container.containsAll(alist));
		alist.add(2);
		assertFalse(container.containsAll(alist));
	}

	@Test
	public void addAllTest() {
		ArrayListInteger container = new ArrayListInteger();
		container.add(1);
		container.add(2);
		container.add(26);
		container.add(-5);
		container.add(71);

		ArrayList<Integer> alist = new ArrayList<>();
		alist.add(71);
		alist.add(129);
		alist.add(-189);
		alist.add(0);
		container.addAll(alist);
		Integer[] array = (Integer[]) container.toArray();
		Integer[] arrayTest = { 1, 2, 26, -5, 71, 71, 129, -189, 0 };
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i], arrayTest[i]);
		}
	}

	@Test
	public void removeAllTest() {
		ArrayListInteger container = new ArrayListInteger();
		container.add(10);
		container.add(96);
		container.add(83);
		container.add(-45);
		container.add(-889);
		ArrayList<Integer> alist = new ArrayList<>();
		alist.add(10);
		alist.add(96);
		alist.add(-18);
		alist.add(80);
		container.removeAll(alist);
		Integer[] array = (Integer[]) container.toArray();
		Integer[] arrayTest = { 83, -45, -889 };
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i], arrayTest[i]);
		}

	}

	@Test
	public void retainAllTest() {
		ArrayListInteger container = new ArrayListInteger();
		ArrayList<Integer> alist = new ArrayList<>();

		assertFalse(container.retainAll(alist));

		container.add(0);
		container.add(67);
		container.add(32);
		container.add(59);
		container.add(0);

		alist.add(0);
		alist.add(67);
		alist.add(-18);
		alist.add(0);
		container.removeAll(alist);
		assertTrue(container.retainAll(alist));
		Integer[] array = (Integer[]) container.toArray();
		Integer[] arrayTest = { 32, 59 };
		for (int i = 0; i < array.length; i++) {
			assertEquals(array[i], arrayTest[i]);
		}
	}

	@Test(expected = UnsupportedOperationException.class)
	public void toArrayTestException() {
		ArrayListInteger container = new ArrayListInteger();
		container.toArray(new Integer[5]);
	}

	@Test
	public void hasNextTest() {
		ArrayListInteger container = new ArrayListInteger();
		assertFalse(container.iterator().hasNext());
		container.add(0);
		container.add(2);
		container.add(5);
		container.add(7);
		assertTrue(container.iterator().hasNext());
		container.clear();
		assertFalse(container.iterator().hasNext());
	}

	@Test
	public void nextTest() {
		ArrayListInteger container = new ArrayListInteger();
		Iterator<Integer> it = container.iterator();
		container.add(10);
		container.add(20);
		container.add(52);
		container.add(71);

		assertEquals(10, (int) it.next());
		assertEquals(20, (int) it.next());
		assertEquals(52, (int) it.next());
		assertEquals(71, (int) it.next());
	}

	@Test(expected = ArrayIndexOutOfBoundsException.class)
	public void nextTestException() {
		ArrayListInteger container = new ArrayListInteger();
		Iterator<Integer> it = container.iterator();
		it.next();
	}

	@Test(expected = UnsupportedOperationException.class)
	public void removeTestException() {
		ArrayListInteger container = new ArrayListInteger();
		Iterator<Integer> it = container.iterator();
		it.remove();
	}

}
