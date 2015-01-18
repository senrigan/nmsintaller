package intaller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import jwf.Wizard;
import jwf.WizardContext;
import jwf.WizardPanel;
import net.miginfocom.swing.MigLayout;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXFrame;

public class WizardInfo extends JXFrame{

	  public WizardInfo(){
		  createComponents();
	  }
	  
	  public void createComponents(){
		  //this.setLayout(new MigLayout("fill"));
		  this.setSize(new Dimension( 450, 200 ));
		  //add(new JXButton("1"), "dock east, width 120");
	        /*add(new JXButton("2"), "west");
	       // add(new JXButton("3"), "center");
	        add(new Wizard(),"center");
	        add(new JXButton("4"), "north,grow");
	        */
	        
	        add(new JXButton("2"), BorderLayout.WEST);
		       // add(new JXButton("3"), "center");
		        add(new Wizard(),BorderLayout.CENTER);
		        add(new JXButton("4"),BorderLayout.NORTH);
	        //add(new JXButton("5"), "south");
	        this.setResizable(true);
	        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        this.setVisible(true);
	        

	  }
	  public static void main(String[] args) {
		WizardInfo in=new WizardInfo();
	
	}
}
