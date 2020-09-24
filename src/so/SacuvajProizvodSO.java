/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IObjekat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Maja
 */
public class SacuvajProizvodSO extends OpstaSistemskaOperacija{

    public SacuvajProizvodSO() {
        super();
    }

    @Override
    protected void proveriPreduslove(IObjekat domenskiObjekat) throws Exception {
      
    }

    @Override
    protected ServerskiOdgovor izvrsiOperaciju(IObjekat domenskiObjekat) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        try {
            getDb().unesi(domenskiObjekat);
            so.setUspesno(true);
            so.setPoruka("Sistem je sacuvao proizvod");
           
        } catch (Exception e) {
            Logger.getLogger(PretragaRacunaSO.class.getName()).log(Level.SEVERE, null, e);

            throw new Exception("Sistem ne moze da sacuva proizvod");
        }
        return so;
    }
    
}
