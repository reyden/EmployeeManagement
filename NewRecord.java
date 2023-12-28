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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author aci&rig
 */
public class NewRecord {
    Connection con = null;
    
public void AddEmployee(){
   /* 
    String insertNewData = "INSERT INTO TB_employee( firstname, middlename, lastname, name_extension, birthdate, sex)values(?,?,?,?,?,?)";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            JOptionPane.showMessageDialog(null, "Birthday   = "+emp.getBirthdate());
            DBConnect();
            ps=con.prepareStatement(insertNewData);
            ps.setString(1, emp.getLastname());
            ps.setString(2, emp.getFirstname());
            ps.setString(3, emp.getMiddlename());            
            ps.setString(4, emp.getName_extension());
            ps.setString(5, emp.getBirthdate());
            ps.setString(6, emp.getSex());
            ps.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "This is error   " + ex);
            //Logger.getLogger(NewRecord.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewRecord.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
        
}
public void StationsRecord(int staCode, String staName){
    String sr = "INSERT INTO TB_station(sta_code, sta_name)VALUES(?,?)";
    
    
}


public void EmployeeHistory(int empid, String fds, String plantillaItem, String position, int staId, 
        String highestEducation, String lastPromotion, String promotionDate, String attachments ){
    
    
    String es = "INSERT INTO TB_history(emp_id, fds, plantilla_no, item_title, sta_id, highest_education, "
            + "last_promotion, promotion_date, attachments, status, note_id)VALUES(?,?,?,?,?,?,?,?,?,?)";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try{
            DBConnect();
            ps = con.prepareStatement(es);
            ps.setInt(0, empid);
            ps.setString(1, fds);
            ps.setString(2, plantillaItem);
            ps.setString(3, position);
            ps.setInt(4, staId);
            ps.setString(5, highestEducation);
            ps.setString(6, lastPromotion);
            ps.setString(7, promotionDate);
            ps.setString(8, promotionDate);
            ps.setString(9, attachments);
            ps.executeUpdate();
            //ps.getGeneratedKeys();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error in EmployeeHistory: "+ e);
        }
    
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
