/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.ServerskaForma;
import java.util.logging.Level;
import java.util.logging.Logger;
import kontroler.Kontroler;

/**
 *
 * @author Maja
 */
public class OsveziLogove extends Thread{
    ServerskaForma sf;

    public OsveziLogove(ServerskaForma sf) {
        this.sf = sf;
    }

    @Override
    public void run() {
        while (true) {            
            sf.osveziLogove(Kontroler.getInstanca().getListaLogova());
            System.out.println("Logovi osvezeni");
            try {
                sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OsveziLogove.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
