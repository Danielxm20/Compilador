public class Sintactico extends ALexico{
	
	int token;
	static boolean declaracion = false;
	boolean error = false;
	
	public Sintactico (String codigo){
		super (codigo); // se le pasa el codigo del lexico al sintactio
		token = devuelveToken ();
		System.out.println(token);
		ManEnsamblado.agrega (""+token);
		compilin ();
	}
	public void compilin (){
		boolean inicio = true;
		while (inicio){
			if (token == Tokens.INICIO){
				token=devuelveToken();
				System.out.println(token);
				ManEnsamblado.agrega (""+token);
				instruccion();																	
				if (token != Tokens.FIN){
					if (token==Tokens.EOF){						
						inicio=false;												
					}else{
						System.out.println("No seas buey te falta poner '?'");							
						ManError.agrega("No seas buey te falta poner '?'");	
						inicio=false;	
					}																					
				}
				else{
					token = devuelveToken ();
					System.out.println(token);
					ManEnsamblado.agrega (""+token);
					if (token==Tokens.EOF){						
						inicio=false;												
					}					
				}
			}else{
				ManError.agrega("falta el simbolo inicial '¿'");	
				inicio=false;										
			}
		}
	}
	public boolean primeroInst(int t) {		
		if (t==Tokens.ENTERO){			
			return true;			
		}		
		else {
			if(t==Tokens.IDEN) {		
				return true;				
			}
			else {
				if (t==Tokens.VOUT){					
					return true;					
				}
				else {
					return false;
				}
			}			
		}
	}
	public void simboloTipo (AuxInt t){
		if(token == Tokens.ENTERO){
			t.setInt (token);			
		}
	}
	public void instruccion (){
		boolean salida = false;
		while (!salida){
			//System.out.println("estoy en instruccion");				
			if(primeroInst (token)){
				simboloI (); 
				//System.out.println("sali de simboloI");								 
				if(token != Tokens.PUNTO_COMA){					
					if(token != Tokens.EOF){
						ManError.agrega("!ERROR¡, falta ';'");
						salida=true;	
					}else{
						salida=true;
					}															
				}
				else{
					//System.out.println("estoy 3");
					token = devuelveToken();
					System.out.println(token);
					ManEnsamblado.agrega (""+token);
					//salida=true;
				}
			}
			else{				
				if(token==Tokens.PAREN_ABRE){
					ManError.agrega("!ERROR¡, falta 'VOUT'");
					//while(token!=Tokens.FIN){
						//token=devuelveToken();					
					//}
					token=Tokens.EOF;		
					salida=true;
				}else{
					salida=true;	
				}				
			}
		}		
	}
	public boolean primeroTipo (int t){
		if(t ==Tokens.ENTERO){			
			return true;
		}
		else{
			return false;
		}
	}
	public void simboloI (){
		if (primeroTipo (token)){
			AuxInt t = new AuxInt ();
			simboloTipo (t);
			declaracion = true;
			int tv = t.getInt ();
			simboloL (tv);  
			declaracion = false;
		}
		else{
			if (token == Tokens.IDEN){
				AuxInt idTipo = new AuxInt ();
				//System.out.println(tSim.getTipo());
				idTipo.setInt (tSim.getTipo());
				token = devuelveToken();
				System.out.println(token);
				ManEnsamblado.agrega (""+token);
				if (token != Tokens.ASIGNACION){
					ManError.agrega("Se esperaba ''=");					
					token=Tokens.EOF;
				}
				else{
					token = devuelveToken ();
					System.out.println(token);
					ManEnsamblado.agrega (""+token);
					AuxInt etipo = new AuxInt ();
					AuxInt valor = new AuxInt ();
					simboloE (etipo,valor);  //Simbolo expresion por hacer
					tSim.setValor (""+valor.getInt ());
					if (idTipo.getInt () != etipo.getInt ()){
						error = true;
						ManError.agrega("Tipo de datos diferentes");						
					}
				}
			}
			else{
				if (token == Tokens.VOUT){
					token = devuelveToken ();
					System.out.println(token);
					ManEnsamblado.agrega (""+token);
					if (token != Tokens.PAREN_ABRE){
						ManError.agrega("Se esperaba '('");						
					}
					//else{
						token = devuelveToken ();
						System.out.println(token);
						AuxInt eTipo = new AuxInt ();
						AuxInt eValor = new AuxInt ();
						simboloE (eTipo,eValor);
						if (error){
							ManError.agrega("No se muestra el error por ser diferentes los tipos de datos");
							error = false;							
						}
						else{
							ManResultado.agrega(""+ eValor.getInt ());
						}
						if (token != Tokens.PAREN_CIERRA){
							ManError.agrega("Se esperaba ')'");
						}
						token = devuelveToken ();
						System.out.println (token);
						ManEnsamblado.agrega (""+token);
						
					//}
				}
			}
		}
	}
	
	public void simboloL(int t){
		boolean salida=false;
		token=devuelveToken();
		System.out.println(token);
		ManEnsamblado.agrega (""+token);
		while(!salida){
			if(token!=Tokens.IDEN){				
				ManError.agrega("Se esperaba identificador");
				salida=true;			
			}
			else{
				tSim.setTipo(t);
				//System.out.println(t);
				token=devuelveToken();
				System.out.println(token);
				ManEnsamblado.agrega (""+token);
				if(token==Tokens.COMA){
					token=devuelveToken();
					System.out.println (token);
					ManEnsamblado.agrega (""+token);				
				}
				else{
					salida=true;
				}
			}
		}
	}
	
public void simboloE(AuxInt eTipo, AuxInt valor){
	boolean salida=false;
	boolean enTipo=false;
	boolean suma=false;
	AuxInt v1 = new AuxInt ();
	AuxInt v2 = new AuxInt ();
	int tipo=0;
	int tok = Tokens.SUMA;
	
	while(!salida){
		simboloT(eTipo, valor);  //********* Parece que falta el metodo SimboloT ************
		if(enTipo){
			if(tipo!=eTipo.getInt() ){
				error=true;
				ManError.agrega("Los tipos de los operandos en la operacion son diferentes");
			}
		}
		else{
			tipo=eTipo.getInt();
		}
		if (token==Tokens.SUMA || token==Tokens.RESTA){
			if (token==Tokens.SUMA) {
				tok=Tokens.SUMA;		
			}
			else{
				tok=Tokens.RESTA;				
			}
			if(!suma){
				v1.setInt(valor.getInt() );
				suma=true;
			}
			else{
				v2.setInt(valor.getInt() );
				valor.setInt(hazOP(v1.getInt(), v2.getInt(), tok ));
				v1.setInt(valor.getInt() );
			}
			token=devuelveToken();
			System.out.println(token);
			ManEnsamblado.agrega (""+token);
			enTipo=true;			
		}
		else{
			salida=true;
			valor.setInt(hazOP(v1.getInt(), valor.getInt(), tok ));
		}
	}
}
    public int hazOP(int a, int b, int OP){
	if(OP==Tokens.SUMA){		
		return a+b;
	}
	else{
		if(OP==Tokens.RESTA){		
			return a-b;
		}
		else{
			return 0;
		}
	}
}
	public int hazOP2(int c, int d, int OP2){
	if(OP2==Tokens.MULT){		
		return c*d;
	}
	else{
		if(OP2==Tokens.DIV){	
			return c/d;
		}
		else{
			return 0;
		}
	}
}

public void simboloT(AuxInt eTipo,AuxInt valor){
	boolean salida=false;
	boolean enTipo=false;
	boolean multi=false;
	AuxInt v1=new AuxInt();
		   v1.setInt(1);
	AuxInt v2=new AuxInt();
		   v2.setInt(1);
	int tipo=0, tok=Tokens.MULT;
	while(!salida){
		simboloF(eTipo,valor);
		if(enTipo){
			if(tipo!=eTipo.getInt()){
				error=true;
				ManError.agrega("Los tipos de los operandos son diferentes");
			}
		}
		else{
			tipo=eTipo.getInt();
		}
		if(token==Tokens.MULT || token==Tokens.DIV){
			if(token==Tokens.MULT){
				tok=Tokens.MULT;				
			}
			else{
				tok=Tokens.DIV;
			}
			if(!multi){
				v1.setInt(valor.getInt());
				multi=true;				
			}
			else{
				v2.setInt(valor.getInt());
				valor.setInt(hazOP2(v1.getInt(),v2.getInt(),tok));
				v1.setInt(valor.getInt());
			}
			token=devuelveToken();
			System.out.println(token);
			ManEnsamblado.agrega (""+token);
			enTipo=true;			
		}
		else{
			salida=true;
			valor.setInt(hazOP2(v1.getInt(),valor.getInt(),tok));
		}
	}
}

public void simboloF(AuxInt eTipo, AuxInt valor){
	if(token==Tokens.IDEN){
		eTipo.setInt(tSim.getTipo());
		//System.out.println(tSim.getTipo());
		valor.setInt(tSim.getValorI());
		token=devuelveToken();
		System.out.println (token);
		ManEnsamblado.agrega (""+token);
	}
	else{
		if(token==Tokens.NUMERO){
			eTipo.setInt(Tokens.ENTERO);
			valor.setInt(sacaNumero());
			token=devuelveToken();
			System.out.println (token);
			ManEnsamblado.agrega (""+token);
		}
		else{
			if(token==Tokens.PAREN_ABRE){
				token=devuelveToken();
				System.out.println(token);
				ManEnsamblado.agrega (""+token);
				simboloE(eTipo, valor);
				if(token!=Tokens.PAREN_CIERRA){
					ManError.agrega("Se esperaba ')'");					
				}
				else{
					token=devuelveToken();
					System.out.println (token);
					ManEnsamblado.agrega (""+token);
				}
			}
			else{
				ManError.agrega("Se esperaba 'id' o 'numero' o '('");
			}
		}
	}
}

public static void main(String args[]){
		//Sintactico al=new Sintactico("¿ ent &a;?");
		//Sintactico al=new Sintactico("¿ ent &a; &a=5;?");
		Sintactico al=new Sintactico("¿ &b,&a;?");
		String errores=ManError.devolver();
		if(errores.matches("")){
			System.out.println("Proceso Completado");
		}else{
			System.out.println(errores);
		}
	}       	
}