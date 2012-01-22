/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmii.data;

import java.util.ArrayList;

/**
 *
 * @author Dan Burger
 */
public class StudentInfo {
    
    String firstName;
    String lastName;
    int classPeriod;
    String password;
    boolean started;
    boolean completed;
    ArrayList<String> log;
    
    public StudentInfo()
    {
        setName("first-name", "last-name");
        password = "";
        log = new ArrayList<String>();
    }
    
    public void setName(String fn, String ln)
    {
        firstName = fn;
        lastName = ln;
    }
    
    //note: separated first name and last name
    public String getFirstName()
    {
        return firstName;
    }
    
    public String getLastName()
    {
        return lastName;
    }
    
    public String getName()
    {
        return firstName + " " + lastName;
    }
    
    public ArrayList<String> getLog()
    {
        return log;
    }
    
    public void setClassPeriod(int period)
    {
        classPeriod = period;
    }
    
    public int getClassPeriod()
    {
        return classPeriod;
    }
    
    public void setPassword(String oldPwd, String newPwd)
    {
        if(oldPwd.equals(newPwd))
        {
            password = newPwd;
        }
    }
    
    public boolean matchPassword(String test)
    {
        return(test.equals(password));
    }
    
    public void writeToLog(String msg)
    {
        log.add(msg);
    }
}
