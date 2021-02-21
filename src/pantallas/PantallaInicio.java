package pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import principal.PanelJuego;
import principal.Pantalla;

import java.awt.*;

/**
 * Pantalla de inicio de la partida
 * 
 * @author Juan Jose Prieto Talavero
 */

public class PantallaInicio implements Pantalla {

    // Referencia al panel de juego
    private PanelJuego panelJuego;

    // Variable que cambia el color de fondo
    private Color colorIntro = Color.WHITE;

    // Fuente pantalla inicio
    private Font fuenteGrande;

    public PantallaInicio(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        fuenteGrande = new Font("Arial", Font.BOLD, 20);
    }

    @Override
    public void inicializarPantalla() {

    }

    @Override
    public void pintarPantalla(Graphics g) {

        // Pinta la pantalla de inicio
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, panelJuego.getWidth(), panelJuego.getHeight());

        g.setColor(colorIntro);
        g.setFont(fuenteGrande);
        g.drawString("PING", panelJuego.getWidth() / 2 - 20, panelJuego.getHeight()/2-100);
        g.drawString("PONG", panelJuego.getWidth() / 2 - 25, panelJuego.getHeight()/2-70);

        g.setColor(Color.RED);
        g.drawString("PINCHA PARA JUGAR", panelJuego.getWidth() / 2 - 100, panelJuego.getHeight()/2);


    }

    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(750);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        colorIntro = colorIntro == Color.WHITE ? Color.LIGHT_GRAY : Color.WHITE;

    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        // TODO: cambiar pantalla
        panelJuego.cambiarPantalla(new PantallaJuego(panelJuego));

    }

    @Override
    public void moverRaton(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moverTabla(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    
}
