package tests;

import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Diff;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] arr1 = {"2","3"};
		String[] arr2 = {"2","9"};
		
		Javers javers = JaversBuilder.javers().build();
		Diff diff = javers.compare(arr1, arr2);
		
		System.out.println(diff);
	}

}
