/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fmii.io;

import fmii.data.StudentInfo;
import fmii.data.TeacherInfo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author Dan Burger
 */
public class Authorization {
    ArrayList<StudentInfo> students;
    ArrayList<TeacherInfo> teachers;
    StudentInfo currentStudent;
    TeacherInfo currentTeacher;
    
    public Authorization()
    {
        students = new ArrayList<StudentInfo>();
        teachers = new ArrayList<TeacherInfo>();
    }
    
    public void addStudent(String firstName, String lastName, int classPeriod)
    {
        StudentInfo si = new StudentInfo();
        si.setName(firstName, lastName);
        si.setClassPeriod(classPeriod);
        students.add(si);
    }
    
    public void deleteStudent(String firstName, String lastName)
    {
        for(int i = 0; i<students.size(); i++)
        {
            if(students.get(i).getFirstName().equalsIgnoreCase(firstName) &&
                    students.get(i).getLastName().equalsIgnoreCase(lastName))
            {
                students.remove(i);
            }
        }
    }
    
    // note: added parameters firstName and lastName
    public StudentInfo findStudent(String firstName, String lastName)
    {
        for(int i = 0; i<students.size(); i++)
        {
            if(students.get(i).getFirstName().equalsIgnoreCase(firstName) &&
                    students.get(i).getLastName().equalsIgnoreCase(lastName))
            {
                return students.get(i);
            }
        }
        return null;
    }
    
    // note: added findTeacher
    public TeacherInfo findTeacher(String firstName, String lastName)
    {
        for(int i = 0; i<teachers.size(); i++)
        {
            if(teachers.get(i).getFirstName().equalsIgnoreCase(firstName) &&
                    teachers.get(i).getLastName().equalsIgnoreCase(lastName))
            {
                return teachers.get(i);
            }
        }
        return null;
    }
    
    public boolean exportStudentData()
    {
        try
        {
            PrintStream out = new PrintStream(new FileOutputStream(
                    "log for " + currentStudent.getFirstName() + " " + currentStudent.getLastName() + ".txt"));
            
            out.println(currentStudent.getLog());
            
            out.close();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    
    // note: returns true or false depending on success
    public boolean saveData()
    {
        // ref: http://www.exampledepot.com/egs/java.io/SerializeObj.html
        try
        {
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream("students.data"));
            out.writeObject(students);
            out.close();
            
            out = new ObjectOutputStream(new FileOutputStream("teachers.data"));
            out.writeObject(teachers);
            out.close();
            
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    // note: returns true or false depending on success
    public boolean loadData()
    {
        // ref: http://www.exampledepot.com/egs/java.io/DeserializeObj.html
        try
        {
            File file = new File("students.data");
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            // Deserialize the object
            students = (ArrayList<StudentInfo>) in.readObject();
            in.close();
            
            file = new File("teachers.data");
            in = new ObjectInputStream(new FileInputStream(file));
            // Deserialize the object
            teachers = (ArrayList<TeacherInfo>) in.readObject();
            in.close();
            
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
    
    // note: changed id to firstName and lastName
    public boolean login(String firstName, String lastName)
    {
        return login(firstName, lastName, "");
    }
    
    public boolean login(String firstName, String lastName,
            String pwd)
    {
        StudentInfo s = findStudent(firstName, lastName);
        if (s != null && s.matchPassword(pwd))
        {
            currentStudent = s;
            return true;
        }
        
        TeacherInfo t = findTeacher(firstName, lastName);
        if (t != null && t.matchPassword(pwd))
        {
            currentTeacher = t;
            return true;
        }
        
        return false;
    }
    
    public void logout()
    {
        currentStudent = null;
        currentTeacher = null;
    }
    
    public void writeToLog(String msg)
    {
        currentStudent.writeToLog(msg);
    }
    
    public void addTeacher(String firstName, String lastName)
    {
        TeacherInfo ti = new TeacherInfo();
        ti.setName(firstName, lastName);
        teachers.add(ti);
    }
    
    public void deleteTeacher(String firstName, String lastName)
    {
        for(int i = 0; i<teachers.size(); i++)
        {
            if(teachers.get(i).getFirstName().equalsIgnoreCase(firstName) &&
                    teachers.get(i).getLastName().equalsIgnoreCase(lastName))
            {
                teachers.remove(i);
            }
        }
    }
    
    public ArrayList<String> getAttendance()
    {
        ArrayList<String> attendance = new ArrayList<String>();
        for(int i = 0; i<students.size(); i++)
        {
            attendance.add(students.get(i).getName());
        }
        return attendance;
    }
    
    public ArrayList<String> getLog(String firstName, String lastName)
    {
        for(int i = 0; i<students.size(); i++)
        {
            if(students.get(i).getFirstName().equalsIgnoreCase(firstName) &&
                    students.get(i).getLastName().equalsIgnoreCase(lastName))
            {
                return students.get(i).getLog();
            }
        }
        return null;
    }
}
