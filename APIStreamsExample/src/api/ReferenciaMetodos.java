package api;

import java.util.List;

public class ReferenciaMetodos {
	public static void main(String[] args) {
		List<String> nombres = List.of(
			"Juan", "Carlos", "Lamin");
		nombres.forEach(e -> System.out.println(e)); //Lambda expression
		
		nombres.forEach(System.out::println);// Reference Method
	}
}
