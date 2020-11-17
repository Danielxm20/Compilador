import javax.swing.*;
import java.awt.*;
import java.io.*;


public class Ventana extends JWindow{
	JPanel princ,p1, p2, p3,p4;
	JProgressBar p;
 	ImageIcon ima;
 	JLabel etiq,etiq2,etiq3;

 	public Ventana(){
 		ima=new ImageIcon("imagenes/Transformer.png");
 		p=new JProgressBar(0,5);
 		p.setStringPainted(true);		
 		Font f1=new Font("Stencil", Font.BOLD,20);
 		etiq=new JLabel("Compilin beta 1.0.1.0",JLabel.CENTER);
 		etiq.setFont(f1);
 		etiq2=new JLabel(ima);
 		//etiq2.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
 		etiq3=new JLabel("Ver. Beta 1.0.1.0");
 		princ=new JPanel();
 		princ.setLayout(new BoxLayout(princ,BoxLayout.Y_AXIS));
 		p1=new JPanel();
 		p1.add(etiq);
 		p2=new JPanel();
 		p2.add(etiq2);
 		p2.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
 		p3=new JPanel();
 		p3.add(p);
 		p3.setOpaque(false);
 		p3.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
 		p4=new JPanel(new BorderLayout ());
 		p4.add(etiq3,BorderLayout.EAST);
 		princ.add(p1);
 		princ.add(p2);
 		princ.add(p3);
 		princ.add(p4);

 		add(princ);
 		setSize(400,400);
 		setLocation(300,200);
 		setVisible(true);

 		Hilillo h=new Hilillo();
 		h.start();


 	}

 	public class Hilillo extends Thread {
 		public void run(){
 			for (int i=1;i<=5;i++){
 				p.setValue(i);
 				try{
 					sleep(1000);

 				}
 				catch(InterruptedException e){
  				}

 			}
 			dispose();
 			Compilin abre=new Compilin();
 		}
 	}
 	public static void main(String s[]){
 		JFrame.setDefaultLookAndFeelDecorated(true); 
		JDialog.setDefaultLookAndFeelDecorated(true);
 		try{
 			//UIManager.setLookAndFeel( new com.nilo.plaf.nimrod.NimRODLookAndFeel());	
 			//UIManager.setLookAndFeel(new de.javasoft.plaf.synthetica.SyntheticaStandardLookAndFeel()); 			 			 			
 			 UIManager.setLookAndFeel("com.easynth.lookandfeel.EaSynthLookAndFeel"); 			 
 		}catch(UnsupportedLookAndFeelException ue){
 			System.out.println(ue.getMessage());
 		} 		 			
 		catch(Exception ne){
 			System.out.println(ne.getMessage());
 		} 		 			 		
 		Ventana obj=new Ventana();
 	}
}