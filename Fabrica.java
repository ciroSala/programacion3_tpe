import java.util.ArrayList;
import java.util.Iterator;

public class Fabrica {
    private final ArrayList<Maquina> maquinas;
    private final int piezasTotales;
    private Backtracking backtracking;
    private Greedy greedy;
    private Estado solucion;

    public Fabrica(ArrayList<Maquina> maquinas, int piezasTotales){
        this.maquinas = maquinas;
        this.piezasTotales = piezasTotales;
        this.backtracking = new Backtracking();
        this.greedy = new Greedy();
        this.solucion = null;

    }

    public String solucionBacktracking(){
        if(this.solucion!=null){
            this.solucion.clear();
        }
        this.solucion = this.backtracking.buscarSecuenciaMaquinas(this.maquinas, this.getPiezasTotales());
        return this.solucion.toString();
    }

    public String solucionGreedy(){
        if(this.solucion!=null){
            this.solucion.clear();
        }
        this.solucion = this.greedy.buscarSecuenciaMaquinas(this.maquinas, this.getPiezasTotales())
        return this.solucion.toString();
    }

    public int getPiezasTotales(){
        return this.piezasTotales;
    }

    public int getCantPuestasEnFuncionamiento(){
        return this.solucion.getPuestasFuncionamiento();
    }

    public int getCantEstados(){
        return this.backtracking.getCantEstados();
    }
}
