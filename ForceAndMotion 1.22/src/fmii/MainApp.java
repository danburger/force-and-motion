/*
 * ForceAndMotionApp.java
 */

package fmii;

import fmii.control.Control;
import fmii.io.Authorization;
import fmii.ui.Authorization_Interface;
import fmii.ui.Model_Editor_Interface;
import fmii.ui.Simulation_Interface;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;
import org.nlogo.api.CompilerException;
import org.nlogo.lite.InterfaceComponent;

import java.awt.event.WindowAdapter;  
import java.awt.event.WindowEvent;  

/**
 * The main class of the application.
 */
public class MainApp extends SingleFrameApplication {
    
    public static InterfaceComponent netLogo;
    static Model_Editor_Interface modelInterface;
    static Simulation_Interface simulationInterface;
    
    static MainView mainView;
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        mainView = new MainView(this);
        show(mainView);
    }
    
    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {}

    /**
     * A convenient static getter for the application instance.
     * @return the instance of ForceAndMotionApp
     */
    public static MainApp getApplication() {
        return Application.getInstance(MainApp.class);
    }

    /**
     * Main method launching the application.
     */
    public static void main(String[] args) {
        launch(MainApp.class, args);
        
       JFrame mainFrame = MainApp.getApplication().getMainFrame();
       netLogo = new InterfaceComponent(mainFrame); 
       modelInterface = new Model_Editor_Interface();
       modelInterface.setVisible(true);
       simulationInterface = new Simulation_Interface();
       simulationInterface.setVisible(true);
       
       
       String first_name = JOptionPane.showInputDialog("What is your FIRST name?");
       String last_name =  JOptionPane.showInputDialog("What is your LAST name?");
       
       Control.authorization = new Authorization();
       Control.authorization.addStudent(first_name, last_name, 1);
       Control.authorization.login(first_name, last_name);
       
       /*
       Authorization_Interface authInt = new Authorization_Interface();
       JFrame authFrame = new JFrame();
       authFrame.setLayout(new GridLayout(1,1));
       authFrame.add(authInt);
       authFrame.resize(400,300);
       authFrame.setVisible(true);
       authInt.revalidate();
         */       
       
        try 
        {
            javax.swing.SwingUtilities.invokeAndWait( new Runnable(){public void run(){
                   try
                   {
                       //add netlogo to the stage
                       mainView.outputPanel.setLayout(new GridLayout(1,1));
                       mainView.outputPanel.remove(mainView.loadingLabel);
                       mainView.outputPanel.add(netLogo);
                       mainView.outputPanel.revalidate();
                       netLogo.open("fmii.nlogo");
                       
                       //add interfaces to the stage
                       mainView.simulationPane.setLayout(new GridLayout(1,1));
                       mainView.simulationPane.add(simulationInterface);
                       mainView.simulationPane.revalidate();
                       
                       mainView.modelPane.setLayout(new GridLayout(1,1));
                       mainView.modelPane.add(modelInterface);
                       mainView.modelPane.revalidate();
                       
                   }
                   catch (Exception e){
                       e.printStackTrace();
                   }
               }
           });

           netLogo.command("init");
           netLogo.command("setup");
          
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
