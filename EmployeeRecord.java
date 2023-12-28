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
public class EmployeeRecord {
                int employee_id;
                int salary_id;
                float actual_salary;
                float adjusted_salary;
                String item_no;
                String job_title;
                String unit;
                String last_promotion;
                String promotion_date;
                String work_status;
                String appointment_nature;
                String highest_education;
                Connection con;

    public EmployeeRecord(int employee_id, int salary_id /*, float actual_salary, float adjusted_salary*///
            , String item_no, String job_title, String unit, String last_promotion, String promotion_date
            , String work_status, String appointment_nature, String highest_education) {
        this.employee_id = employee_id;
        this.salary_id = salary_id;
        //this.actual_salary = actual_salary;
       // this.adjusted_salary = adjusted_salary;
        this.item_no = item_no;
        this.job_title = job_title;
        this.unit = unit;
        this.last_promotion = last_promotion;
        this.promotion_date = promotion_date;
        this.work_status = work_status;
        this.appointment_nature = appointment_nature;
        this.highest_education = highest_education;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getSalary_id() {
        return salary_id;
    }

    public void setSalary_id(int salary_id) {
        this.salary_id = salary_id;
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

    public String getItem_no() {
        return item_no;
    }

    public void setItem_no(String item_no) {
        this.item_no = item_no;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getLast_promotion() {
        return last_promotion;
    }

    public void setLast_promotion(String last_promotion) {
        this.last_promotion = last_promotion;
    }

    public String getPromotion_date() {
        return promotion_date;
    }

    public void setPromotion_date(String promotion_date) {
        this.promotion_date = promotion_date;
    }

    public String getWork_status() {
        return work_status;
    }

    public void setWork_status(String work_status) {
        this.work_status = work_status;
    }

    public String getAppointment_nature() {
        return appointment_nature;
    }

    public void setAppointment_nature(String appointment_nature) {
        this.appointment_nature = appointment_nature;
    }

    public String getHighest_education() {
        return highest_education;
    }

    public void setHighest_education(String highest_education) {
        this.highest_education = highest_education;
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
  
    public int NewRecord(){
        int erec_id = 0;
        String pstmt = "INSERT INTO TB_employee_record( employee_id, salary_id, item_no, \n" +
            "job_title, unit, highest_education, last_promotion, \n" +
            "promotion_date, work_status, appointment_nature) values (?,?,?,?,?,?,?,?,?,?)";
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            //JOptionPane.showMessageDialog(null, "Birthday   = "+this.getBirthdate());
            DBConnect();
            ps=con.prepareStatement(pstmt);
            ps.setInt(1, this.getEmployee_id());
            ps.setInt(2, this.getSalary_id());
          //  ps.setInt(2, this.getEmployee_no());
          //  ps.setString(3, this.getFds()); 
            ps.setString(3, this.getItem_no());
            ps.setString(4, this.getJob_title());
            ps.setString(5, this.getUnit());
            ps.setString(6, this.getHighest_education());
            ps.setString(7, this.getLast_promotion());
            ps.setString(8, this.getPromotion_date());
            ps.setString(9, this.getWork_status());
            ps.setString(10, this.getAppointment_nature());
            ps.executeUpdate();
            ResultSet res = ps.getGeneratedKeys();
            
           while (res.next()) {
              erec_id = Integer.parseInt(res.getString(1));
  
            }
            
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "This is error<>EmployeeRecord \n NewRecord first catch " + ex);
        }finally{
            try {
                ps.close();
                con.close();
                
                //rs.close();
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error exists " + ex);
                
               // Logger.getLogger(NewRecord.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        
        return erec_id;
     
    }
    
}
