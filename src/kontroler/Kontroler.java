/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.IObjekat;
import java.util.ArrayList;
import logovi.LogovanjeOperacija;
import so.ОbrisiProizvodSO;
import so.ОbrisiRacunSO;
import so.IzmenaProizvodaSO;
import so.PretragaProizvodaSO;
import so.PretragaRacunaSO;
import so.PrijavaKorisnikaSO;
import so.SacuvajProizvodSO;
import so.ZapamtiNoviRacunSO;
import so.VratiPozicijeSO;
import so.VratiProizvodeSO;
import so.VratiProizvodjaceSO;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Maja
 */
public class Kontroler {
    
    private static Kontroler instanca;
    ArrayList<LogovanjeOperacija> listaLogova;

    private Kontroler() {
        listaLogova = new ArrayList<>();
    }

    public static Kontroler getInstanca() {
        if(instanca == null)
            instanca = new Kontroler();
        return instanca;
    }
    
    public void dodajLog(LogovanjeOperacija log){
        listaLogova.add(log);
    }

    public ArrayList<LogovanjeOperacija> getListaLogova() {
        return listaLogova;
    }
    

    public ServerskiOdgovor ulogujRadnika(IObjekat domenskiObjekat) {
        return new PrijavaKorisnikaSO().izvrsiTransakciju(domenskiObjekat);
    }

    public ServerskiOdgovor vratiProizvodjace(IObjekat domenskiObjekat) {
        return new VratiProizvodjaceSO().izvrsiTransakciju(domenskiObjekat);    
    }

    public ServerskiOdgovor sacuvajProizvod(IObjekat domenskiObjekat) {
        return new SacuvajProizvodSO().izvrsiTransakciju(domenskiObjekat);    
    }

    public ServerskiOdgovor pretraziProizvode(IObjekat domenskiObjekat) {
       return new PretragaProizvodaSO().izvrsiTransakciju(domenskiObjekat);    

    }

    public ServerskiOdgovor izmeniProizvode(IObjekat domenskiObjekat) {
       return new IzmenaProizvodaSO().izvrsiTransakciju(domenskiObjekat);    
    }

    public ServerskiOdgovor obrisiProizvod(IObjekat domenskiObjekat) {
       return new ОbrisiProizvodSO().izvrsiTransakciju(domenskiObjekat);    
    }

    public ServerskiOdgovor vratiPozicije(IObjekat domenskiObjekat) {
       return new VratiPozicijeSO().izvrsiTransakciju(domenskiObjekat);
    }

    public ServerskiOdgovor vratiProizvode(IObjekat domenskiObjekat) {
        return new VratiProizvodeSO().izvrsiTransakciju(domenskiObjekat);
    }

    public ServerskiOdgovor unesiRacun(IObjekat domenskiObjekat) {
        return new ZapamtiNoviRacunSO().izvrsiTransakciju(domenskiObjekat);
    }

    public ServerskiOdgovor pretraziRacune(IObjekat domenskiObjekat) {
        return new PretragaRacunaSO().izvrsiTransakciju(domenskiObjekat);
    }

    public ServerskiOdgovor obrisiRacun(IObjekat domenskiObjekat) {
        return new ОbrisiRacunSO().izvrsiTransakciju(domenskiObjekat);
    }
}
