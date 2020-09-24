/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maja
 */
public class PokreniServer extends Thread{
    ServerskaForma sf;

    public PokreniServer(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(9000);
            ZaustavljanjeServera zs = new ZaustavljanjeServera(this, ss);
            zs.start();
            int brojac = 1;
            while (!isInterrupted()) {                
                Socket s = ss.accept();
                System.out.println("Klijent se povezao");
                String imeKlijenta = "Klijent "+brojac;
                brojac++;
                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(s, imeKlijenta);
                okz.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
