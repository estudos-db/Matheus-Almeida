package br.com.game.src.main.model;

import br.com.game.src.main.Ninja;

import java.util.ArrayList;
import java.util.List;

public abstract class Personagem implements Ninja {
    private String nome;
    private int idade;
    private String aldeia;
    private ArrayList<String> jutsus;
    private int chakra;

    public Personagem(String nome, int idade, String aldeia, ArrayList<String> jutsus, int chakra) {
        this.nome = nome;
        this.idade = idade;
        this.aldeia = aldeia;
        this.jutsus = jutsus;
        this.chakra = chakra;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getAldeia() {
        return aldeia;
    }

    public List<String> getJutsus() {
        return jutsus;
    }

    public int getChakra() {
        return chakra;
    }

    public boolean adicionarJustu(String jutsu){
        return jutsus.add(jutsu);
    }

    public void aumentarChakra(int quantidade){
        if (quantidade > 0 && quantidade <= 100){
            this.chakra += quantidade;
            System.out.println("Seu Chakra aumentou!");
        }else{
            System.out.println("Seu Chakra não aumentou.");
        }
    }

    public void getInformacaoPersonagem(){
        System.out.println("Informações de " + this.nome);
        System.out.println( "[Idade: " + this.idade +
                            ", Aldeia: " + this.aldeia +
                            ", Jutsus: " + this.jutsus +
                            ", Chakra: " + this.chakra +
                            "]");
    }



}
