package model;

import java.util.Objects;

public class Jutsu {
    private final int dano;
    private final int chakra;

    public Jutsu(int dano, int chakraGasto) {
        this.dano = dano;
        this.chakra = chakraGasto;
    }

    public int getDano() {
        return dano;
    }

    public int getChakraGasto() {
        return chakra;
    }
}
