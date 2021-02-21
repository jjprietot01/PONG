package principal;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

import java.awt.event.ComponentEvent;
/**
 * Interfaz Pantalla
 * 
 * @author Juan Jose Prieto Talavero
 */
public interface Pantalla {
    public void inicializarPantalla();
    public void pintarPantalla(Graphics g);
    public void ejecutarFrame();
    public void pulsarRaton(MouseEvent e);
    public void moverRaton(MouseEvent e);
    public void redimensionarPantalla(ComponentEvent e);
    public void moverTabla(KeyEvent e);
}
