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
public class VratiPozicijeSO extends OpstaSistemskaOperacija{

    public VratiPozicijeSO() {
        super();
    }

    @Override
    protected void proveriPreduslove(IObjekat domenskiObjekat) throws Exception {
    }

    @Override
    protected ServerskiOdgovor izvrsiOperaciju(IObjekat domenskiObjekat) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        
        try {
          List<IObjekat> lista = domenskiObjekat.kreirajListu(getDb().vrati(domenskiObjekat));
          so.setUspesno(true);
          so.setOdgovor(lista);
          so.setPoruka("Uspesno pronadjene pozicije");

        } catch (Exception e) {
            Logger.getLogger(PretragaRacunaSO.class.getName()).log(Level.SEVERE, null, e);

            throw new Exception("Nije pronadjena lista pozicija");
        }
        return so;    }
    
}
