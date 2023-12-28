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
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import ntp201.YearCalculator;

/**
 *
 * @author New User
 */
public class CreateTables {
    //YearCalculator age_calculator;
    

public TableModel CreateViewFilesTable(TableModel model){
    
    Icon note_icon = new ImageIcon("...ntp201\\icons\\note.png");//C:\\JavaProj\\NTP201\\src\\
    Icon attachment_icon = new ImageIcon("...ntp201\\icons\\attachment.png");//C:\\JavaProj\\NTP201\\src\\
    
    String pst = "Select e.employee_no, r.item_no , e.ofds,"
            + "(e.lastname||', '||e.firstname||' '||e.name_extension ||'. '||e.middlename)AS 'employee', "
            + "e.birthdate , cast(strftime('%Y.%m%d', 'now') - strftime('%Y.%m%d', e.birthdate) as int),"
            + "e.sex , r.job_title ,r.unit , r.highest_education, r.last_promotion,r.promotion_date, "
            + "r.work_status, r.emprecord_id FROM TB_employee e, TB_employee_record r "  /* n.note, n.employee_id,*/
            + "WHERE e.employee_id = r.employee_id ORDER BY employee DESC";// AND n.emprecord_id = r.emprecord_id"; //TB_notes n 
        
        JTable table = new JTable();
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{ 
            Connection con = DBConnect();
            table.setModel(model);            
            DefaultTableModel tm1 = (DefaultTableModel)table.getModel();
            tm1.setRowCount(0);
            table.setModel(tm1);            
            ps = con.prepareStatement(pst);
            rs = ps.executeQuery();
            //int y = 1;            
            while(rs.next())
            {
                Object[] rows = new Object[13];                
                for(int i = 0; i <=12; i++){
                    
                        rows[i]=rs.getObject(i+1);
                }
                int x=0; 
                ((DefaultTableModel)table.getModel()).insertRow(x, rows);
                x++;  
           //y++;
            }           
        }catch(SQLException e){ 
            JOptionPane.showMessageDialog(null, "createViewFilesTable :"+e);
        }finally{
            try{
                ps.close();
                rs.close();
            }catch(SQLException e){  
                JOptionPane.showMessageDialog(null, e);
            }
        }
        return table.getModel();
    }    

public TableModel AttachmentTableModel(TableModel tm){//, String s){
    TableModel model = tm;
    JTable table = new JTable();
    
    String pstmt = "SELECT e.employee_no, e.lastname, e.firstname, e.middlename, e.name_extension FROM TB_employee e ";
              // + "WHERE e.employee_no || e.lastname || e.firstname || e.middlename || e.name_extension LIKE ? ";
    Connection con = DBConnect();
    try{
        table.setModel(model);
        DefaultTableModel dtf =(DefaultTableModel)table.getModel();
        dtf.setRowCount(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement(pstmt);
       // ps.setString(1,s);
        rs = ps.executeQuery();
        while(rs.next()){
             Object[] rows = new Object[5];                
                for(int i = 0; i <=4; i++){
                    
                        rows[i]=rs.getObject(i+1);
                        //JOptionPane.showMessageDialog(null, rows);
                }
                int x=0; 
                ((DefaultTableModel)table.getModel()).insertRow(x, rows);
                x++;   
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"ERRor in attachment table model "+e);
    }
    
    return table.getModel();
}

public TableModel EmployeeTableModel(TableModel tm){
    TableModel model = tm;
    JTable table = new JTable();
    
    String pstmt = "SELECT DISTINCT e.employee_no, r.item_no, e.ofds, "
            + "e.lastname||', '||e.firstname||' '||e.name_extension||' '||e.middlename AS 'Employee', "
            + "e.birthdate, cast(strftime('%Y.%m%d', 'now') - strftime('%Y.%m%d', e.birthdate) as int) AS Age,e.sex, e.civil_status, r.job_title, r.unit, s.salary_grade, s.salary_step, "
            + "s.salary_amount, r.highest_education, r.last_promotion, r.promotion_date, r.appointment_nature, "
            + "r.work_status,n.note,e.lastname, e.firstname, e.middlename, e.name_extension FROM TB_employee e, TB_employee_record r, TB_salary s, TB_notes n "
            + "WHERE e.employee_id=r.employee_id AND s.salary_id = r.salary_id AND n.emprecord_id = r.emprecord_id "
            + "ORDER BY e.lastname ASC";
    Connection con = DBConnect();
    try{
        table.setModel(model);
        DefaultTableModel dtf =(DefaultTableModel)table.getModel();
        dtf.setRowCount(0);
        PreparedStatement ps = null;
        ResultSet rs = null;
        ps = con.prepareStatement(pstmt);
       // ps.setString(1,s);
        rs = ps.executeQuery();
        while(rs.next()){
             Object[] rows = new Object[23];                
                for(int i = 0; i <=22; i++){
                    
                        rows[i]=rs.getObject(i+1);
                        //JOptionPane.showMessageDialog(null, rows);
                }
                int x=0; 
                ((DefaultTableModel)table.getModel()).insertRow(x, rows);
                x++;   
        }
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"ERRor in attachment table model "+e);
    }
    
    return table.getModel();
}

   
public Connection DBConnect(){
        Connection con=null;
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
