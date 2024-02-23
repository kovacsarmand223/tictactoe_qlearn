package org.example.Gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * TODO:SZUKSEG VAN EGY DESIGN-RA, ILLETVE HOGY MILYEN INFORMACIOK LEGYENEK MEGJELENITVE
 * TODO:A JATEK FELULET ALATT KELL MEGJELENJEN A STATISZTIKA JATEKOSOKRA LEBONTVA (HA ELINDULT A JATEK) + EGY RESTART HA UJRA AKARJUK INDITANI A JATEKOT
 * TODO:A JATEK ELEJEN LEGYEN EGY KIVALASZTHATO FELULET AHOL A KEZDO JATEKOS VALASZT SZIMBOLUMOT (BEALLITASOK -> EGY KIS ABLAK JELENIK MEG)
 * TODO:START GOMB A JATEK ELEJEN, AZTAN ELTUNIK
 * TODO:BEALLITAS, HOGY AI-AL AKAR JATSZANI A JATEKOS VAGY EGY MASIK JATEKOSSAL
 * TODO:AZT IS BE LEHESSEN ALLITANI, HOGY AZ AI JATSZON SAJAT MAGAVAL
 * TODO:HA AZ AI JATSZIK SAJAT MAGAVAL AKKOR LE KELL LASSITANI AZ INPUTOKAT
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SuperTicTacToeGUI {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 650; // A gomboknak/informacioknak hely

    private JFrame frame = new JFrame();
    private JPanel gamePanel = new JPanel();

    // A játék logikájának inicializálása

    /**
     * STATIKOKAT KELL MEGVALOSITANI A JATEKMODOK-HOZ
     */

    public SuperTicTacToeGUI() {
        frame.setVisible(true);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null); // középre igazít
        frame.setResizable(false); // nem lehet változtatni a méretet az ablaknak
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // bezárja az alkalmazást ha az x-re kattintunk
        frame.setLayout(new BorderLayout());
        frame.setTitle("SuperTicTacToe");

        gamePanel.setLayout(new GridLayout());
    }
}
