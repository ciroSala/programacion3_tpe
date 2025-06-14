import java.util.ArrayList;
import java.util.List;

public class Greedy {
    private ArrayList<Maquina> maquinas;
    private int cantPiezasProducir;

    public Greedy() {
        this.maquinas = new ArrayList<>();
        this.cantPiezasProducir = 0;
    }

    public Estado buscarSecuenciaMaquinas(ArrayList<Maquina> maquinas, int cantPiezasProducir) {
        this.maquinas = maquinas;
        this.cantPiezasProducir = cantPiezasProducir;
        return greedy();
    }

    /*
           Estrategia Greedy

      - Ordena las máquinas de mayor a menor producción.
      - En cada iteración, elige la máquina de mayor producción que no exceda el resto de piezas.
      - Repite hasta llegar exactamente a la cantidad requerida o determinar que no hay solución.

     */

    private Estado greedy() {
        Estado estado = new Estado();

        // Obtener todas las máquinas y ordenarlas descendente por piezas producidas
        List<Maquina> maquinas = new ArrayList<>(this.maquinas);

        maquinas.sort((m1, m2) -> Integer.compare(m2.getPiezasProduce(), m1.getPiezasProduce()));

        int piezasRestantes = cantPiezasProducir;
        int candidatosConsiderados = 0;

        while (piezasRestantes > 0) {
            boolean maquinaElegida = false;

            for (Maquina maquina : maquinas) {
                candidatosConsiderados++;
                if (maquina.getPiezasProduce() <= piezasRestantes) {
                    // Avanzar con esta máquina
                    estado.avanzar(maquina);
                    piezasRestantes -= maquina.getPiezasProduce();
                    maquinaElegida = true;
                    break; // Elegimos solo una máquina por iteración
                }
            }

            // Si no encontramos máquina que no sobrepase piezas restantes, no hay solución exacta
            if (!maquinaElegida) {
                return null;  // No se puede alcanzar la cantidad exacta
            }
        }

        return estado;
    }
}

