package br.com.game.src.test;

import br.com.game.src.main.type.NinjaDeGenjutsu;
import br.com.game.src.main.type.NinjaDeNinjutsu;
import br.com.game.src.main.type.NinjaDeTaijutsu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeNinjaTest {

    private NinjaDeTaijutsu ninjaDeTaijutsu;
    private NinjaDeGenjutsu ninjaDeGenjutsu;
    private NinjaDeNinjutsu ninjaDeNinjutsu;

    @BeforeEach
    void init(){
        criarNinjas();
    }

    @Test
    void deveAumentarChakraDosNinjas(){
        ninjaDeTaijutsu.aumentarChakra(50);
        ninjaDeGenjutsu.aumentarChakra(30);
        ninjaDeNinjutsu.aumentarChakra(70);

        assertAll(() ->{
            assertEquals(150, ninjaDeTaijutsu.getChakra());
            assertEquals(230, ninjaDeGenjutsu.getChakra());
            assertEquals(370, ninjaDeNinjutsu.getChakra());
        });
    }
    @Test
    void naoDeveAumentarChakraDosNinjas(){
        ninjaDeTaijutsu.aumentarChakra(110);
        ninjaDeGenjutsu.aumentarChakra(300);
        ninjaDeNinjutsu.aumentarChakra(430);

        assertAll(() ->{
            assertEquals(100, ninjaDeTaijutsu.getChakra());
            assertEquals(200, ninjaDeGenjutsu.getChakra());
            assertEquals(300, ninjaDeNinjutsu.getChakra());
        });
    }

    @Test
    void deveAdicionarJutsu(){
        ninjaDeTaijutsu.adicionarJustu("Cabeçada");
        ninjaDeGenjutsu.adicionarJustu("Rinnegan");
        ninjaDeNinjutsu.adicionarJustu("Rasengan Gigante");

        assertAll(() ->{
            assertEquals(2, ninjaDeTaijutsu.getJutsus().size());
            assertEquals(2, ninjaDeGenjutsu.getJutsus().size());
            assertEquals(2, ninjaDeNinjutsu.getJutsus().size());
        });
    }

    @Test
    void deveRetornarMensagemAoUsarJutsu(){
        assertAll(() ->{
            assertEquals("O ninja de Taijutsu esta atacando!", ninjaDeTaijutsu.usarJutsu());
            assertEquals("O ninja de Genjutsu esta atacando!", ninjaDeGenjutsu.usarJutsu());
            assertEquals("O ninja de Ninjutsu esta atacando!", ninjaDeNinjutsu.usarJutsu());
        });
    }
    @Test
    void deveRetornarMensagemAoDesviar(){
        assertAll(() ->{
            assertEquals("O ninja de Taijutsu desviou!", ninjaDeTaijutsu.desviar());
            assertEquals("O ninja de Genjutsu desviou!", ninjaDeGenjutsu.desviar());
            assertEquals("O ninja de Ninjutsu desviou!", ninjaDeNinjutsu.desviar());
        });
    }

    void criarNinjas(){
        ArrayList<String> taijutsuList = new ArrayList<>();
        taijutsuList.add("Punho Adamantino");
        ninjaDeTaijutsu = new NinjaDeTaijutsu("Sakura Haruno", 12, "Konoha", taijutsuList, 100);

        ArrayList<String> genjutsuList = new ArrayList<>();
        genjutsuList.add("Coerção Sharingan");
        ninjaDeGenjutsu = new NinjaDeGenjutsu("Sasuke Uchiha", 12, "Konoha", genjutsuList, 200);

        ArrayList<String> ninjutsuList = new ArrayList<>();
        ninjutsuList.add("Rasengan");
        ninjaDeNinjutsu = new NinjaDeNinjutsu("Naruto Uzumaki", 12, "Konoha", ninjutsuList, 300);

    }
}
