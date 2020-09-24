/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import domen.IObjekat;
import domen.Racun;
import domen.StavkaRacuna;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Maja
 */
public class ZapamtiNoviRacunSO extends OpstaSistemskaOperacija{

    public ZapamtiNoviRacunSO() {
        super();
    }

    @Override
    protected void proveriPreduslove(IObjekat domenskiObjekat) throws Exception {
    }

    @Override
    protected ServerskiOdgovor izvrsiOperaciju(IObjekat domenskiObjekat) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        
        try {
            int id = getDb().unesi(domenskiObjekat);
            Racun r = (Racun) domenskiObjekat;
            //r.setRacunID(getDb().max(domenskiObjekat));
            r.setRacunID(id);
            for (StavkaRacuna sr : r.getListaStavki()) {
                sr.setRacun(r);
                getDb().unesi(sr);
            }
            so.setUspesno(true);
            so.setPoruka("Sistem je sacuvao racun");
        } catch (Exception e) {
            Logger.getLogger(PretragaRacunaSO.class.getName()).log(Level.SEVERE, null, e);
            throw new Exception("Sistem nije sacuvao racun");

        }
        return so;
    }
    
}
