package com.xaxocode;

import com.xaxocode.modules.Plataforma;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Plataforma.initComponents();
            }
        });
    }
}
