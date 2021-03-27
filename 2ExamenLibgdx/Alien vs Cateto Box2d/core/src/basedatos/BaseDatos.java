package basedatos;

public abstract class BaseDatos {
    private short aliensGenerados;

    public BaseDatos (){
        aliensGenerados=0;
    }

    public void aumentarCuenta(){
        aliensGenerados++;
    }

    public short getAliensGenerados(){
        return aliensGenerados;
    }

    public void setAliensGenerados(short numero){
        aliensGenerados=numero;
    }

    /**
     * Carga el número de aliens generados de la base de datos
     */
    public abstract void cargarCuenta();

    /**
     * Persiste el número de aliens en base de datos
     */
    public abstract void persistir();
}
