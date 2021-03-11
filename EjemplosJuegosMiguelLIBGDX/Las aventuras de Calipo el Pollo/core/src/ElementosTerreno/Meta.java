package ElementosTerreno;

import world.Mundo;

/**
 * Created by Miguel on 27/02/2019.
 */

public class Meta extends BloqueFijo {
    public Meta(Mundo w, int posX, int posY) {
        super(w, posX, posY, "elementosFijos/meta.png");
        //La meta es siempre inactiva, para que se pueda atravesar
        setInactivo();
    }
}
