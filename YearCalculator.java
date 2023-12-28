/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ntp201;

/**
 *
 * @author New User
 */
import java.awt.EventQueue;
import java.time.LocalDate; 
import java.time.Month; 
import java.time.Period; 

/** * Java Program to calculate number of years and months */ 
public class YearCalculator { 
        int num_of_years;
        int num_of_mos;
        int num_of_day;

    public YearCalculator(int num_of_years, int num_of_mos, int num_of_day) {
        
        LocalDate appointment_date = LocalDate.of(num_of_years, num_of_mos, num_of_day);
        LocalDate today = LocalDate.now();
        Period yrcount = Period.between(appointment_date, today);
        this.num_of_years = yrcount.getYears();
        this.num_of_mos = yrcount.getMonths();
        this.num_of_day = yrcount.getDays();
    }
    
    public int getNum_of_years() {
        return num_of_years;
    }

    public void setNum_of_years(int num_of_years) {
        this.num_of_years = num_of_years;
    }

    public int getNum_of_mos() {
        return num_of_mos;
    }

    public void setNum_of_mos(int numb_of_mos) {
        this.num_of_mos = numb_of_mos;
    }

    public int getNum_of_day() {
        return num_of_day;
    }

    public void setNum_of_day(int numb_of_day) {
        this.num_of_day = numb_of_day;
    }
    
    
}
