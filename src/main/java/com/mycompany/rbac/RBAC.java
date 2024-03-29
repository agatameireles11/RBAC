/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.rbac;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author agata
 */
public class RBAC {

    Map<String, Usuario> usuarios = new HashMap<>();
    Map<String, Papel> papeis = new HashMap<>();
    Map<String, Permissao> permissoes = new HashMap<>();

    //função que adiciona os usuarios com seus respectivos papeis
    public void adicionarUsuario(String nome, String papel) {
        usuarios.put(nome, new Usuario(nome, papel));
    }
    // adiicona os papeis
    public void adicionarPapel(String nome, String[] permissoes) {
        papeis.put(nome, new Papel(nome, permissoes));
    }
    //adiciona as permissoes
    public void adicionarPermissao(String nome) {
        permissoes.put(nome, new Permissao(nome));
    }
    //recebe o usuario e verifica se ele tem permissao para tal ação
    public boolean verificarAcesso(String nomeUsuario, String nomePermissao) {
        if (usuarios.containsKey(nomeUsuario)) {
            Usuario usuario = usuarios.get(nomeUsuario);
            if (papeis.containsKey(usuario.papel)) {
                Papel papel = papeis.get(usuario.papel);
                for (String permissao : papel.permissoes) {
                    if (permissao.equals(nomePermissao)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        //Sistema como blog de notícias, onde o adm pode adicionar notícias, editar notícias, restringir comentários, visualizar notícias e comentar
        //O usuário com login pode ler notícias e comentar 
        //O convidado pode apenas visualizar notícia
        RBAC sistema = new RBAC();

        //Primeiro definimos permissões
        sistema.adicionarPermissao("adicionar_notícias");
        sistema.adicionarPermissao("editar_notícias");
        sistema.adicionarPermissao("restringir_comentarios");
        sistema.adicionarPermissao("visualizar_noticia");
        sistema.adicionarPermissao("comentar");

        //Definindo os papeis
        sistema.adicionarPapel("adm", new String[]{"visualizar_noticia", "restringir_comentarios", "editar_noticia", "adicionar_noticias", "comentar"});
        sistema.adicionarPapel("usuario", new String[]{"visualizar_noticia", "comentar"});
        sistema.adicionarPapel("convidado", new String[]{"visualizar_noticia"});

        // Adicionar usuários
        sistema.adicionarUsuario("Sam", "adm");
        sistema.adicionarUsuario("Castiel", "usuario");
        sistema.adicionarUsuario("Dean", "convidado");

        //Teste, para sair preciona 0
        Scanner ler = new Scanner(System.in);
        int opcao = 1;
        String nome;
        while (opcao != 0) {
            System.out.println("Nome: ");
            nome = ler.next();
            System.out.println("O que desenja fazer?\n1-Adicionar notícias\n2-Editar notícias\n3-Restringir comentários\n4-Visualizar notícias\n5-Comentar\nOpcao:");
            opcao = ler.nextInt();
            switch (opcao) {
                case 1:
                    if (sistema.verificarAcesso(nome, "adicionar_noticias")) {
                        System.out.println("Permissao Aceita");
                    }else{
                        System.out.println("Permissao Negada");
                    }
                    break;
                case 2:
                    if (sistema.verificarAcesso(nome, "editar_noticia")) {
                        System.out.println("Permissao Aceita");
                    }else{
                        System.out.println("Permissao Negada");
                    }
                    break;
                case 3:
                    if (sistema.verificarAcesso(nome, "restringir_comentarios")) {
                        System.out.println("Permissao Aceita");
                    }else{
                        System.out.println("Permissao Negada");
                    }
                    break;
                case 4:
                    if (sistema.verificarAcesso(nome, "visualizar_noticia")) {
                        System.out.println("Permissao Aceita");
                    }else{
                        System.out.println("Permissao Negada");
                    }break;
                case 5:
                    if (sistema.verificarAcesso(nome, "comentar")) {
                        System.out.println("Permissao Aceita");
                    }else{
                        System.out.println("Permissao Negada");
                    }
                    break;
            }

        }

    }
}
