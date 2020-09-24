/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DBBroker;
import domen.IObjekat;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Maja
 */
public abstract class OpstaSistemskaOperacija {
    DBBroker db;

    public OpstaSistemskaOperacija() {
        db = new DBBroker();
    }
    
    public ServerskiOdgovor izvrsiTransakciju(IObjekat domenskiObjekat){
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            db.ucitajDrajver();
            db.otvoriKonekciju();
            proveriPreduslove(domenskiObjekat);
            so = izvrsiOperaciju(domenskiObjekat);
            db.commit();
        } catch (Exception ex) {
            Logger.getLogger(OpstaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
            so.setOdgovor(null);
            so.setUspesno(false);
            so.setPoruka(ex.getMessage());
            try {
                db.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(OpstaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally{
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(OpstaSistemskaOperacija.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return so;
    }

    protected abstract void proveriPreduslove(IObjekat domenskiObjekat) throws Exception;

    protected abstract ServerskiOdgovor izvrsiOperaciju(IObjekat domenskiObjekat) throws Exception;

    protected DBBroker getDb() {
        return db;
    }
    
    
    
}
