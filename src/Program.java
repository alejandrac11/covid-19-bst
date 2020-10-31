import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Program {

	public static void main (String [] args) throws NumberFormatException, IOException {
		
		Bst arbolPacientes = new Bst();
		
		BufferedReader archivo = new BufferedReader(new FileReader("DatosPacientes.txt"));
		
		int numPacientes = Integer.parseInt(archivo.readLine());
		
		for (int i = 0; i<numPacientes; i++ ) {
			String datosPaciente[] = archivo.readLine().split(",");
			
			Paciente paciente = new Paciente();
			paciente.nombre = datosPaciente[0];
			paciente.id = Integer.parseInt(datosPaciente[1]);
			
			int numSintomas = Integer.parseInt(datosPaciente[2]);
			
			paciente.pago = numSintomas*100;
			
			paciente.sintomas = new String [numSintomas];
			
			for (int s = 0; s<numSintomas; s++) {
				String sintoma = archivo.readLine();
				paciente.sintomas[s] = sintoma;	
			}
			arbolPacientes.agregarPaciente(paciente);
		}
		arbolPacientes.recorrerArbol();
		arbolPacientes.consultarSintomas(11);
		arbolPacientes.consultarPago(11);
		arbolPacientes.contarSintoma();
		arbolPacientes.consultaPacienteMasSintomas();
		arbolPacientes.promedioPagos();
		
		
 	}
	

}
