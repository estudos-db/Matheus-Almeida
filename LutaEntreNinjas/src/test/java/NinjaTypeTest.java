import model.Jutsu;
import model.Personagem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import type.NinjaDeNinjutsu;
import type.NinjaDeTaijutsu;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
public class NinjaTypeTest {
    private Personagem ninjaDeNinjutsu = Mockito.mock(NinjaDeNinjutsu.class);
    private Personagem ninjaDeTaijutsu = Mockito.mock(NinjaDeTaijutsu.class);

    @Test
    void deveCriarUmNinjaDeNinjutsu(){
        ninjaDeNinjutsu = new NinjaDeNinjutsu("Naruto", 100);

        ninjaDeNinjutsu.adicionarJutsu("Rasengan", new Jutsu(15,10));


        assertAll(
                () -> assertEquals(1, ninjaDeNinjutsu.getJutsus().size()),
                () -> assertEquals(100, ninjaDeNinjutsu.getChakra()),
                () -> assertEquals("Naruto", ninjaDeNinjutsu.getNome()),
                () -> assertEquals(100, ninjaDeNinjutsu.getVida())
        );
    }
    @Test
    void deveCriarUmNinjaDetaijutsu(){
        ninjaDeTaijutsu = new NinjaDeTaijutsu("Sakura", 100);

        ninjaDeTaijutsu.adicionarJutsu("soco", new Jutsu(20,0));


        assertAll(
                () -> assertEquals(1, ninjaDeTaijutsu.getJutsus().size()),
                () -> assertEquals(100, ninjaDeTaijutsu.getChakra()),
                () -> assertEquals("Sakura", ninjaDeTaijutsu.getNome()),
                () -> assertEquals(100, ninjaDeTaijutsu.getVida())
        );
    }
    @Test
    void ninjaDeveDesviar() {
        when(ninjaDeTaijutsu.desviar()).thenReturn(false);
        boolean atacou = ninjaDeNinjutsu.usarJutsu(ninjaDeTaijutsu, "rasengan");

        assertFalse(atacou);
    }

    @Test
    void ninjaDeveAtacar(){
        when(ninjaDeTaijutsu.usarJutsu(ninjaDeNinjutsu, "soco")).thenReturn(true);

        boolean socoPegou = ninjaDeTaijutsu.usarJutsu(ninjaDeNinjutsu, "soco");

        assertTrue(socoPegou);
    }

    @Test
    void ninjaDevePerderChakraAoAtacarOutroNinja(){
        when(ninjaDeNinjutsu.getChakra()).thenReturn(90);
        when(ninjaDeTaijutsu.getVida()).thenReturn(85);
        when(ninjaDeNinjutsu.usarJutsu(ninjaDeTaijutsu, "rasengan")).thenReturn(true);

        boolean danoPegou = ninjaDeNinjutsu.usarJutsu(ninjaDeTaijutsu, "rasengan");

        assertTrue(danoPegou);
        assertEquals(90, ninjaDeNinjutsu.getChakra());
        assertEquals(85, ninjaDeTaijutsu.getVida());
    }



}
