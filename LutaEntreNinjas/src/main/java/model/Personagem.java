package model;


import type.Ninja;

import java.util.*;

public abstract class Personagem implements Ninja {
    private String nome;
    private Map<String, Jutsu> jutsus;
    private int chakra;
    private int vida;

    public Personagem(String nome, int vida) {
        this.nome = nome;
        this.jutsus = new HashMap<>();
        this.chakra = 100;
        this.vida = vida;
    }

    public String getNome() {
        return nome;
    }

    public Map<String, Jutsu> getJutsus() {
        return Collections.unmodifiableMap(jutsus);
    }

    public int getChakra() {
        return chakra;
    }

    public int getVida() {
        return vida;
    }

    public void setChakra(int chakra) {
        this.chakra = chakra;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void adicionarJutsu(String nomeJutsu, Jutsu jutsu) {
        jutsus.put(nomeJutsu.trim().toLowerCase(), jutsu);
    }

    public boolean desviar(){
        int numberRandom = new Random().ints(1, 0, 100).findFirst().orElse(100);
        return 30 > numberRandom;
    }

    protected void descontarChakra(Jutsu jutsu) {
        if (this.chakra - jutsu.getChakraGasto() >= 0 && this.vida > 0){
            this.chakra -= jutsu.getChakraGasto();
        }else {
            System.out.println("Chakra insuficiente");
        }
    }

    public void receberDano(Jutsu jutsu) {
        if (this.vida - jutsu.getDano() >= 0  && this.chakra >= 0) {
            this.vida -= jutsu.getDano();
        }else{
            System.out.println("O personagem esta morto");
        }
    }
}
