/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import logovi.LogovanjeOperacija;

/**
 *
 * @author Maja
 */
public class ModelTabeleLogovi extends AbstractTableModel{
    ArrayList<LogovanjeOperacija> logovi;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    public ModelTabeleLogovi() {
        logovi = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return logovi.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LogovanjeOperacija log = logovi.get(rowIndex);
        switch(columnIndex){
            case 0: return log.getKlijent();
            case 1: return log.getOperacija();
            case 2: return sdf.format(log.getVremeOperacije());
            default:return "";
        }
    }

    @Override
    public String getColumnName(int column) {
         switch(column){
            case 0: return "Klijent";
            case 1: return "Operacija";
            case 2: return "Vreme";
            default:return "";
        }
    }

    public void setLogovi(ArrayList<LogovanjeOperacija> logovi) {
        this.logovi = logovi;
        fireTableDataChanged();
    }
    
    
}
