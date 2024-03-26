package com.polytech;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CustomTimeTest {
    private CustomTime time;

    @BeforeEach
    void initialise() throws IllegalArgumentException {
        time = new CustomTime(8, 14);
    }

    @Test
    @DisplayName("Test de setter simple")
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
        assertEquals(8, time.getHeures());
        assertEquals(26, time.getMinutes());
    }

    @Test
    void avancerMinutesDePresqueUneHeureMaisPasEncoreUneHeure() {
        time.avancerMinutes(51);
        assertEquals(9, time.getHeures());
        assertEquals(5, time.getMinutes());
    }

    @Test
    void avancerMinutesDePlusUneHeure() {
        time.avancerMinutes(73);
        assertEquals(9, time.getHeures());
        assertEquals(27, time.getMinutes());
    }

    @Test
    void avancerMinutesDePlusDeDeuxHeures() {
        time.avancerMinutes(125);
        assertEquals(10, time.getHeures());
        assertEquals(19, time.getMinutes());
    }

    @Test
    void avancerMinutesDePlusDeTroisCentMinutes() {
        time.setHeuresMinutes(20, 4);
        time.avancerMinutes(303);
        assertEquals(1, time.getHeures());
        assertEquals(7, time.getMinutes());
    }

    @Test
    void avancerMinutesDePlusDeUnJourEnVraiUnJourEtUneMinute() {
        time.setHeuresMinutes(3, 56);
        time.avancerMinutes(1441);
        assertEquals(3, time.getHeures());
        assertEquals(57, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeEtPasTropEnVrai() {
        time.avancerMinutes(-12);
        assertEquals(8, time.getHeures());
        assertEquals(2, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeEnvironQuinzeMinutes() {
        time.avancerMinutes(-16);
        assertEquals(7, time.getHeures());
        assertEquals(58, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeDePlusUneHeure() {
        time.avancerMinutes(-70);
        assertEquals(7, time.getHeures());
        assertEquals(4, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeDePlusDeDeuxHeures() {
        time.avancerMinutes(-121);
        assertEquals(6, time.getHeures());
        assertEquals(13, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeDePlusDeDeuxCentQuaranteMinutes() {
        time.setHeuresMinutes(3, 56);
        time.avancerMinutes(-241);
        assertEquals(23, time.getHeures());
        assertEquals(55, time.getMinutes());
    }

    @Test
    void avancerMinutesMaisCaReculeDunJourEtUneMinute() {
        time.setHeuresMinutes(3, 56);
        time.avancerMinutes(-1441);
        assertEquals(3, time.getHeures());
        assertEquals(55, time.getMinutes());
    }

    @ParameterizedTest
    @CsvSource(value = {"12:8:26", "51:9:5", "73:9:27", "125:10:19", "303:1:7", "1441:3:57", "-12:8:2", "-16:7:58", "-70:7:4", "-121:6:13", "-241:23:55", "-1441:3:55"}, delimiter = ':')
    void testParametriqueAvancerMinutes(int decal, int HH, int MM) {
        switch (decal) {
            case 303:
                time.setHeuresMinutes(20, 4);
                break;
            case 1441, -241, -1441:
                time.setHeuresMinutes(3, 56);
                break;
        }

        time.avancerMinutes(decal);
        assertEquals(HH, time.getHeures());
        assertEquals(MM, time.getMinutes());
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