package pantallas;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

import principal.PanelJuego;
import principal.Pantalla;
import principal.Reproductor;
import principal.Sprite;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Pantalla de juego
 * 
 * @author Juan Jose Prieto Talavero
 */

public class PantallaJuego implements Pantalla {
    // Referencia al panel de juego
    private PanelJuego panelJuego;

    // Fuente
    private Font fuente;

    // Imagen de fondo
    private BufferedImage fondo;
    private Image fondoRedimensionado;

    // Reprotuctor de musica
    private Reproductor rp;

    private static int PELOTA_X = 25;
    private static int PELOTA_Y = 25;
    private static int TABLA_X = 100;
    private static int TABLA_Y = 10;
    private Sprite pelota;
    private static int VEL_PELOTA = 5;
    private int vel_final;
    private Sprite tabla1;
    private int puntos1;
    private Sprite tabla2;
    private int puntos2;
    


    public PantallaJuego(PanelJuego panelJuego) {
        this.panelJuego = panelJuego;
        puntos1=0;
        puntos2=0;
        fuente = new Font("Arial", Font.BOLD, 40);

        // iniciamos la musica
        try {
            rp = new Reproductor();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        rp.play();

    }

    @Override
    public void inicializarPantalla() {

        tabla1 = new Sprite(Color.WHITE, TABLA_X, TABLA_Y, panelJuego.getWidth() / 2 - 50, panelJuego.getHeight() - 50, 0, 0);
        tabla2 = new Sprite(Color.WHITE, TABLA_X, TABLA_Y, panelJuego.getWidth() / 2 - 50, 50 - TABLA_Y, 0, 0);

        generalPelota();

        // Fondo
        fondo = null;
        try {
            fondo = ImageIO.read(new File("Imagenes/campo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Ajustar al tamaño actual
        redimensionarFondo();

    }

    @Override
    public void pintarPantalla(Graphics g) {
        rellenarFondo(g);

        tabla1.estampar(g);
        tabla2.estampar(g);
        pelota.estampar(g);

        // Pintar los marcadores
        g.setColor(Color.WHITE);
        g.setFont(fuente);
        g.drawString(Integer.toString(puntos2), 20, panelJuego.getHeight()/2 - 20);
        g.drawString(Integer.toString(puntos1), panelJuego.getWidth()-40, panelJuego.getHeight()/2 + 50);

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

    public void comprobarColisiones() {
       
        //Colision tabla1
        if(pelota.colisiona(tabla1)){
            pelota.hitTopBottom(tabla1);
        }

        //Colision tabla2
        if(pelota.colisiona(tabla2)){
            pelota.hitTopBottom(tabla2);
        }

        //Colision suelo, punto para jugador 2
        if (pelota.getPosY() + pelota.getAlto() >= panelJuego.getHeight()) {
            pelota=null;
            puntos2++;
            generalPelota();
        }

        //Colision techo, punto para jugador 1
        if(pelota.getPosY() < 0){
            pelota=null;
            puntos1++;
            generalPelota();
        }
    }

    /**
     * Comprueba quien de los jugadores gana
     */
    public void comprobarScore(){
        if(puntos1==3){
            rp.stop();
            panelJuego.cambiarPantalla(new PantallaWin1(panelJuego));
        }
        if(puntos2==3){
            rp.stop();
            panelJuego.cambiarPantalla(new PantallaWin2(panelJuego));
        }
    }

    /**
     * Se encarga de mover todos los Sprites
     */
    public void moverSprites() {
        pelota.mover(panelJuego.getWidth(), panelJuego.getHeight());
    }

    @Override
    public void ejecutarFrame() {
        try {
            Thread.sleep(25);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        moverSprites();

        comprobarColisiones();

        comprobarScore();

    }

    @Override
    public void pulsarRaton(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e)){
            if(tabla2.getPosX()+tabla2.getAncho() <= panelJuego.getWidth()){
                tabla2.setPosX(tabla2.getPosX() + 20);
            }
        }
        if(SwingUtilities.isLeftMouseButton(e)){
            if(tabla2.getPosX()>0){
                tabla2.setPosX(tabla2.getPosX() - 20);
            }
        }
    }

    @Override
    public void moverRaton(MouseEvent e) {
        
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
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            if(tabla1.getPosX()+tabla1.getAncho() <= panelJuego.getWidth()){
                tabla1.setPosX(tabla1.getPosX() + 20);
            }
        }
        if (key == KeyEvent.VK_LEFT) {
            if(tabla1.getPosX()>0){
                tabla1.setPosX(tabla1.getPosX() - 20);
            }
        }
    }

    /**
     * Me genera una pelota en el centro de la pantalla la cual sale random hacia uno de los lados
     */
    public void generalPelota(){
        vel_final=salidaRandom();
        pelota = new Sprite("Imagenes/pelota.png", PELOTA_X, PELOTA_Y, panelJuego.getWidth() / 2 - PELOTA_X/2,
                panelJuego.getHeight()/2 - PELOTA_Y/2, vel_final, vel_final);
    }

    /**
     * Metodo que genera la velocidad final
     * @return
     */
    public int salidaRandom(){
        Random rd = new Random();
        int num = rd.nextInt(2);
        int vel;
        if(num==0){
            vel=VEL_PELOTA*-1;
        }
        else{
            vel=VEL_PELOTA;
        }
        return vel;
    }
    
}
