package javaapplication2;


import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;



public class Colagem {

    private BufferedImage img1;
    private BufferedImage img2;

    public Colagem(String urlImagemOrigem1, String urlImagemOrigem2){

        try {
            img1 = ImageIO.read(new File(urlImagemOrigem1));
            img2 = ImageIO.read(new File(urlImagemOrigem2));
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void usarColagem(String urlDest) throws IOException{
        joinBufferedImage(img1,img2);
        BufferedImage joinedImg = joinBufferedImage(img1,img2);
        ImageIO.write(joinedImg, "png", new File(urlDest+".png"));
    }
    public static BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2) {

        //do some calculate first
        int offset  = 5;
        int wid = img1.getWidth()+img2.getWidth()+offset;
        int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
        //create a new buffer and draw two image into the new image
        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
        
       for (int i = 0; i < img1.getHeight(); i++){
            for (int j = 0; j < img1.getWidth(); j++){
                int pixel = img1.getRGB(j,i);
                newImage.setRGB(j, i, pixel);
            }    
        }
        for (int i = 0; i < img2.getHeight(); i++){
            for (int j = 0; j < img2.getWidth()-1; j++){
                int pixel = img2.getRGB(j,i);
                newImage.setRGB(wid-j-offset , i+offset, pixel);
            }    
        }
        return newImage;
    }
    
}
