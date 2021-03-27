package basedatos;

public abstract class BaseDatos {
    private short alienGenerados;

    public BaseDatos(){
        alienGenerados=0;
    }

    public void aumentarCuenta(){
        alienGenerados++;
    }

    /**
     * Carga el numero de aliens generados de la base de datos
     */
    public abstract  void  cargarCuenta();
    /**
     * Persiste el número de aliens en base de datos
     */
    public abstract void persistir();


    public short getAlienGenerados() {
        return alienGenerados;
    }

    public void setAlienGenerados(short alienGenerados) {
        this.alienGenerados = alienGenerados;
    }
}
