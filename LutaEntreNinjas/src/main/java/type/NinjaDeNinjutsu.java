package type;


import model.Jutsu;
import model.Personagem;

public class NinjaDeNinjutsu extends Personagem {
    public NinjaDeNinjutsu(String nome, int vida) {
        super(nome, vida);
    }


    @Override
    public boolean atacar(Personagem personagem, String nomeDoJutsu) {
        try{
            Jutsu jutsu = this.getJutsus().get(nomeDoJutsu.trim().toLowerCase());

            if (personagem.desviar() ){
                this.descontarChakra(jutsu);
                return false;
            }

            personagem.receberDano(jutsu);
            this.descontarChakra(jutsu);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
