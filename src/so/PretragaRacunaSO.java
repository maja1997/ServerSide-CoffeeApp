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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Maja
 */
public class PretragaRacunaSO extends OpstaSistemskaOperacija{

    public PretragaRacunaSO() {
        super();
    }

    @Override
    protected void proveriPreduslove(IObjekat domenskiObjekat) throws Exception {
    }

    @Override
    protected ServerskiOdgovor izvrsiOperaciju(IObjekat domenskiObjekat) throws Exception {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<IObjekat> listaRacuna = new ArrayList<>();
        try {
          List<IObjekat> lista = domenskiObjekat.kreirajListu(getDb().vrati(domenskiObjekat));
            for (IObjekat iObjekat : lista) {
                Racun r = (Racun) iObjekat;
                StavkaRacuna sr = new StavkaRacuna();
                sr.setRacun(r);
                List<IObjekat> listaS = sr.kreirajListu(getDb().vrati(sr));
                ArrayList<StavkaRacuna> listaSt = new ArrayList<>();
                for (IObjekat stavkaRacuna : listaS) {
                    listaSt.add((StavkaRacuna) stavkaRacuna);
                }
                r.setListaStavki(listaSt);
                listaRacuna.add(r);
            }
          so.setUspesno(true);
          so.setOdgovor(listaRacuna);
          so.setPoruka("Uspesno pronadjena lista racuna");            
        } catch (Exception e) {
            Logger.getLogger(PretragaRacunaSO.class.getName()).log(Level.SEVERE, null, e);

            throw new Exception("Nije pronadjena lista racuna");
        }
        return so;
    }
    
}
