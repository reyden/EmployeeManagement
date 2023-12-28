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
public class SaveNote {
    int emp_id;
    int emprec_id;
    int notid;
    String note;
    Connection con;

    public  SaveNote(int emp_id, int emprec_id, String note) {
        //int notid = 0;
        this.emp_id = emp_id;
        this.emprec_id = emprec_id;
        this.note = note;
        LoadNote();
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getNotid() {
        return notid;
    }

    public void setNotid(int notid) {
        this.notid = notid;
    }
    
    private void LoadNote(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String pstmt = "INSERT INTO TB_notes(employee_id, emprecord_id, note)VALUES(?,?,?)";
        int nid = 0;
        try{
            DBConnect();
            ps = con.prepareStatement(pstmt);
            ps.setInt(1, getEmp_id());
            ps.setInt(2,getEmprec_id());
            ps.setString(3, getNote());            
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            while(rs.next()){
                nid = rs.getInt(1);
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"eRROR saving values in LoadNote: "+e);
        }
          setNotid(nid);
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
