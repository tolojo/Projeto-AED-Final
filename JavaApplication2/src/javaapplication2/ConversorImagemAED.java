/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.io.FileWriter;


public class ConversorImagemAED {

    private BufferedImage imagem;

    public ConversorImagemAED(String urlImagemOrigem) {

        try {
            imagem = ImageIO.read(new File(urlImagemOrigem));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void rodarEsquerda(String urlImagemDestino){
        
        try{
            
            BufferedImage rodar = new BufferedImage(imagem.getHeight(), imagem.getWidth(), BufferedImage.TYPE_INT_RGB);
            
            for (int j = 0; j < imagem.getHeight(); j++){
                for (int i = 0, w = imagem.getWidth(); i < imagem.getWidth(); i++){
                 
                    int pixel = imagem.getRGB(i,j);
                    rodar.setRGB( j, w - 1 - i, pixel);
                }
                
            }
            
            ImageIO.write(rodar, "jpg", new File(urlImagemDestino));
            System.out.println("Imagem rodada para a esquerda");
            
        } catch (Exception e) {
            e.printStackTrace();
        }   
    }
    
    public void espelhar(String urlImagemDestino){
        
        try{
            
            BufferedImage imagemESP = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
            
            for (int j = 0; j < imagem.getHeight(); j++){
                for (int i = 0, w = imagem.getWidth() - 1; i < imagem.getWidth(); i++, w--){
                 
                    int p = imagem.getRGB(i,j);
                    imagemESP.setRGB( w, j, p);
                }
                
            }
            
            ImageIO.write(imagemESP, "jpg", new File(urlImagemDestino));
            System.out.println("Imagem espelhada");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void criarMargem(int margem, String urlImagemDestino){
        
        try{
            
            BufferedImage imagemMar = new BufferedImage(imagem.getWidth() + 2 * margem, imagem.getHeight() + 2 * margem, BufferedImage.TYPE_INT_RGB);
            
            for (int i = 0; i < imagem.getHeight(); i++){
                for (int j = 0; j < imagem.getWidth(); j++){
                    int pixel = imagem.getRGB(j,i);
                    imagemMar.setRGB( j + margem, i + margem, pixel);
                }    
            }
            
            ImageIO.write(imagemMar, "jpg", new File(urlImagemDestino));
            System.out.println("Margem Criada");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void converteUmaCorRGB(String urlImagemDestino, int rgb){
        
        try{
            
            BufferedImage imagemRGB = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
            
            for (int i = 0; i < imagem.getHeight(); i++){
                for (int j = 0; j < imagem.getWidth(); j++){
                    int pixel = imagem.getRGB(j, i);
                    
                    switch (rgb) {
                        case 0:
                            {
                                int a = (pixel >> 24) & 0xFF;
                                int r = (pixel >> 16) & 0xFF;
                                pixel = (a << 24) | (r << 16) | (0 << 8) | 0;
                                imagemRGB.setRGB(j, i, pixel);
                                break;
                            }
                        case 1:
                            {
                                int a = (pixel >> 24) & 0xFF;
                                int g = (pixel >> 8) & 0xFF;
                                pixel = (a << 24) | (0 << 16) | (g << 8) | 0;
                                imagemRGB.setRGB(j, i, pixel);
                                break;
                            }
                        default:
                            {
                                int a = (pixel >> 24) & 0xFF;
                                int b = pixel & 0xFF;
                                pixel = (a << 24) | (0 << 16) | (0 << 8) | b;
                                imagemRGB.setRGB(j, i, pixel);
                                break;
                            }
                    }
                    
                }    
            }
            
            ImageIO.write(imagemRGB, "jpg", new File(urlImagemDestino));
            System.out.println("Imagem convertida para uma cor RGB");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void histogramaCsvBW(String urlFicheiroDestino){
         
        try {
         int [] vetor = new int[256];
         FileWriter ficheiro = new FileWriter(urlFicheiroDestino);
         
        
        for (int i = 0; i < imagem.getHeight(); i++) {
                for (int j = 0; j < imagem.getWidth(); j++) {

                   Color cor = new Color(imagem.getRGB(j, i));
                   vetor[cor.getBlue()] =  vetor[cor.getBlue()] + 1;
                }
        }
        for(int i=0; i<vetor.length;i++){
        ficheiro.write(i + ";" + vetor[i] + "\n");
        }
        ficheiro.close();
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    public void binarizarBW(String urlImagemDestino, int limiteRGB){
        
        try{
            
            BufferedImage imagemBBW = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
            
            for (int i = 0; i < imagem.getHeight(); i++){
                for (int j = 0; j < imagem.getWidth(); j++){
                    Color cor = new Color (imagem.getRGB(j, i));
                    
                    if(cor.getRed() < limiteRGB){
                        imagemBBW.setRGB(j, i, new Color(255,255,255).getRGB());
                    } else {
                        imagemBBW.setRGB(j, i, new Color(0,0,0).getRGB());
                    } 
                }    
            }
            
            ImageIO.write(imagemBBW, "jpg", new File(urlImagemDestino));
            System.out.println("Imagem Binarizada (limite: " + limiteRGB + ")");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void negativoBW(String urlImagemDestino){
        try{
            BufferedImage imagemBW = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < imagem.getHeight(); i++) {
                for (int j = 0; j < imagem.getWidth(); j++) {

                    Color cor = new Color(imagem.getRGB(j, i));
                    int novoRGB = 255-cor.getRed();
                    imagemBW.setRGB(j, i, new Color(novoRGB,novoRGB,novoRGB).getRGB());
                }
            }
            ImageIO.write(imagemBW, "jpg", new File(urlImagemDestino));
            System.out.println("Imagem convertida para B&W negativo");
            
        }
        catch (Exception e){e.printStackTrace();}
    }
    public void detetarContornosBW(String urlImagemDestino){
        
        try{
            
            BufferedImage imagemCBW = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);
            int M[][] = new int [imagem.getHeight()][imagem.getWidth()];
            int Mx[][] = new int [imagem.getHeight()][imagem.getWidth()];
            int My[][] = new int [imagem.getHeight()][imagem.getWidth()];
            int Gx[][] = {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}};
            int Gy[][] = {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}};
            int maiorGradiente = 0;
            
            for (int j = 1; j < imagem.getHeight() - 1; j++){
                for (int i = 1; i < imagem.getWidth() - 1; i++){
                    
                    Mx[j - 1][i - 1] = new Color(imagem.getRGB(i - 1, j - 1)).getRed();
                    Mx[j - 1][i] = new Color(imagem.getRGB(i, j - 1)).getRed();
                    Mx[j - 1][i + 1] = new Color(imagem.getRGB(i + 1, j - 1)).getRed();
                    
                    Mx[j][i - 1] = new Color(imagem.getRGB(i - 1, j)).getRed();
                    Mx[j][i] = new Color(imagem.getRGB(i, j)).getRed();
                    Mx[j][i + 1] = new Color(imagem.getRGB(i + 1, j)).getRed();
                    
                    Mx[j + 1][i - 1] = new Color(imagem.getRGB(i - 1, j + 1)).getRed();
                    Mx[j + 1][i] = new Color(imagem.getRGB(i, j + 1)).getRed();
                    Mx[j + 1][i + 1] = new Color(imagem.getRGB(i + 1, j + 1)).getRed();
                    
                    Mx[j][i] = (Gx[0][0] * Mx[j - 1][i - 1]) + (Gx[0][1] * Mx[j - 1][i]) + (Gx[0][2] * Mx[j - 1][i + 1])
                            + (Gx[1][0] * Mx[j][i - 1]) + (Gx[1][1] * Mx[j][i]) + (Gx[1][2] * Mx[j][i + 1])
                            + (Gx[2][0] * Mx[j + 1][i - 1]) + (Gx[2][1] * Mx[j + 1][i]) + (Gx[2][2] * Mx[j + 1][i + 1]);
                    
                    My[j - 1][i - 1] = new Color(imagem.getRGB(i - 1, j - 1)).getRed();
                    My[j - 1][i] = new Color(imagem.getRGB(i, j - 1)).getRed();
                    My[j - 1][i + 1] = new Color(imagem.getRGB(i + 1, j - 1)).getRed();
                    
                    My[j][i - 1] = new Color(imagem.getRGB(i - 1, j)).getRed();
                    My[j][i] = new Color(imagem.getRGB(i, j)).getRed();
                    My[j][i + 1] = new Color(imagem.getRGB(i + 1, j)).getRed();
                    
                    My[j + 1][i - 1] = new Color(imagem.getRGB(i - 1, j + 1)).getRed();
                    My[j + 1][i] = new Color(imagem.getRGB(i, j + 1)).getRed();
                    My[j + 1][i + 1] = new Color(imagem.getRGB(i + 1, j + 1)).getRed();
                    
                    My[j][i] = (Gy[0][0] * My[j - 1][i - 1]) + (Gy[0][1] * My[j - 1][i]) + (Gy[0][2] * My[j - 1][i + 1])
                            + (Gy[1][0] * My[j][i - 1]) + (Gy[1][1] * My[j][i]) + (Gy[1][2] * My[j][i + 1])
                            + (Gy[2][0] * My[j + 1][i - 1]) + (Gy[2][1] * My[j + 1][i]) + (Gy[2][2] * My[j + 1][i + 1]);
                      
                    M[j][i] = (int) (Math.sqrt(Math.pow(Mx[j][i], 2) + Math.pow(My[j][i], 2)));
                    
                    if (M[j][i] > maiorGradiente){
                        maiorGradiente = M[j][i];
                    }
                    
                }  
                
            }
          
            for (int j = 1; j < M.length; j++){
                for (int i = 1; i < M[0].length; i++){
                    
                    M[j][i] = M[j][i] * 255 / maiorGradiente;
                    
                    Color cor = new Color(M[j][i], M[j][i], M[j][i]);
                    imagemCBW.setRGB(i, j, cor.getRGB());
                }
            }
            
            ImageIO.write(imagemCBW, "jpg", new File(urlImagemDestino));
            System.out.println("Deteção de contornos efetuada");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void converteBW(String urlImagemDestino) {

        try {

            BufferedImage imagemBW = new BufferedImage(imagem.getWidth(), imagem.getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < imagem.getHeight(); i++) {
                for (int j = 0; j < imagem.getWidth(); j++) {

                    Color cor = new Color(imagem.getRGB(j, i));
                    imagemBW.setRGB(j, i, getCinza(cor).getRGB());
                }
            }

            ImageIO.write(imagemBW, "jpg", new File(urlImagemDestino));
            System.out.println("Imagem convertida para B&W");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Color getCinza(Color cor) {

        int r = (int) (cor.getRed() * 0.3);
        int g = (int) (cor.getGreen() * 0.59);
        int b = (int) (cor.getBlue() * 0.11);

        return new Color(r+g+b, r+g+b, r+g+b);
    }

}
