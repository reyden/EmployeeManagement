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
import dataAccess.Appraisal;
/**
 *
 * @author New User
 */
public class Appraisal {
       int emp_id;
       int emprec_id;
       int sal_id;
       int step;
       float actual_salary;
       float adjusted_salary;

       Connection con;
       /*
       employee_id, erec_id, salary_id, 
                    Integer.parseInt(jcbo_step.getSelectedItem().toString().trim()),
                    Float.parseFloat(jcbo_amount.getSelectedItem().toString().trim()));
       */
       
       
       
    public Appraisal(int emp_id, int emprec_id, int sal_id, int step, float actual_salary, float adjusted_salary) {
        this.emp_id = emp_id;
        this.emprec_id = emprec_id;
        this.sal_id = sal_id;
        this.step = step;
        this.actual_salary = actual_salary;
        this.adjusted_salary = adjusted_salary;
        
        
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getEmprec_id() {
        return emprec_id;
    }

    public void setEmprec_id(int emprec_id) {
        this.emprec_id = emprec_id;
    }

    public int getSal_id() {
        return sal_id;
    }

    public void setSal_id(int sal_id) {
        this.sal_id = sal_id;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public float getActual_salary() {
        return actual_salary;
    }

    public void setActual_salary(float actual_salary) {
        this.actual_salary = actual_salary;
    }

    public float getAdjusted_salary() {
        return adjusted_salary;
    }

    public void setAdjusted_salary(float adjusted_salary) {
        this.adjusted_salary = adjusted_salary;
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
    
    public int new_appraisal(){
        int appraise_id = 0;
        String pstmnt = "INSERT INTO TB_appraisal(employee_id, emprecord_id, "
                + "salary_id, step, actual_salary, adjusted_salary) VALUES (?,?,?,?,?,?)";
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try{
            DBConnect();
            ps = con.prepareStatement(pstmnt);
            ps.setInt(1, getEmp_id());
            ps.setInt(2, getEmprec_id());
            ps.setInt(3, getSal_id());
            ps.setInt(4, getStep());
            ps.setFloat(5, getActual_salary());
            ps.setFloat(6, getAdjusted_salary());
            ps.executeUpdate();
            rs=ps.getGeneratedKeys();
            
            while(rs.next()){
                appraise_id = rs.getInt((1));
            }
           // JOptionPane.showMessageDialog(null, "Appraisal id = "+appraise_id);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error in recording appraisal: "+e);
            return 0;
        }finally{
            try{
                ps.close();
                rs.close();
                con.close();
                
            }catch(Exception e){
                
            }
        }
        
        
        
        return appraise_id;
    }
}
