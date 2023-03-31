package br.com.game.src.main.type;

import br.com.game.src.main.model.Personagem;

import java.util.ArrayList;

public class NinjaDeGenjutsu extends Personagem {
    public NinjaDeGenjutsu(String nome, int idade, String aldeia, ArrayList<String> jutsus, int chakra) {
        super(nome, idade, aldeia, jutsus, chakra);
    }

    @Override
    public String usarJutsu() {
        return "O personagem " + this.getNome() + " atacou com Genjutsu!";
    }

    @Override
    public String desviar() {
        return "O personagem " + this.getNome() + " desviou usando Genjutsu!";
    }
}
