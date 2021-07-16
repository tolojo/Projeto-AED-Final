/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 */
public class Sistema {
    private Autor autorAux= null;
    private ArrayList<Autor> listaAutores = new ArrayList<>();

    public Sistema() {
    }

    public void menu() throws IOException {
     
        boolean termina = false;
        Imagem maca = new Imagem("RedApple.jpg", "./Imagens/");
        Imagem monkeyy = new Imagem("macaco.jpg", "./Imagens/");
        Autor autor1 = new Autor("Danilo");
        autor1.listaImagens.add(maca);
        autor1.listaImagens.add(monkeyy);
        listaAutores.add(autor1);
        autor1 = new Autor("Alexandre");
        listaAutores.add(autor1);
        String urlImg1;
        String urlImg2;
        Scanner scanner = new Scanner(System.in);

        while (!termina) {

            do {
                int opcao = 1;
        
                if (opcao == 1 || opcao == 2 || opcao == 3 || opcao == 4) {

                    System.out.println("Menu:");
                    System.out.println("Selecione uma opcao;");
                    System.out.println("Opcao 1 - Gerir Autores");
                    System.out.println("Opcao 2 - Gerir Imagens");
                    System.out.println("Opcao 3 - Aplicar filtros");
                    System.out.println("Opcao 4 - Aplicar colagem \n");
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    
                   
                   
                } else {
                    System.out.println("Digite um numero dentro do intervalo pedido");
                    opcao = 1;
                }
            
                if (opcao == 4){
                    System.out.println("Digite o url e nome da imagem 1:");
                    urlImg1 = scanner.nextLine();
                    System.out.println("Digite o url e nome da imagem 2:");
                    urlImg2 = scanner.nextLine();
                    Colagem colagem = new Colagem(urlImg1,urlImg2);
                    System.out.println("Digite nome da imagem destino");
                    String nomeImgD = scanner.nextLine();
                    colagem.usarColagem("./Editadas/"+nomeImgD);
                }
                if (opcao == 1) {
                    System.out.println("Selecione uma opcao");
                    System.out.println("Opcao 1 - Adicionar Autores");
                    System.out.println("Opcao 2 - Remover Autores");
                    System.out.println("Opcao 3 - Pesquisar Autor");
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    if (opcao >= 1 || opcao <= 3) {
                        if (opcao == 1) {
                            System.out.println("Autor a adicionar");
                            String nome = scanner.nextLine();
                            adicionarAutor(nome);
                            termina = true;
                        }
                        if (opcao == 2) {
                            System.out.println("Autor a remover");
                            String nome = scanner.nextLine();
                            removerAutor(nome);
                            termina = true;
                        }
                        if (opcao == 3) {
                            System.out.println("Pesquise um autor");
                            String nome = scanner.nextLine();
                            System.out.println("Autores com esse nome:\n");
                            obterAutor(nome);

                            termina = true;
                        }
                    } else {
                        System.out.println("Insire um numero no intervalo pedido");
                    }

                } else if (opcao == 2) {
                    System.out.println("Que autor pretende pesquisar?");
                    String nomeAux = scanner.nextLine();
                    autorAux = autorImagens(nomeAux);
                    System.out.println("Opcao 1 - Adicionar Imagem");
                    System.out.println("Opcao 2 - Remover Imagem");
                    System.out.println("Opcao 3 - Pesquisar Imagem");
                    System.out.println("Selecione uma opcao");
                    opcao = scanner.nextInt();
                    scanner.nextLine();
                    if (opcao >= 1 || opcao <= 3) {
                        if (opcao == 1) {
                            System.out.println("Imagem a adicionar:");
                            System.out.println("Digite o nome");
                            String nome = scanner.nextLine();
                            System.out.println("Digite o url");
                            String url = scanner.nextLine();
                            adicionarImagem(autorAux, nome, url);
                            termina = true;
                        }
                        if (opcao == 2) {
                            System.out.println("Imagem a remover:");
                            String nome = scanner.nextLine();
                            removerImagem(autorAux, nome);
                            termina = true;
                        }
                        if (opcao == 3) {
                            System.out.println("Imagens do autor:" + autorAux.getNome());
                            obterImagem(autorAux);
                            termina = true;
                        } else {
                            System.out.println("Insira um numero no intervalo pedido");
                        }
                    }
                } else if (opcao == 3) {

                    System.out.println("Que autor pretende escolher?");
                    String nomeAux = scanner.nextLine();
                    String existe = "";
                    while (existe.equals("")) {
                        do {
                            int i = 0;
                            for (i = 0; i < listaAutores.size(); i++) {

                                if (listaAutores.get(i).getNome().equals(nomeAux)) {
                                    autorAux = autorImagens(nomeAux);
                                    existe = "existe";

                                }

                            }
                            if (!existe.equals("existe")) {
                                System.out.println("Insira um nome dentro da lista");
                                nomeAux = scanner.nextLine();

                            }
                        } while (existe.equals(""));
                    }
                    System.out.println("Imagens do autor:" + autorAux.getNome());
                    obterImagem(autorAux);

                    System.out.println("Digite o id da imagem que deseja editar");
                    int idImagem = scanner.nextInt();
                    if (opcao >= 1 || opcao <= 7) {
                        System.out.println("Opcao 1 - Rodar Esquerda");
                        System.out.println("Opcao 2 - Espelhar");
                        System.out.println("Opcao 3 - Criar Margem");
                        System.out.println("Opcao 4 - Converter para uma cor RGB");
                        System.out.println("Opcao 5 - Binarizar");
                        System.out.println("Opcao 6 - Criar Negativo");
                        System.out.println("Opcao 7 - Contornos");
                        System.out.println("Selecione uma opcao");
                        opcao = scanner.nextInt();

                        //PASTA DE EDITADOS
                        String url = "./Editadas/";

                        if (opcao == 1) {
                            ConversorImagemAED minhaImagem = new ConversorImagemAED(autorAux.listaImagens.get(idImagem - 1).getUrl() + autorAux.listaImagens.get(idImagem - 1).getNome());

                            System.out.println("Digite o nome da nova imagem");
                            String nome = scanner.next() + ".jpg";
                            minhaImagem.rodarEsquerda(url + nome);
                            termina = true;
                        }

                        if (opcao == 2) {
                            ConversorImagemAED minhaImagem = new ConversorImagemAED(autorAux.listaImagens.get(idImagem - 1).getUrl() + autorAux.listaImagens.get(idImagem - 1).getNome());

                            System.out.println("Digite o nome da nova imagem");
                            String nome = scanner.next() + ".jpg";
                            minhaImagem.espelhar(url + nome);
                            termina = true;
                        }

                        if (opcao == 3) {
                            ConversorImagemAED minhaImagem = new ConversorImagemAED(autorAux.listaImagens.get(idImagem - 1).getUrl() + autorAux.listaImagens.get(idImagem - 1).getNome());

                            System.out.println("Digite o nome da nova imagem");
                            String nome = scanner.next() + ".jpg";

                            System.out.println("Digite um valor de px para margem");
                            int margem = scanner.nextInt();

                            minhaImagem.criarMargem(margem, url + nome);
                            termina = true;
                        }

                        if (opcao == 4) {
                            ConversorImagemAED minhaImagem = new ConversorImagemAED(autorAux.listaImagens.get(idImagem - 1).getUrl() + autorAux.listaImagens.get(idImagem - 1).getNome());

                            System.out.println("Digite o nome da nova imagem");
                            String nome = scanner.next() + ".jpg";

                            System.out.println("Digite um de RGB (R=0, G=1, B=2)");
                            int rgb = scanner.nextInt();

                            minhaImagem.converteUmaCorRGB(url + nome, rgb);
                            termina = true;
                        }

                        if (opcao == 5) {
                            ConversorImagemAED minhaImagem = new ConversorImagemAED(autorAux.listaImagens.get(idImagem - 1).getUrl() + autorAux.listaImagens.get(idImagem - 1).getNome());

                            System.out.println("Digite o nome da nova imagem");
                            String nome = scanner.next() + ".jpg";

                            System.out.println("Digite um limite RGB (0-254)");
                            int limRGB = scanner.nextInt();

                            minhaImagem.binarizarBW(url + nome, limRGB);
                            termina = true;
                        }

                        if (opcao == 6) {
                            ConversorImagemAED minhaImagem = new ConversorImagemAED(autorAux.listaImagens.get(idImagem - 1).getUrl() + autorAux.listaImagens.get(idImagem - 1).getNome());

                            System.out.println("Digite o nome da nova imagem");
                            String nome = scanner.next() + ".jpg";

                            minhaImagem.negativoBW(url + nome);
                            termina = true;
                        }

                        if (opcao == 7) {
                            ConversorImagemAED minhaImagem = new ConversorImagemAED(autorAux.listaImagens.get(idImagem - 1).getUrl() + autorAux.listaImagens.get(idImagem - 1).getNome());

                            System.out.println("Digite o nome da nova imagem");
                            String nome = scanner.next() + ".jpg";

                            minhaImagem.detetarContornosBW(url + nome);
                            termina = true;
                        }
                    } else {
                        System.out.println("Insira um numero dentro do intervalo pedido");
                    }

                }
            } while (!termina);

            termina = false;
            System.out.println("Voltar ao menu? S/N");
            char confirma = scanner.next().charAt(0);
            if (confirma == 'N') {
                termina = true;
                scanner.close();
            }
        }

    }

