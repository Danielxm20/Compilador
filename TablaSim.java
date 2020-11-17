import java.util.Hashtable;

public class TablaSim{
	
	Atributo att;
	Hashtable ht;
	String ultimoId;
	
	public TablaSim (){
		ht = new Hashtable ();
		ultimoId = "nulo";
	}
	public void agrega (String id){
		att= new Atributo ();
		att.attLimpiar();
		att.setValor ("0");
		att.setTipo (200);

		if (!ht.containsKey (id)){
			if (Sintactico.declaracion){
				//att= new Atributo ();
				//att.attLimpiar();
				//att.setValor ("0");
				//att.setTipo (0);
				ht.put(id,att);	
			}
			else{
				ht.put(id,att);	
				ManError.agrega ("La variable no esta declarada");
			}
		}
		else{
			if (Sintactico.declaracion){
				
				ManError.agrega ("La variable esta duplicada");
			}
		}
		ultimoId = id;
	}
	public void setTipo (int t){
		Atributo a = (Atributo)ht.get(ultimoId);
		a.setTipo (t);
		ht.remove(ultimoId);
		ht.put(ultimoId,a);
	}
	public void setValor (String valor){
		Atributo a = (Atributo)ht.get(ultimoId);
		a.setValor (valor);
		ht.remove(ultimoId);
		ht.put(ultimoId,a);
	}
	public int getTipo (){
		Atributo a = (Atributo)ht.get(ultimoId);
		return a.getTipo();
	}
	public int getValorI (){
		Atributo a = (Atributo)ht.get(ultimoId);
		return a.getValorI ();
	}	
}