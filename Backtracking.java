import java.util.ArrayList;
import java.util.Iterator;

public class Backtracking {
    private ArrayList<Maquina> maquinas;
    private final Estado mejorSolucion;
    private int cantPiezasProducir;
    private int estadosGenerados; // <-- contador agregado

    public Backtracking(){
        this.maquinas = new ArrayList<>();
        this.cantPiezasProducir = 0;
        this.mejorSolucion = new Estado();
        this.estadosGenerados = 0;
    }

    public Estado buscarSecuenciaMaquinas(ArrayList<Maquina> maquinas, int cantPiezasProducir){
       Estado estado = new Estado();
       this.estadosGenerados = 0; // reinicio el contador antes de comenzar
       this.maquinas = maquinas;
       this.cantPiezasProducir = cantPiezasProducir;
       this.backtracking(estado);
       return this.mejorSolucion;
    }

   /*
            Estrategia de resolución con Backtracking

     ¿Cómo se genera el árbol de exploración?

     El arbol de exploracion se genera iterando desde el estado actual, hacia
    todas las maquinas que tenemos disponibles en cada nivel, verificamos que se
    cumpla con la condicion de que sea factible avanzar a cada maquina, viendo que las piezas
    fabricadas no superen la piezas a fabricar y que la cantidad de puestas en funcionamiento sean menor
    a la mejor solucion si existe. Luego, se llama recursivamente con el estado actual y finalmente
    en caso de que no se cumpla la condicion o se haya explorado por completo se quita la maquina del estado actual
    para iniciar una nueva posible solucion.

    Poda:
     Se implemento una poda la cual compara las puestas en funcionamiento actuales con las
    de la mejor solucion, si nuestro estado ya tiene mas puestas en funcionamiento, no es necesario
    buscar un estado final porque sabemos que no va ser mejor a nuestra mejor solucion.
    */

    private void backtracking(Estado estadoActual){
        this.estadosGenerados++; // <-- cada vez que se genera un nuevo estado

        // Caso Base: Cuando llegue a un estado final (Cuando se llegue a la cantPiezasProducir)
        if(estadoActual.estadoFinal(this.cantPiezasProducir)){
            // Si es es un estado final es una solución (Verificar si es mejor que mi mejor solucion)
            if(estadoActual.mejorSolucion(this.mejorSolucion) || this.mejorSolucion.getSecuenciaMaquinas().isEmpty()){
                // Copio los valores de la solucion a la mejor solucion
                this.mejorSolucion.copiar(estadoActual);
            }
        }else{
            // Generar hijos / espacio de busqueda (Todas las maquinas a las que puedo avanzar)
            Iterator<Maquina> maquinas = this.maquinas.iterator();
            // Por cada maquina avanzar el estado
            while(maquinas.hasNext()){
                Maquina maquina = maquinas.next();
                //  Avanzar el estado, es agregar la maquina a secuencia de maquinas, agregar a piezas fabricadas las
                // piezas que produce la maquina, y aumentar las puestas en funcionamiento en uno
                estadoActual.avanzar(maquina);
                // Verificar si es factible hacer la llamada recursiva
                if(estadoActual.esFactible(this.cantPiezasProducir) &&
                        (this.mejorSolucion.getSecuenciaMaquinas().isEmpty() ||
                                estadoActual.getPuestasFuncionamiento() < this.mejorSolucion.getPuestasFuncionamiento())){
                    // Realizo llamada recursiva
                    this.backtracking(estadoActual);
                }
                //  Deshacer el estado, es eliminar la ultima maquina de secuencias de maquinas, eliminar de piezas fabricadas las
                // piezas que produjo la ultima maquina
                estadoActual.deshacer(maquina);
            }
        }
    }

    public int getCantEstados(){
        return this.estadosGenerados;
    }
}
