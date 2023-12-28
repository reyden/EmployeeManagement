/*
 * Thange this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataAccess;

import java.awt.HeadlessException;
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
public class Notes {
       String note;
       Connection con;
       int note_id=0;
    public Notes(){
       
    }
    
    public Notes(String note) {
        this.note = note;  
        JOptionPane.showMessageDialog(null, note);
        CreateTableAndSaveTemporaryNote();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public void  CreateTableAndSaveTemporaryNote(){
       
        ResultSet rs = null;
        PreparedStatement ps = null;
        String pstmt = "CREATE TABLE IF NOT EXISTS 'temp_note'('temp_note_id' INTEGER PRIMARY KEY AUTOINCREMENT, 'note' STRING)";
        try {
            //JOptionPane.showMessageDialog(null, "Birthday   = "+this.getBirthdate());
            DBConnect();
            ps = con.prepareStatement(pstmt);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Table temp_note, created successfully.");
        } catch (NumberFormatException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "eRROR CREATING DATABASE!...   " + ex);
        }finally{
           // try {
               // ps.close();
               // con.close();
                
            //} catch (SQLException ex) {
                
           // }
            
            try{
                
                DBConnect();
                ps=null;
                String pst = "INSERT INTO temp_note(note)VALUES(?)";
                ps = con.prepareStatement(pst);
                ps.setString(1, getNote());
                ps.executeUpdate();
                rs=ps.getGeneratedKeys();
                while(rs.next()){
                      note_id = rs.getInt(1);
                }
                JOptionPane.showMessageDialog(null, "Successfully saved to temp_note. ");
                
                JOptionPane.showMessageDialog(null, "new note id =" + note_id);
            }catch(HeadlessException | SQLException e){
                JOptionPane.showMessageDialog(null,"eRROR saving note to temporary table. "+e);
            }finally{
                try{
                    ps.close();
                    con.close();
                }catch(SQLException ex){
                    
                }
            }
            
        }
        
    }
    public String RetrieveNote(){
        String the_note="";
        String pstmt = "SELECT note FROM temp_note";
        PreparedStatement ps;
        ResultSet rs;
        
        try{
            DBConnect();
            ps = con.prepareStatement(pstmt);
            rs = ps.executeQuery();
            
            while(rs.next()){
              the_note =  rs.getString(1);
            }
            
        }catch(Exception e){
            //JOptionPane.showMessageDialog(null, "eRRor in RetrieveNote:  "+e);
        }
        JOptionPane.showMessageDialog(null, "the new_note is = "+the_note);
        return the_note;
    }
    public void DropNote(){
        String pstmt = "DROP TABLE IF EXISTS temp_note";
        PreparedStatement ps = null;
        try{
            DBConnect();
            ps = con.prepareStatement(pstmt);
            ps.executeUpdate();
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "eRRor dropping table: "+e);
        }
    }
    public void SaveTemporaryNote(){
        ResultSet rs = null;
        PreparedStatement ps = null;
        String stmt = "INSERT INTO temp_note(note)VALUES(?)";
        try{
            
        }catch(Exception e){
            
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
