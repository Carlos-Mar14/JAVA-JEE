package api;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//Interfaz funcional Predicate
class FiltrarPares implements Predicate<Integer> {

	@Override
	public boolean test(Integer t) {

		return t % 2 == 0;
	}

}

//Interfaz funcional Function
class Doblaje implements Function<Integer, Integer> {

	@Override
	public Integer apply(Integer t) {

		return t * 2;
	}

}

public class Anatomia {

	public static void main(String[] args) {

		// Source = la fuente
		List<Integer> nums = List.of(6, 9, 3, 4, 5, 1, 7, 8, 2, 10);

		// Obtener el stream a aprtir de la fuente
		Stream<Integer> stream = nums.stream();

		// Pipeline
		nums = stream.filter(new FiltrarPares()).map(new Doblaje()).sorted().collect(Collectors.toList());

		
/*
		// Funtion Lambda
		nums = stream.filter(num -> num % 2 == 0).map(num -> num * 2).sorted().collect(Collectors.toList());
*/
		
		nums.forEach(System.out::println);
		
	}

}
