public class Maquina {
    private int key;
    private int piezasProduce;

    public Maquina(String key, int piezasProduce){
        this.key = Integer.parseInt(key);
        this.piezasProduce = piezasProduce;
    }

    public int getKey() {
        return key;
    }

    public int getPiezasProduce() {
        return piezasProduce;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maquina maquina = (Maquina) o;
        return key == maquina.key;
    }
}