    public void removerAutor(String nome) {        
        for (int i = 0; i < listaAutores.size(); i++) {
            if (listaAutores.get(i).getNome().equals(nome)) {
                listaAutores.remove(i);
            }
        }
    }

    public void adicionarAutor(String nome) {
        Autor aux = new Autor(nome);
        listaAutores.add(aux);
    }

    public void obterAutor(String autor) {
        String verNome = "";
        for (int i = 0; i < listaAutores.size(); i++) {
            if (listaAutores.get(i).getNome().equals(autor)) {

                System.out.println(listaAutores.get(i).getNome());
                verNome = listaAutores.get(i).getNome();
            }
        }
        if (verNome.equals("")) {
            System.out.println("Nao existe");
        }
        //return null;
    }

    public Autor autorImagens(String nome){
        String verNome = "";
        for (int i = 0; i < listaAutores.size(); i++) {
            if (listaAutores.get(i).getNome().equals(nome)) {
                return listaAutores.get(i);
            }
        }
        if (verNome.equals("")){ 
        return null;
        }
        else return null;
    }

    public void adicionarImagem(Autor aux,String nome, String url) {
        Imagem imagemAux = new Imagem(nome,url);
        aux.listaImagens.add(imagemAux);
    }

    public void removerImagem(Autor aux, String nome) {
        for (int i = 0; i < aux.listaImagens.size(); i++) {
            if (aux.listaImagens.get(i).getNome().equals(nome)) {
                aux.listaImagens.remove(i);

            }
        }
    }

    public void obterImagem(Autor aux){
        for(int i = 0; i < aux.listaImagens.size();i++){
                System.out.println(aux.listaImagens.get(i).getId() + " " + aux.listaImagens.get(i).getNome()+ " " +aux.listaImagens.get(i).getUrl());
        }
    }
}


