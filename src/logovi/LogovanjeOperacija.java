/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logovi;

import java.util.Date;

/**
 *
 * @author Maja
 */
public class LogovanjeOperacija {
    private String klijent;
    private String operacija;
    private Date vremeOperacije;

    public LogovanjeOperacija() {
    }

    public LogovanjeOperacija(String klijent, String operacija, Date vremeOperacije) {
        this.klijent = klijent;
        this.operacija = operacija;
        this.vremeOperacije = vremeOperacije;
    }

    public Date getVremeOperacije() {
        return vremeOperacije;
    }

    public void setVremeOperacije(Date vremeOperacije) {
        this.vremeOperacije = vremeOperacije;
    }

    public String getKlijent() {
        return klijent;
    }

    public void setKlijent(String klijent) {
        this.klijent = klijent;
    }

    public String getOperacija() {
        return operacija;
    }

    public void setOperacija(String operacija) {
        this.operacija = operacija;
    }
    
    
}
