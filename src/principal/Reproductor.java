package principal;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

/**
 * Reproductor
 * 
 * @author Juan Jose Prieto Talavero
 */

public class Reproductor {
    Clip clip; 
      
    AudioInputStream audioInputStream; 
  
    /**
     * El costructor solo va a cargar la cancion por defecto
     * Si quisiera añadir musica de victoria o derrota seria tan simple como pasarle por
     * parametro al constructor la ruta del archivo
     * Si quisiera añadir sonidos de siparo habria que modificar que en el constructor se ponga en loop el sonido/cancion
     * Todas las propuesta de arribas son posibles mejoras que se podrian incluir
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     */
    public Reproductor() throws UnsupportedAudioFileException, IOException, LineUnavailableException  { 
        // create AudioInputStream object 
        audioInputStream = AudioSystem.getAudioInputStream(new File("Music/music.wav").getAbsoluteFile()); 
          
        // create clip reference 
        clip = AudioSystem.getClip(); 
          
        // open audioInputStream to the clip 
        clip.open(audioInputStream); 
          
        clip.loop(Clip.LOOP_CONTINUOUSLY); 
    } 

    public void play()  
    {  
        clip.start();
    } 

    public void stop()  
    {  
        clip.stop();
    } 
}
