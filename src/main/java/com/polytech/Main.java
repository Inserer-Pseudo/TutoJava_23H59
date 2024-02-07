package com.polytech;

public class Main {
    public static void main(String[] args) {
        //CustomTime time1 = new CustomTime();
        //System.out.println(time1);

        //CustomTime time2 = new CustomTime(11,101);
        //System.out.println(time2);

        try {
            CustomTime time3 = new CustomTime();
            System.out.println(time3);
            time3.avancerMinutes(130);
            System.out.println(time3);
        } catch (IllegalArgumentException e) {
            // Permet de ne pas arrêter le programme si une heure est invalide
            System.out.println(e.getMessage());
        }
    }
}