import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Compilin extends JFrame {
	
	BotonPersonal bot,bot2,bot3,bot4;	
	JTextArea at1,at2, at3;
	JLabel nom;
	JPanel pan1,pan2,pan3,pan4,pan5, pan6;
	Font fon;
	JScrollPane ps1, ps2,ps3;
	JMenuBar bm;
	JMenu men,inf;
	JMenuItem im1,im2, im3,n1,n2,n3;
	ImageIcon img1, img2, img3, img4;
	File ab, xy;
	JProgressBar pb;
	
	public Compilin(){
		super("C0MP1L1N");
		Font f1=new Font("Dungeon",Font.BOLD,28);
		Font f2=new Font("Dungeon",Font.BOLD,10);
		nom= new JLabel("C0MP1L1N",JLabel.CENTER);
		nom.setFont(f1);//aqui cambie
		nom.setForeground(new Color(0x0162B4));
				
		img1= new ImageIcon("imagenes/exit.png");
		img2= new ImageIcon("imagenes/pepper12.png");
		img3= new ImageIcon("imagenes/reproducir.png");
		img4= new ImageIcon ("imagenes/guardar.png");
		
		bm= new JMenuBar();
		men= new JMenu("Opciones");
		men.setIcon(img2);
						
		im1= new JMenuItem("Compilar");
		im1.setIcon(img3);
		im1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b){
				Hilo h=new Hilo();
				h.start();				
			}
		});
		im3= new JMenuItem("Guardar");
		im3.setIcon(img4);
		im3.addActionListener (new ActionListener (){
			public void actionPerformed (ActionEvent a) {
				JFileChooser guardar=new JFileChooser();
				if(guardar.showSaveDialog(at1)==JFileChooser.APPROVE_OPTION){
					File ab=guardar.getSelectedFile();
					//ab= new File("Archivo.cpm");
					if (ab.exists()){
						try {
							FileWriter fw= new FileWriter(ab, true);
							BufferedWriter br= new BufferedWriter(fw);
							PrintWriter pw= new PrintWriter(br);
							pw.println(at1.getText());
							pw.close();
							}
						catch(IOException ioex) {	}					
					}
				
					else{
					
						try{
							FileWriter fw= new FileWriter(ab);
							BufferedWriter br= new BufferedWriter(fw);
							PrintWriter pw= new PrintWriter(br);
							pw.println(at1.getText());
							pw.close();
						
						}
						catch(IOException ioex) {	}
					}
				
					JOptionPane.showMessageDialog (Compilin.this, "El Archivo ha sido Guardado con exito");

				} 
				
			}
		});
				
		im2= new JMenuItem("Salir");
		im2.setIcon(img1);
		im2.addActionListener (new ActionListener(){
			public void actionPerformed(ActionEvent a) {
				
				dispose();			
				
			}
		});
		
		men.add(im1);
		men.add(im3);
		men.add(im2);
		
		bm.add(men);
		
		pb=new JProgressBar(0,3);
		pb.setStringPainted(true);		
		JPanel ppb=new JPanel();
		ppb.add(pb);
		
		bm.add(pb);
		
		setJMenuBar(bm);				
				
		bot= new BotonPersonal("COMPILAR");
		bot.setPreferredSize(new Dimension(80,25));
		bot.setColor1(Color.cyan);
		bot.setFont(f2);
		bot.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b){
				bot4.setEnabled (false);
				Hilo h=new Hilo();
				h.start();				
			}
		});
		
		bot2= new BotonPersonal("GUARDAR");
		bot2.setPreferredSize(new Dimension(80,25));
		bot2.setColor1(Color.cyan);
		bot2.setFont(f2);
		bot2.addActionListener (new ActionListener (){
			public void actionPerformed (ActionEvent a) {
				JFileChooser guardar=new JFileChooser();
				if(guardar.showSaveDialog(at1)==JFileChooser.APPROVE_OPTION){
					File ab=guardar.getSelectedFile();
					//ab= new File("Archivo.cpm");
					if (ab.exists()){
						try {
							FileWriter fw= new FileWriter(ab, true);
							BufferedWriter br= new BufferedWriter(fw);
							PrintWriter pw= new PrintWriter(br);
							pw.println(at1.getText());
							pw.close();
							}
						catch(IOException ioex) {	}					
					}
				
					else{
					
						try{
							FileWriter fw= new FileWriter(ab);
							BufferedWriter br= new BufferedWriter(fw);
							PrintWriter pw= new PrintWriter(br);
							pw.println(at1.getText());
							pw.close();
						
						}
						catch(IOException ioex) {	}
					}
				
					JOptionPane.showMessageDialog (Compilin.this, "El Archivo ha sido Guardado con exito");

				} 
				
			}
		});
		
		bot3=new BotonPersonal("ABRIR");
		bot3.setPreferredSize(new Dimension(80,25));
		bot3.setColor1(Color.cyan);
		bot3.setFont(f2);
		bot3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent c){
				JFileChooser abrir=new JFileChooser();
				if(abrir.showOpenDialog(at1)==JFileChooser.APPROVE_OPTION){
					File ab=abrir.getSelectedFile();
					//ab= new File("Archivo.cpm");
					String salida="";
					String s="";
						try {
							
							FileReader fw= new FileReader(ab);
							BufferedReader br= new BufferedReader(fw);
							
							while((s = br.readLine()) != null){
								salida=salida+s+"\n";
							}
							
							
						}
						catch(IOException ioex) {	}					
						at1.setText(salida);
					
				}

			}
		});
		bot4 = new BotonPersonal ("Mostrar");
		bot4.setColor1(Color.cyan);
		bot4.setEnabled (false);
		bot4.setPreferredSize(new Dimension(80,25));
		bot4.setFont(f2);
		bot4.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent b){
				String a = ManEnsamblado.devolver ();
				JOptionPane.showMessageDialog (Compilin.this,a);				
			}
		});
		at1= new JTextArea(15,25);		
		at2= new JTextArea(5,50);
		at2.setEditable(false);
		//at2.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
		at2.setBackground(Color.black);
		at2.setForeground(Color.white);
		//at3= new JTextArea(5,25); // Tercer panel 
		
		
		ps1= new JScrollPane(at1);
		ps2= new JScrollPane(at2);
		//ps3= new JScrollPane(at3);
		
		pan1= new JPanel();
		pan1.add(nom);
		
		pan2= new JPanel();
		pan2.add(ps1);			
			
		pan3= new JPanel();
		pan3.add(ps2);
	//	pan3.add(ps3);
		
		
		
		pan4= new JPanel(new BorderLayout());
		pan4.add(pan2, BorderLayout.NORTH);
		//pan4.add(ppb, BorderLayout.CENTER);
		pan4.add(pan3, BorderLayout.SOUTH);
		
		pan5= new JPanel();
		pan5.add(bot);
		pan5.add(bot2);
		pan5.add(bot3);
		pan5.add(bot4);
		
		pan6= new JPanel(new BorderLayout());
		pan6.add(pan1, BorderLayout.NORTH);
		pan6.add(pan4, BorderLayout.CENTER);
		pan6.add(pan5, BorderLayout.SOUTH);				
		
		add(pan6);
		setSize(500,500);
		setLocation(50,50);
		setVisible(true);
	}
	
	public class Hilo extends Thread{
		public void run(){
			for(int i=1;i<=3;i++){
				pb.setValue(i);
				try{
					sleep(1000);	
				}catch(InterruptedException ie){
					
				}						
			}
			pb.setValue(0);			
			ManError.limpiar();	
			ManEnsamblado.limpiar ();
			ManResultado.limpiar ();
			Sintactico al=new Sintactico(at1.getText());
			String errores=ManError.devolver();
			if(errores.matches("")){
				at2.setText("");				
				at2.setText("Proceso Completado");
				bot4.setEnabled (true);
				String resul=ManResultado.devolver ();
				if(!resul.matches("")){
					JOptionPane.showMessageDialog (Compilin.this,resul);				
				}
//				at3.setText(ManResultado.devolver ());
			}else{
				at2.setText("");
				at2.setText(errores);			
			}			
		}		
	}
	
	// public static void main(String args[]){
		
	// 	Compilin abc= new Compilin(); 
	// }
	
}