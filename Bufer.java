public class Bufer {
	
	String codigo;
	int indi, indf;
	
	public Bufer (String cod) {
		
		codigo=cod+'$';
		indi=0;
		indf=0;
		
	}
	
	public char leesim(){
		
		char c=0;
		if (indf<codigo.length()) {
			c=codigo.charAt(indf);
			indf=indf+1;
		}
		return c;
	}
	
	public String leeCadena () {
		String cadena="";
		for (int i=indi; i<indf; i++) {
			if(codigo.charAt(i)!=' ') {
				cadena=cadena+codigo.charAt(i);
			}
		}
		return cadena;
	}
	
	public void decind() {
		indf=indf-1;
	}
	public void incInd(){
		indi=indf-1; // Nota= se va a cambiar a +1
	}
	
}