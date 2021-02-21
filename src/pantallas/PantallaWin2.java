package pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import principal.PanelJuego;
import principal.Pantalla;

import java.awt.*;

/**
 * Pantalla de perder la partida
 * 
 * @author Juan Jose Prieto Talavero
 */

public class PantallaWin2 implements Pantalla {

    // Referencia al panel de juego
    private PanelJuego panelJuego;

    // Color texto
    private Color colorTexto;

    // Fuente pantalla
    private Font fuenteTexto;

    // Imagen de fondo
    private BufferedImage fondo;
    private Image fondoRedimensionado;

    public PantallaWin2(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        fuenteTexto = new Font("Arial", Font.BOLD, 50);
        colorTexto = Color.WHITE;
    }

    @Override
    public void inicializarPantalla() {
        // Fondo
        fondo = null;
        try {
            fondo = ImageIO.read(new File("Imagenes/w2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Ajustar al tamaño actual
        redimensionarFondo();

    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);

        g.setColor(colorTexto);
        g.setFont(fuenteTexto);
        g.drawString("J2 WIN", panelJuego.getWidth() - 250, 150);
        g.drawString("J1 LOSE", 50, panelJuego.getHeight() - 130);

    }

    /**
     * Método para rellenar el fondo del componente.
     * 
     * @param g
     */
    private void rellenarFondo(Graphics g) {
        // Rescalar la imagen al tamaño actual del panel de juego
        g.drawImage(fondoRedimensionado, 0, 0, null);
    }

    @Override
    public void ejecutarFrame() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void moverRaton(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void redimensionarPantalla(ComponentEvent e) {
        redimensionarFondo();
    }

    /**
     * Redimensiona la imagen al tamaño actual
     */
    private void redimensionarFondo() {
        fondoRedimensionado = fondo.getScaledInstance(panelJuego.getWidth(), panelJuego.getHeight(),
                Image.SCALE_SMOOTH);
    }

    @Override
    public void moverTabla(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    
}
