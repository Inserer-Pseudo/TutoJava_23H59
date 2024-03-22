package com.polytech;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomTime {
    private byte heures; // -127 à 128
    private byte minutes;

    public CustomTime(int heures, int minutes) throws IllegalArgumentException {
        this.setHeures(heures);
        this.setMinutes(minutes);
    }

    public CustomTime() {
        LocalDateTime temps = LocalDateTime.now(); // A formater
        DateTimeFormatter formateurDate = DateTimeFormatter.ofPattern("HH:mm");
        String formatDate = temps.format(formateurDate);
        String[] stringCoupee = formatDate.split(":");
        this.setHeures(Integer.parseInt(stringCoupee[0]));
        this.setMinutes(Integer.parseInt(stringCoupee[1]));
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

    public void setHeures(int hh) {
        // Pas necessaire de propager le throw avec throws car c'est une RuntimeException
        if(hh < 0 || hh > 23){
            throw new IllegalArgumentException("Heure invalide");
        } else {
            this.heures = (byte)hh;
        }
    }

    public void setMinutes(int mm) {
        if(mm < 0 || mm > 59){
            throw new IllegalArgumentException("Minutes invalide");
        } else {
            this.minutes = (byte) mm;
        }
    }

    public void setHeuresMinutes(int hh, int mm) throws IllegalArgumentException {
        this.setHeures(hh);
        this.setMinutes(mm);
    }

    public void avancerMinutes(int mm) {
        int minutesRemappees;

        int totalMinutes = this.heures * 60 + this.minutes + mm;
        if (totalMinutes < 0) {
            minutesRemappees = (1440 + totalMinutes) % 1440;
        } // 1440 minutes dans un jour
        else {
            minutesRemappees = totalMinutes % 1440;
        } // 1440 minutes dans un jour
        this.heures = (byte) (minutesRemappees / 60);
        this.minutes = (byte) (minutesRemappees % 60);
    }
}
