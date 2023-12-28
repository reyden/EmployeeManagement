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
public class Salary {
    String sal_grade;
    int sal_step;
    float sal_amount;
    Connection con;

    public Salary(String sal_grade, int sal_step, float sal_amount) {
        this.sal_grade = sal_grade;
        this.sal_step = sal_step;
        this.sal_amount = sal_amount;
    }

    public String getSal_grade() {
        return sal_grade;
    }

    public void setSal_grade(String sal_grade) {
        this.sal_grade = sal_grade;
    }

    public int getSal_step() {
        return sal_step;
    }

    public void setSal_step(int sal_step) {
        this.sal_step = sal_step;
    }

    public float getSal_amount() {
        return sal_amount;
    }

    public void setSal_amount(float sal_amount) {
        this.sal_amount = sal_amount;
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
   
    public int CheckSalary(){
        int salid = 0;
        String pstmt = "SELECT salary_id FROM TB_salary s WHERE s.salary_grade = ? AND s.salary_step = ? AND s.salary_amount = ?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            DBConnect();
            ps = con.prepareStatement(pstmt);
            ps.setString(1, getSal_grade());
            ps.setInt(2, getSal_step());
            ps.setFloat(3, getSal_amount());
            rs = ps.executeQuery();
            
            while(rs.next()){
                salid = rs.getInt(1);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Exception in CheckSalary "+ e);
            return 0;
            
        }finally{
            try{
            ps.close();
            //rs.close();
            con.close();
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, "Error finally closing connections in CheckSalary "+e);
            }
        }

        if(salid == 0){
            String pstmt1 = "INSERT INTO TB_salary(salary_grade, salary_step, salary_amount) VALUES (?,?,?)";
            DBConnect();
            PreparedStatement ps1 = null;
            ResultSet rs1 = null;
            //JOptionPane.showMessageDialog(null, "sal_grade "+this.getSal_grade()+"\n"
            //        + "sal_step "+this.getSal_step()+"\n sal_amount = "+this.getSal_amount());
            try{
                ps1 = con.prepareStatement(pstmt1);
                ps1.setString(1, getSal_grade());
                ps1.setInt(2, getSal_step());
                ps1.setFloat(3, getSal_amount());
                ps1.executeUpdate();
                rs1 = ps1.getGeneratedKeys();
                while(rs1.next()){
                    salid = Integer.parseInt(rs1.getString(1));
                }
            }catch(NumberFormatException | SQLException e){
                JOptionPane.showMessageDialog(null, "Error inserting in CheckSalary "+ e);
            }finally{
                try{
                    ps1.close();
                    con.close();
                    
                }catch(SQLException e){
                   JOptionPane.showMessageDialog(null, "Error finally inserting in CheckSalary "+ e); 
                }
            }
            
        }
       
        
        return salid;
    }
    
}
