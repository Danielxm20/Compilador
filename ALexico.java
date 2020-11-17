public class ALexico {
	
	Bufer bufer;
	boolean entrada=false;
	String numero;
	TablaSim tSim;
	
	public ALexico (String codigo) {
		
		bufer=new Bufer(codigo);
		tSim = new TablaSim ();
	}
	
	public boolean inicio(char c) {
		
		String d="";
		if (c=='¿'){
			return true;
		}
		return false;
	}
	
	public boolean fin(char c){
		
		String d="";
		if(c=='?') {
			return true;
		}
		return false;
	}
	
	public int devuelveToken() {
				
		int token=0;
		char c;
		while(true) {
			c=bufer.leesim();
			if (entrada) {
				if (numero (c)) {									
					return Tokens.NUMERO;
				}
				else {
					if(c==';'){
						return Tokens.PUNTO_COMA;
					}
					else{
						if(c==','){
							return Tokens.COMA;
						}
						else{
							if(c=='='){
								return Tokens.ASIGNACION;
							}
							else{
								if(c=='+'){
									return Tokens.SUMA;
								}
								else{
									if(c=='-'){
										return Tokens.RESTA;
									}
									else{
										if(identificador(c)){
											tSim.agrega(bufer.leeCadena ());
											System.out.println(bufer.leeCadena());
											return Tokens.IDEN;
										}
										else{
											if(c=='('){
												return Tokens.PAREN_ABRE;
											}
											else {
												if(c==')'){
													return Tokens.PAREN_CIERRA;
												}
												else {
													if(vout(c)){
														return Tokens.VOUT;	
													}
													else {
														if(entero(c)){
															return Tokens.ENTERO;
														}
														else{
															if(fin (c)) {
																entrada=false;
																return Tokens.FIN;
															}else {
																if (c=='$'){
																	return Tokens.EOF;
																}
															}
														}
													}
												}
											}															
										}
									}
								}
							}
						}
					}					
				}
			}
			else {
				if(inicio(c)) {
					entrada=true;
					return Tokens.INICIO;
				}else{					
					if (c=='$'){
						return Tokens.EOF;
					}					
				}				
			}
			
		}
	}
	
	public boolean digito (char c){
		if(c>='0' && c<='9') {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean numero(char c) {
		
		String num="";
		if (digito(c)) {
			while (digito(c)) {
				num=num+c;
				c=bufer.leesim();
			}
			
			bufer.decind();
			meteNum(num);
			return true;
		}
		else{
			return false;
		}
	}
	
	public void meteNum(String n) {
		
		numero=n;
	}
	
	public int sacaNumero() {
		
		return Integer.parseInt(numero);
	}
	
	public boolean numeros (char c) {
		
		String num="";
		if (digito(c)){
			while(digito(c)) {
				num=num+c;
				c=bufer.leesim();
			}
			
			bufer.decind();
			meteNum(num);
			return true;
		}
		
		else{
			return false;
		}
	}
	
	private boolean vout(char c){
		if(c=='v')
		{
			c=bufer.leesim();
			if(c=='o')
			{
				c=bufer.leesim();
				if(c=='u')
				{
					c=bufer.leesim();
					if(c=='t')
					{						
						return true;
					}
					else{						
						return false;
					}																									
				}
				else return false;	
			}
			else bufer.decind();return false;
		}
		else return false;				
	}
	
	private boolean identificador(char c){
		if(c=='&'){
			bufer.incInd();
			c=bufer.leesim();
			if(letra(c)){
				c=bufer.leesim();
				while(letra(c) || digito(c))
					c=bufer.leesim();
				bufer.decind();
				return true;
			}
			bufer.decind();
			return false;
		}
		else
			return false;
	}
	
	private boolean letra(char c){
		if(c>='a' && c<='z')
			return true;
		else
			return false;
	}
	
	public boolean entero( char c){
		if(c=='e'){
			c=bufer.leesim();
			if(c=='n'){
				c=bufer.leesim();
				if(c=='t'){
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String args[]){
		Sintactico al=new Sintactico("¿ ent &a;?");
		
				
	}
}