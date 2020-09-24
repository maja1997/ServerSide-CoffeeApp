/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.IObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Konstante;
import kontroler.Kontroler;
import logovi.LogovanjeOperacija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Maja
 */
public class ObradaKlijentskihZahteva extends Thread{
    Socket s;
    String imeKlijenta;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket s, String imeKlijenta) {
        this.s = s;
        this.imeKlijenta = imeKlijenta;
    }

    @Override
    public void run() {
        while (!kraj) {            
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            LogovanjeOperacija log = new LogovanjeOperacija(imeKlijenta, kz.getOperacija(), new Date());
            Kontroler.getInstanca().dodajLog(log);
            System.out.println("Operacija: "+kz.getOperacija());
            IObjekat domenskiObjekat = kz.getParametar();
            switch(kz.getOperacija()){
                case Konstante.ULOGUJ_RADNIKA:
                    so = Kontroler.getInstanca().ulogujRadnika(domenskiObjekat);
                    break;
                case Konstante.VRATI_PROIZVODJACE:
                    so = Kontroler.getInstanca().vratiProizvodjace(domenskiObjekat);
                    break;
                case Konstante.UNOS_PROIZVODA:
                    so = Kontroler.getInstanca().sacuvajProizvod(domenskiObjekat);
                    break;
                case Konstante.PRETRAGA_PROIZVODA:
                    so = Kontroler.getInstanca().pretraziProizvode(domenskiObjekat);
                    break;   
                case Konstante.IZMENA_PROIZVODA:
                    so = Kontroler.getInstanca().izmeniProizvode(domenskiObjekat);
                    break;  
                case Konstante.BRISANJE_PROIZVODA:
                    so = Kontroler.getInstanca().obrisiProizvod(domenskiObjekat);
                    break;    
                case Konstante.VRATI_POZICIJE:
                    so = Kontroler.getInstanca().vratiPozicije(domenskiObjekat);
                    break;    
                case Konstante.VRATI_PROIZVODE:
                    so = Kontroler.getInstanca().vratiProizvode(domenskiObjekat);
                    break;     
                case Konstante.UNOS_RACUNA:
                    so = Kontroler.getInstanca().unesiRacun(domenskiObjekat);
                    break;
                case Konstante.PRETRAGA_RACUNA:
                    so = Kontroler.getInstanca().pretraziRacune(domenskiObjekat);
                    break;
                case Konstante.BRISANJE_RACUNA:
                     so = Kontroler.getInstanca().obrisiRacun(domenskiObjekat);
                    break;   
                case Konstante.KRAJ_RADA:
                    kraj=true;
                    so.setPoruka("Sistem je zavrsio sa radom.");
                    break;                    
            }
            
            posaljiOdgovor(so);
        }
    }

    public KlijentskiZahtev primiZahtev() {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            kz = (KlijentskiZahtev) ois.readObject();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kz;
    }

    public void posaljiOdgovor(ServerskiOdgovor so) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
