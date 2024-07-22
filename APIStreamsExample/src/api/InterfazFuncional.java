package api;

//Esta interfaz no es funcional porque solo tiene un metodo
interface A {
    default void foo(){
       System.out.println("Llamando A.foo()");
    }
}

class Clazz implements A {
}

public class InterfazFuncional {

	public static void main(String[] args) {
		Clazz clazz = new Clazz();
		clazz.foo(); // Llamando a A.foo()
	}

}
