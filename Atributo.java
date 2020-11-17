public class Atributo extends Object {
	
	private int tipo;
	private String valor;
	
	public void attLimpiar () {
		tipo=0;
		valor="";
	}
	
	public Atributo () {
		attLimpiar();
		
	}
	
	public void setTipo(int t) {
		tipo=t;
	}
	
	public int getTipo () {
		return tipo;
	}
	
	public void setValor (String v) {
		valor=v;
	}
	
	public int getValorI () {
		int valorI=Integer.parseInt(valor);
		return valorI;
	}
}