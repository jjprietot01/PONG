package principal;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * Sprite
 * 
 * @author Juan Jose Prieto Talavero
 */
public class Sprite {
    protected BufferedImage buffer;
    protected Color color = Color.BLACK;

    // Dimension
    protected int ancho;
    protected int alto;

    // Colocacion
    protected int posX;
    protected int posY;

    // Velocidades
    protected int velX;
    protected int velY;

    public Sprite(Color color, int ancho, int alto, int posX, int posY, int velX, int velY) {
        this.color = color;
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        inicializarBuffer();
    }

    public Sprite(String rutaImagen, int ancho, int alto, int posX, int posY, int velX, int velY) {
        this.ancho = ancho;
        this.alto = alto;
        this.posX = posX;
        this.posY = posY;
        this.velX = velX;
        this.velY = velY;
        inicializarBuffer(rutaImagen);
    }

    /**
     * Crea una imagen (Buffer) vacia del color del Sprite
     */
    protected void inicializarBuffer() {
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        Graphics g = buffer.getGraphics();
        g.setColor(color);
        g.fillRect(0, 0, ancho, alto);
        g.dispose();
    }

    /**
     * Crea una imagen (Buffer) vacia con la ruta
     */
    protected void inicializarBuffer(String rutaImagen) {
        BufferedImage imagenSprite = null;
        buffer = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
        try {
            imagenSprite = ImageIO.read(new File(rutaImagen));
            Graphics g = buffer.getGraphics();
            // Estampar la imagen Sprite en el buffer creado
            g.drawImage(imagenSprite.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH), 0, 0, null);
            g.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo estampar
     */
    public void estampar(Graphics g) {
        g.drawImage(buffer, posX, posY, null);
    }

    /**
     * Metodo mover
     * 
     * @param width
     */
    public void mover(int widthPanel, int heightPanel) {
        // Mover el cuadrado
        posX += velX;
        posY += velY;

        // Comprobar si choca con los bordes
        // Por la derecha
        if (posX + ancho >= widthPanel) {
            velX = -Math.abs(velX); // Forzar que siempre sea negativa
        }

        // Por la izquierda
        if (posX < 0) {
            velX = Math.abs(velX); // Forzar que siempre sea positiva
        }
    }

    public boolean colisiona(Sprite otroSprite) {
        boolean colisionEjeX = false;

        if (posX < otroSprite.getPosX()) {
            colisionEjeX = posX + ancho >= otroSprite.getPosX();
        } else {
            colisionEjeX = otroSprite.getPosX() + otroSprite.getAncho() >= posX;
        }

        /*
         * //Optimizacion: Dejar de comprobar si uno no colisiona if(!colisionEjeX){
         * return false; }
         */

        boolean colisionEjeY = false;

        if (posY < otroSprite.getPosY()) {
            colisionEjeY = posY + alto >= otroSprite.getPosY();
        } else {
            colisionEjeY = otroSprite.getPosY() + otroSprite.getAncho() >= posY;
        }

        return colisionEjeX && colisionEjeY;
    }

    public void moverSinBordes(){
        posX += velX;
        posY += velY;
    }

    public void hitTopBottom(Sprite otroSprite){
        int yBloque = otroSprite.getPosY();
        int xBloque = otroSprite.getPosX();
        int bloqueAlto = yBloque + otroSprite.getAlto();
        int bloqueAncho = xBloque + otroSprite.getAncho();

        //Top
        if((((posY + alto) > yBloque) && (posY < bloqueAlto)) && (((posX + ancho) > xBloque) && (posX < bloqueAncho))){
            velY = -Math.abs(velY);
        }

        //Botton
        if(((posY > yBloque) && (posY < bloqueAlto)) && (((posX + ancho) > xBloque) && (posX < bloqueAncho))){
            velY = Math.abs(velY);
        }
    }

    // Getters y setters
    public BufferedImage getBuffer() {
        return this.buffer;
    }

    public void setBuffer(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getAncho() {
        return this.ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return this.alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getPosX() {
        return this.posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getVelX() {
        return this.velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return this.velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

}
