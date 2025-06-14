import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        String archivo = "C:\\Users\\ciros\\IdeaProjects\\programacion3\\src\\tp_especial\\untitled\\src\\Configuracion.txt";
        System.out.println("Leyendo archivo: " + archivo);
        try (BufferedReader buffer = new BufferedReader(new FileReader(archivo))) {
            String linea;
            ArrayList<Maquina> maquinas = new ArrayList<>();

            int piezasTotales = Integer.parseInt(buffer.readLine()); // primera línea: cantidad de piezas

            while ((linea = buffer.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] confMaq = linea.split(",\\s*");
                if (confMaq.length != 2) {
                    throw new IllegalArgumentException("Línea mal formateada: " + linea);
                }
                String nombre = confMaq[0].trim();
                int piezasQueProduce = Integer.parseInt(confMaq[1].trim());
                Maquina maq = new Maquina(nombre, piezasQueProduce);
                maquinas.add(maq);
            }

            Fabrica fabrica = new Fabrica(maquinas, piezasTotales);

            System.out.println(">> Backtracking:");
            System.out.println("Solución obtenida: " + fabrica.solucionBacktracking());
            System.out.println("Cantidad de piezas producidas: " + fabrica.getPiezasTotales());
            System.out.println("Cantidad de puestas en marcha requeridas: " + fabrica.getCantPuestasEnFuncionamiento());
            System.out.println("Cantidad de estados generados: " + fabrica.getCantEstados());

            System.out.println("\n>> Greedy:");
            System.out.println("Solución obtenida: " + fabrica.solucionGreedy());
            System.out.println("Cantidad de piezas producidas: " + fabrica.getPiezasTotales());
            System.out.println("Cantidad de puestas en marcha requeridas: " + fabrica.getCantPuestasEnFuncionamiento());

            // Asegurate de tener implementado este método en la clase Greedy y Fabrica:
            // getCantCandidatosConsiderados() o similar
            System.out.println("Cantidad de candidatos considerados (Greedy): " + fabrica.getCantEstadosGreedy());

        } catch (Exception e) {
            System.out.println("Error al procesar archivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
