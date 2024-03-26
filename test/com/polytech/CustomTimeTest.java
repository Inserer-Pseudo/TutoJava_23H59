package com.polytech;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomTimeTest {
    private CustomTime time;
    @BeforeEach
    void initialise() throws IllegalArgumentException {
        time = new CustomTime(8,14);
    }

    @Test @DisplayName("Test de setter simple")
    void setHeures() {
        time.setHeures(13);
        assertEquals(13, time.getHeures());
    }

    @Test
    void setMinutes() {
        time.setMinutes(13);
        assertEquals(13, time.getMinutes());
    }

    @Test
    void avancerMinutesDePasTropDeMinutes() {
        time.avancerMinutes(12);
        assertEquals(8,time.getHeures());
        assertEquals(26, time.getMinutes());
    }

    @Test
    void avancerMinutesDePresqueUneHeureMaisPasEncoreUneHeure() {
        time.avancerMinutes(51);
        assertEquals(9,time.getHeures());
        assertEquals(5, time.getMinutes());
    }

    @Test
    void avancerMinutesDePlusUneHeure() {
        time.avancerMinutes(73);
        assertEquals(9,time.getHeures());
        assertEquals(27, time.getMinutes());
    }

    @Test
    void avancerMinutesDePlusDeDeuxHeures() {
        time.avancerMinutes(125);
        assertEquals(10,time.getHeures());
        assertEquals(19, time.getMinutes());
    }

    @Test
    void avancerMinutesDePlusDeTroisCentMinutes() {
        time.setHeuresMinutes(20,4);
        time.avancerMinutes(303);
        assertEquals(1,time.getHeures());
        assertEquals(7, time.getMinutes());
    }

    @Test
    void avancerMinutesDePlusDeUnJourEnVraiUnJourEtUneMinute() {
        time.setHeuresMinutes(3,56);
        time.avancerMinutes(1441);
        assertEquals(3,time.getHeures());
        assertEquals(57, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeEtPasTropEnVrai() {
        time.avancerMinutes(-12);
        assertEquals(8,time.getHeures());
        assertEquals(2, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeEnvironQuinzeMinutes() {
        time.avancerMinutes(-16);
        assertEquals(7,time.getHeures());
        assertEquals(58, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeDePlusUneHeure() {
        time.avancerMinutes(-70);
        assertEquals(7,time.getHeures());
        assertEquals(4, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeDePlusDeDeuxHeures() {
        time.avancerMinutes(-121);
        assertEquals(6,time.getHeures());
        assertEquals(13, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeDePlusDeDeuxCentQuaranteMinutes() {
        time.setHeuresMinutes(3,56);
        time.avancerMinutes(-241);
        assertEquals(23,time.getHeures());
        assertEquals(55, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeDunJourEtUneMinute() {
        time.setHeuresMinutes(3,56);
        time.avancerMinutes(-1441);
        assertEquals(3,time.getHeures());
        assertEquals(55, time.getMinutes());
    }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23})
        void testParametriqueAvancerMinutes(int hh) {
            time.setHeuresMinutes(hh, 0);
            time.avancerMinutes(60);
            assertEquals((hh + 1) % 24, time.getHeures());
            assertEquals(0, time.getMinutes());
        }

    @Test
    void testLeveeException() {
        assertThrows(IllegalArgumentException.class, () -> time.setHeures(-2));
        assertThrows(IllegalArgumentException.class, () -> time.setHeures(25));
        assertThrows(IllegalArgumentException.class, () -> time.setMinutes(-2));
        assertThrows(IllegalArgumentException.class, () -> time.setMinutes(61));
        assertThrows(IllegalArgumentException.class, () -> time.setHeuresMinutes(-2, 25));
        assertThrows(IllegalArgumentException.class, () -> time.setHeuresMinutes(25, 61));
    }
}