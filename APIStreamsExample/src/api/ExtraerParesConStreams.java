package api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtraerParesConStreams {
	
	public static void main(String[] args) {
		
		List<Integer> numeros = new ArrayList<>();
		for (int i=0; i<100; i++)
			numeros.add(i);
		
		
		//*************************************************
		
		/*List<Integer> pares = 
				numeros.stream()
					.filter(n -> n % 2 == 0)
						.collect(Collectors.toList());*/
		
		
		
		Stream<Integer> stream = numeros.stream();
		
		/*@FunctionalInterface
		public interface Predicate<T> {
			boolean test(T t);
		}*/

		
		
		List<Integer> pares = stream.filter(n -> n % 2 == 0).limit(25).collect(Collectors.toList());
		
		System.out.println(pares);
	}
}


