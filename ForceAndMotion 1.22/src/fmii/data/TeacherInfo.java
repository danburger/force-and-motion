/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmii.data;

/**
 *
 * @author Dan Burger
 */
public class TeacherInfo {
    
    String firstName;
    String lastName;
    String password;
    
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
}
