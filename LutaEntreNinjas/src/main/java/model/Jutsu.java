package model;

import java.util.Objects;

public class Jutsu {
    private final int dano;
    private final int chakra;

    public Jutsu(int dano, int chakraGasto) {
        this.dano = dano;
        this.chakra = chakraGasto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jutsu jutsu = (Jutsu) o;
        return dano == jutsu.dano && chakra == jutsu.chakra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dano, chakra);
    }

    public int getDano() {
        return dano;
    }

    public int getChakraGasto() {
        return chakra;
    }
}
