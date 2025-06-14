import java.util.ArrayList;
import java.util.Iterator;

public class Estado {
    private ArrayList<Maquina> secuenciaMaquinas;
    private int puestasFuncionamiento;
    private int piezasFabricadas;

    public Estado(){
        this.secuenciaMaquinas = new ArrayList<>();
        this.puestasFuncionamiento = 0;
        this.piezasFabricadas = 0;
    }

    // Retorno si soy un estado final
    public boolean estadoFinal(int cantPiezasProducir){
        return this.piezasFabricadas == cantPiezasProducir;
    }

    // Retornar si soy mejor solucion que la solucion del estado pasado por parametro
    public boolean mejorSolucion(Estado e){
        if(e!=null){
            return this.puestasFuncionamiento < e.getPuestasFuncionamiento();
        }else{
            return true;
        }
    }

    public void copiar(Estado e){
        if (e == null) return;
        this.secuenciaMaquinas.clear();
        this.secuenciaMaquinas = new ArrayList<>(e.getSecuenciaMaquinas());
        this.puestasFuncionamiento = e.getPuestasFuncionamiento();
        this.piezasFabricadas = e.getPiezasFabricadas();
    }

    public int getPuestasFuncionamiento() {
        return puestasFuncionamiento;
    }

    public ArrayList<Maquina> getSecuenciaMaquinas(){
        return this.secuenciaMaquinas;
    }

    public int getPiezasFabricadas() {
        return piezasFabricadas;
    }

    public void avanzar(Maquina maquina){
        this.secuenciaMaquinas.add(maquina);
        this.piezasFabricadas += maquina.getPiezasProduce();
        this.puestasFuncionamiento++;
    }

    public void deshacer(Maquina maquina){
        int lastIndex = this.secuenciaMaquinas.size() - 1;
        if (lastIndex >= 0) {
            this.secuenciaMaquinas.remove(lastIndex);
            this.piezasFabricadas -= maquina.getPiezasProduce();
            this.puestasFuncionamiento--;
        }
    }

    public boolean esFactible(int cantPiezasProducir){
        return this.piezasFabricadas <= cantPiezasProducir;
    }

    @Override
    public String toString() {
        StringBuilder salida = new StringBuilder("[");
        Iterator<Maquina> iterator = this.secuenciaMaquinas.iterator();
        while(iterator.hasNext()){
            Maquina maquina = iterator.next();
            salida.append(maquina.getNombre()).append(", ");
        }
        if (salida.length() > 1) {
            salida.setLength(salida.length() - 2);
        }
        salida.append("]");
        return salida.toString();
    }

    public void clear(){
        this.secuenciaMaquinas.clear();
        this.piezasFabricadas = 0;
        this.puestasFuncionamiento = 0;
    }
}
