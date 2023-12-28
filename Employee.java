/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author New User
 */
public class Employee {
    String firstname;
    String middlename;
    String lastname;
    String name_extension;
    String birthdate;
    String civil_status;
    String sex;
    //String cs;
    String employee_num;
    String fds;
    String highest_educ;
    
    Connection con;
    
    public Employee() {
    }
    
    public  Employee(String lastname, String firstname, String middlename, String name_extension, 
            String birthdate,  String sex, String cs, String employee_num, String fds) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.middlename = middlename;
        this.name_extension = name_extension;
        this.birthdate = birthdate;
        this.sex = sex;
        this.civil_status = cs;
        this.employee_num = employee_num;
        this.fds = fds;
    }

    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName_extension() {
        return name_extension;
    }

    public void setName_extension(String name_extension) {
        this.name_extension = name_extension;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCivil_status() {
        return civil_status;
    }

    public void setCivil_status(String civil_status) {
        this.civil_status = civil_status;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmployee_num() {
        return employee_num;
    }

    public void setEmployee_num(String employee_num) {
        this.employee_num = employee_num;
    }

    public String getFds() {
        return fds;
    }

    public void setFds(String fds) {
        this.fds = fds;
    }

        
    public int AddEmployee(){
        int emp_id = 0;
        String insertNewData = "INSERT INTO TB_employee( lastname, firstname, middlename, "
                + "name_extension, birthdate, sex, civil_status, "
                + "employee_no, ofds)values(?,?,?,?,?,?,?,?,?)";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            //JOptionPane.showMessageDialog(null, "Birthday   = "+this.getBirthdate());
            DBConnect();
            ps=con.prepareStatement(insertNewData);
            ps.setString(1, this.getLastname());
            ps.setString(2, this.getFirstname());
            ps.setString(3, this.getMiddlename());            
            ps.setString(4, this.getName_extension());
            ps.setString(5, this.getBirthdate());
            ps.setString(6, this.getSex());
            ps.setString(7, this.getCivil_status());
            ps.setString(8, this.getEmployee_num());
            ps.setString(9, this.getFds());
            
            ps.executeUpdate();
            ResultSet res = ps.getGeneratedKeys();
      
           while (res.next()) {
              emp_id = Integer.parseInt(res.getString(1));
  
            }
            
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "This is error   " + ex);
        }finally{
            try {
                ps.close();
                con.close();
                
                //rs.close();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error exists " + ex);
                return 0;
               // Logger.getLogger(NewRecord.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return emp_id;
    }
    
    public Connection DBConnect(){
       
            try{
		Class.forName("org.sqlite.JDBC");
                    con = DriverManager.getConnection("jdbc:sqlite:C:\\JavaProj\\NTP201\\src\\ntp201\\db\\ntp201.db");		
                }
                catch(ClassNotFoundException | SQLException e)
                {
                       
			JOptionPane.showMessageDialog(null, "Error in getConnection: "+e);
		}
                
		return con;
}
}
