public class ManResultado{
	
	static String salida = "";
	
	public static void agrega (String s){
		
		salida = salida + s + "\n";
	}
	
	public static void limpiar (){
		
		salida = "";
	}
	
	public static String devolver (){
		
		return salida;
	}
}