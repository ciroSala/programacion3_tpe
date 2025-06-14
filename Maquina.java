import java.util.Objects;

public class Maquina {
    private String nombre;
    private int piezasProduce;

    public Maquina(String nombre, int piezasProduce){
        this.nombre = nombre;
        this.piezasProduce = piezasProduce;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPiezasProduce() {
        return piezasProduce;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maquina maquina = (Maquina) o;
        return Objects.equals(nombre, maquina.nombre);
    }
}
