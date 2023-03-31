import model.Jutsu;
import model.Personagem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import type.NinjaDeNinjutsu;
import type.NinjaDeTaijutsu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NinjaTypeTest {
    private Personagem ninjaDeNinjutsu;
    private Personagem ninjaDeTaijutsu;

    @BeforeEach
    void init(){
        ninjaDeNinjutsu = Mockito.mock(NinjaDeNinjutsu.class);
        ninjaDeTaijutsu = Mockito.mock(NinjaDeTaijutsu.class);
    }
    @Test
    void ninjaDeTaijutsuDeveDesviar() {
        when(ninjaDeTaijutsu.desviar()).thenReturn(true);
        ninjaDeNinjutsu = new NinjaDeNinjutsu("Naruto", 100);
        ninjaDeNinjutsu.adicionarJutsu("Rasengan", new Jutsu(10,5));
        boolean atacou = ninjaDeNinjutsu.usarJutsu(ninjaDeTaijutsu, "rasengan");

        assertFalse(atacou);
    }

    @Test
    void ninjaDeTaijutsuDeveAtacar(){
        when(ninjaDeTaijutsu.usarJutsu(ninjaDeNinjutsu, "soco")).thenReturn(true);
        ninjaDeTaijutsu = new NinjaDeTaijutsu("Sakura", 100);
        ninjaDeTaijutsu.adicionarJutsu("soco", new Jutsu(10,0));

        ninjaDeTaijutsu.usarJutsu(ninjaDeNinjutsu, "soco");
    }

    @Test
    void ninjaDeNinjutsuDeveAtacar(){
        when(ninjaDeNinjutsu.usarJutsu(ninjaDeTaijutsu, "soco")).thenReturn(true);
        ninjaDeNinjutsu = new NinjaDeNinjutsu("Naruto", 100);
        ninjaDeNinjutsu.adicionarJutsu("Rasengan", new Jutsu(15,5));

        ninjaDeNinjutsu.usarJutsu(ninjaDeTaijutsu, "rasengan");
    }
    @Test
    void ninjaDeNinjajutsuDeveDesviar() {
        when(ninjaDeNinjutsu.desviar()).thenReturn(true);
        ninjaDeTaijutsu = new NinjaDeTaijutsu("Sakura", 100);
        ninjaDeTaijutsu.adicionarJutsu("soco", new Jutsu(10,0));
        boolean atacou = ninjaDeTaijutsu.usarJutsu(ninjaDeNinjutsu, "soco");

        assertFalse(atacou);
    }



}
