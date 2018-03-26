import java.util.Iterator;

/*
 *  @Author Nick Foster
 *  @Date September 2017
 *  
 *  Simple class that makes use of the ArrayBag class.
 */

public class UseArrayBag {
	public static void main(String[] args) {
		Bag<String> myBag = new ArrayBag<String>();
		
		myBag.add("Apple");
		myBag.add("Banana");
		myBag.add("Cherry");
		myBag.add("Donut");
		myBag.add("Eggs");
		
		Iterator<String> it = myBag.iterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

}
