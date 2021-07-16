/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;
import java.util.ArrayList;
/**
 *
 * 
 */
public class Autor {
    private String nome;
    private int id;
    static int proximoId = 0;
    public ArrayList<Imagem> listaImagens = new ArrayList<>();
    
    public Autor(String nome){
        id= ++proximoId;
        this.nome=nome;
    }
    public void adicionaImagem(Imagem img){
        listaImagens.add(img);
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
         
      
}
