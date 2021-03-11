package ElementosTerreno;

import world.Mundo;

/**
 * Created by Miguel on 27/02/2019.
 */

public class Suelo extends BloqueFijo {

    public Suelo(Mundo w, int posX, int posY) {
        super(w, posX, posY,"elementosFijos/suelo.png");
    }
}
