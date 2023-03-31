package br.com.game.src.main;

import br.com.game.src.main.model.Personagem;
import br.com.game.src.main.type.NinjaDeTaijutsu;

import java.util.ArrayList;
import java.util.List;

public class testeDeNinjas {

    public static void main(String[] args) {
        ArrayList<String> jutsus = new ArrayList<>();
        jutsus.add("Palma Inferior");

        Personagem ninjaDeTaijutsu = new NinjaDeTaijutsu("Hinata Hyuga", 12, "Konoha", jutsus, 100);
        ninjaDeTaijutsu.adicionarJustu("Punho Gentil");

        ninjaDeTaijutsu.getInformacaoPersonagem();
    }
}
