package com.polytech;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomTime {
    private byte heures;
    private byte minutes;

    public CustomTime(int heures, int minutes){
        this.setHeures(heures);
        this.setMinutes(minutes);
    }

    public CustomTime(){
        LocalDateTime temps = LocalDateTime.now(); // A formater
        DateTimeFormatter formateurDate = DateTimeFormatter.ofPattern("HH:mm");
        String formatDate = temps.format(formateurDate);
        String[] parts = formatDate.split(":");
        this.setHeures(Integer.parseInt(parts[0]));
        this.setMinutes(Integer.parseInt(parts[1]));
    }

    public String toString(){
        return "Il est : "+heures+"heure(s) et "+minutes+"minute(s)";
    }

    public int getHeures(){
        return heures;
    }

    public int getMinutes(){
        return minutes;
    }

    public void setHeures(int hh){
        // Pas necessaire de propager le throw avec throws car c'est une RuntimeException
        if(hh < 0 || hh > 23){
            throw new IllegalArgumentException("Heure invalide");
        } else {
            this.heures = (byte)hh;
        }
    }

    public void setMinutes(int mm){
        if(mm < 0 || mm > 59){
            throw new IllegalArgumentException("Heure invalide");
        } else {
            this.minutes = (byte) mm;
        }
    }

    public void setHeuresMinutes(int hh, int mm){
        this.setHeures(hh);
        this.setMinutes(mm);
    }

    public void avancerMinutes(int mm) {
        int totalMinutes = this.heures * 60 + this.minutes + mm;
        this.heures = (byte) (totalMinutes / 60);
        this.minutes = (byte) (totalMinutes % 60);
    }
}
