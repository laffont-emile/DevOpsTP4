import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import datastruct.EmptyListException;
import datastruct.MyUnsortedList;
import datastruct.UnsortedList;

public class MyUnsortedListTest {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(1);//ajout du timeout
	
	//test simple sur les methodes
	@Test
	public void testTaille() {
		UnsortedList<Integer> integers = MyUnsortedList.of(1, 2, 3, 4);
		assertEquals("get size interger list", 4, integers.size());
		UnsortedList<String> strings = MyUnsortedList.of("toto");
		assertEquals("get size string list", 1, strings.size());
	}
	
	@Test
	public void testTailleListeVide() {
		UnsortedList<Integer> integers = MyUnsortedList.of();
		assertEquals("get size of a enpmty list", 0, integers.size());
	}
	
	@Test
	public void testListEmpty() {
		UnsortedList<Integer> integers = MyUnsortedList.of();
		assertTrue("is empty ",integers.isEmpty());
	}
	
	@Test
	public void testAjoutSuprressionEltEnTete() {
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3);
		integers.prepend(-1);
		assertEquals("get size after prepend", 4, integers.size());
		int res = integers.pop();
		assertEquals("get size after prepend and pop", 3, integers.size());
		assertEquals("same element get after prepend pop", -1 , res);
	}
	
	@Test
	public void testAjoutSuprressionEltEnQueue() {
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3);
		integers.append(4);
		assertEquals("get size after append", 4, integers.size());
		int res = integers.popLast();
		assertEquals("get size after append and popLast", 3, integers.size());
		assertEquals("same element get after append popLast", 4 , res);
	}
	
	@Test
	public void testAjoutSuprressionEltPositionQuelquonque() {
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3,4,5);
		integers.insert(-1,0);
		integers.insert(0,4);
		assertEquals("get size after double insert", 7, integers.size());
		int res2 = integers.remove(4);
		int res1 = integers.remove(0);
		assertEquals("get size after double insert and double remove", 5, integers.size());
		assertEquals("same 1st elt get after insert remove ", -1 , res1);
		assertEquals("same 2nd elt get after  insert remove ", 0 , res2);
	}
	
	@Test
	public void testEquals() {
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3,4,5);
		UnsortedList<Integer> integers_expected = MyUnsortedList.of(-1,1,2,3,4,5,0);
		integers.insert(-1,0);
		integers.insert(0,6);
		integers.prepend(4);
		integers.append(6);
		integers.remove(0);
		integers.remove(7);
		assertTrue("is the list "+ integers_expected.toString() + " equals to the generate list after insert pop etc ", integers.equals(integers_expected));
	}
	
	@Test
	public void testEqualsFalse() {
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3,4,5);
		UnsortedList<Integer> integers_expected = MyUnsortedList.of(-1,1,2,3,4,5,0);
		assertFalse("are the lists equals ?  " + integers.toString() + "  " + integers_expected.toString(), integers.equals(integers_expected));
		UnsortedList<Integer> integers2 = MyUnsortedList.of(1,2,3,4,0);
		assertFalse("are the lists equals ?  " + integers.toString() + "  " + integers2.toString(), integers.equals(integers2));
		String s = "";
		assertFalse("are the list equals to a string ?", integers.equals(s));
		
		UnsortedList<String> strings1 = MyUnsortedList.of("toto","tata");
		UnsortedList<String> strings2 = MyUnsortedList.of("toto",null);
		assertFalse("are the lists equals ?  " + strings1.toString() + "  " + strings2.toString(), strings1.equals(strings2));
		
	}
	
	@Test
	public void testOutOfBundOfInsertNegatifIndex() throws Exception{
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3,4,5);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			integers.insert(0,-1);
	    });
	}
	
	@Test
	public void testOutOfBundOfInsertBiggerIndex() throws Exception{
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3,4,5);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			integers.insert(0,6);
	    });
	}
	
	@Test
	public void testOutOfBundOfRemoveNegatifIndex() throws Exception{
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3,4,5);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			integers.remove(-1);
	    });
	}
	
	@Test
	public void testOutOfBundOfRemoveBiggerIndex() throws Exception{
		UnsortedList<Integer> integers = MyUnsortedList.of(1,2,3,4,5);
		assertThrows(IndexOutOfBoundsException.class, () -> {
			integers.remove(6);
	    });
	}
	
	@Test
	public void testEmptyListExceptionOfpop() throws Exception{
		UnsortedList<Integer> integers = MyUnsortedList.of();
		assertThrows(EmptyListException.class, () -> {
			integers.pop();
	    });
	}
	
	@Test
	public void testEmptyListExceptionOfpopLast() throws Exception{
		UnsortedList<Integer> integers = MyUnsortedList.of();
		assertThrows(EmptyListException.class, () -> {
			integers.popLast();
	    });
	}
}
