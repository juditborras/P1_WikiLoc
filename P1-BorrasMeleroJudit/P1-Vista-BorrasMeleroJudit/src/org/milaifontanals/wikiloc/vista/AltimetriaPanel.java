/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.wikiloc.vista;

import java.awt.Graphics;
import java.util.List;
import javax.swing.JPanel;
import org.milaifontanals.wikiloc.model.Punt;

/**
 *
 * @author JUDIT
 */
public class AltimetriaPanel extends JPanel {
    public List<Punt> puntos;

    public AltimetriaPanel(List<Punt> puntos) {
        this.puntos = puntos;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Dibuja el gráfico de altimetría
        if (puntos != null && puntos.size() > 1) {
            for (int i = 0; i < puntos.size() - 1; i++) {
                Punt puntoActual = puntos.get(i);
                Punt puntoSiguiente = puntos.get(i + 1);

                int x1 = i * 10; // Ajusta la escala horizontal
                int y1 = getHeight() - (int) puntoActual.getAlt();
                int x2 = (i + 1) * 10;
                int y2 = getHeight() - (int) puntoSiguiente.getAlt();

                g.drawLine(x1, y1, x2, y2);
            }
        }
    }
}
