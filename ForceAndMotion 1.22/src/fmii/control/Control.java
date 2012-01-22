package fmii.control;

import fmii.MainApp;
import fmii.io.Authorization;
import java.util.*;

//generic NetLogo-compatible control class
public abstract class Control {
    
    public static Authorization authorization;
    
    //send command to NetLogo
    public void command(final String str)
    {
        new Thread(new Runnable(){public void run(){
            try {
               MainApp.netLogo.command(str);
               authorization.writeToLog(str);
            } 
            catch (Exception e){
               e.printStackTrace();
            }
        }}).start();
    }
/*            
    public void receive_input(List<Object> user_input)
    {
        
    }
    
    public String perror(String str)
    {
        return str;
    }
 */   
}
