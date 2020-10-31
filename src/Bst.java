
public class Bst {

	NodoPaciente head;
	int contEnfermedad = 0;
    
	public void agregarPaciente(Paciente paciente) {
		
		if (head == null) {
			
			head = new NodoPaciente();
			head.paciente = paciente;
			
			return; 
		}
		
		boolean registroDoble = validarId(head, paciente.id);
		
		if (registroDoble == true) {
			System.out.println("El ID, del " + paciente.nombre + " ya se encuentra registrado en el sistema.");
			return;
		}
		else {
			agregarHijos(head, paciente);
		}
		
	}
	
	private void agregarHijos(NodoPaciente head, Paciente paciente) {
		
		if (head == null) {
			return;
		}
		
		if (paciente.id > head.paciente.id) {
			
			if (head.derecha == null) {
				head.derecha = new NodoPaciente();
				head.derecha.paciente = paciente;
			}
			else {
				agregarHijos(head.derecha, paciente);
				
			}
		}
		
		if (paciente.id < head.paciente.id) {
			
			if (head.izquierda == null) {
				head.izquierda = new NodoPaciente();
				head.izquierda.paciente = paciente;
			}
			else {
				agregarHijos(head.izquierda, paciente);
				
			}
		}
	}
	
	public void recorrerArbol() {
		
		imprimirPacientes(head);
	}
	
	private void imprimirPacientes(NodoPaciente head) {
		if (head == null) {
			return;
		}
		
		if (head.derecha != null) {
			System.out.println("Derecha:" + head.derecha.paciente.nombre);
			
			imprimirPacientes(head.derecha);	
		}
		
		if (head.izquierda != null) {
			System.out.println("Izquierda:" + head.izquierda.paciente.nombre);
			
			imprimirPacientes(head.izquierda);
		}
		
	}
	
	private boolean validarId(NodoPaciente head, int id) {
		if (head == null) {
			return false;
		}
		
		if (id == head.paciente.id) {
			return true;
		}
		
		if (id > head.paciente.id) {
			return validarId(head.derecha, id);
		}
		else {
			return validarId(head.izquierda, id);
		}
		
	}
	public void consultarSintomas(int id) {
		consultarSintomasXId(head, id); 
	}
	
	private void consultarSintomasXId(NodoPaciente head, int id) {
		if (head == null) {
			System.out.println("El ID que busca, no es existente");
			return;
		}
		
		if (id == head.paciente.id) {
			if (head.paciente.sintomas.length > 0 ) {
				System.out.println("Los sintomas que tiene el paciente " + head.paciente.nombre + " son: "  );
				for(int s = 0; s < head.paciente.sintomas.length; s++) {
					System.out.println(head.paciente.sintomas[s]);
				}
			}
			else {
				System.out.println("El paciente no posee sintomas de COVID-19");
			}
			return;
		}
		
		if (id > head.paciente.id) {
			 consultarSintomasXId(head.derecha, id);
		}
		else {
			 consultarSintomasXId(head.izquierda, id);
		}
	}
	
	public void consultarPago(int id) {
		consultarPagoXId(head, id); 
	}
	
	private void consultarPagoXId(NodoPaciente head, int id) {
		if (head == null) {
			System.out.println("El ID que busca, no es existente");
			return;
		}
		
		if (id == head.paciente.id) {
			System.out.println("El valor del pago del paciente " + head.paciente.nombre + " es: " + head.paciente.pago);
			return;
		}
		
		if (id > head.paciente.id) {
			 consultarPagoXId(head.derecha, id);
		}
		else {
			 consultarPagoXId(head.izquierda, id);
		}
	}
	
	public void contarSintoma() {
		conteoSintomas(head); 
		System.out.println("La cantidad de pacientes con Fiebre, son: " + contEnfermedad);
	}
	
	private void conteoSintomas(NodoPaciente head) {
		if (head == null) {
			return;
		}
		if (head.paciente.sintomas.length > 0 ) {
			for(int s = 0; s < head.paciente.sintomas.length; s++) {
				
				if (head.paciente.sintomas[s].equals("Fiebre")) {
					contEnfermedad += 1;
				}	
			}
		}
		if (head.derecha != null) {
			 conteoSintomas(head.derecha);
		}
		if (head.izquierda != null) {
			conteoSintomas(head.izquierda);
		}
		
		
	}

	public void consultaPacienteMasSintomas() {
		Paciente paciente = pacienteMasSintomas(head);
		if(paciente != null) {
			System.out.println("El paciente con más síntomas, es: " + paciente.nombre);
		}
		
	}
	private Paciente pacienteMasSintomas(NodoPaciente head) {
		if(head == null) {
			return null;
		}
	
		Paciente pacienteIzquierda = null;
		if(head.izquierda != null) {
			pacienteIzquierda = pacienteMasSintomas(head.izquierda);
		}
		
		Paciente pacienteDerecha = null;
		if(head.derecha != null) {
			pacienteDerecha = pacienteMasSintomas(head.derecha);
		}
		
		Paciente masSintomas = head.paciente;
		if(pacienteIzquierda != null) {
			if(masSintomas.sintomas.length <= pacienteIzquierda.sintomas.length) {
				masSintomas = pacienteIzquierda;
		}
		}
		if(pacienteDerecha != null) {
			if(masSintomas.sintomas.length <= pacienteDerecha.sintomas.length) {
				masSintomas = pacienteDerecha;
			}
		}
		return masSintomas;
	}
	public void promedioPagos() {
		float pagosTotales = pagosTotales(head);
		pagosTotales += head.paciente.pago;
		float totalPacientes = contarPacientes(head);
		totalPacientes += 1;
		
		float promedio =  pagosTotales / totalPacientes;
		
		System.out.println("Promedio total de pagos: " + promedio);
	}
	private float pagosTotales(NodoPaciente head) {
		if(head == null) {
			return 0;
		}		
		float derecha = 0;
		if(head.derecha != null) {
			derecha =  pagosTotales(head.derecha);
			derecha += head.derecha.paciente.pago;
		}
		float izquierda = 0;
		if(head.izquierda != null) {
			izquierda =  pagosTotales(head.izquierda);
			izquierda += head.izquierda.paciente.pago;
		}
		float totalPayments = derecha + izquierda;
		return totalPayments;
		
	}
	
	private float contarPacientes(NodoPaciente head) {
		if(head == null) {
			return 0;
		}		

		float derecha = 0;
		if(head.derecha != null) {
			derecha =  contarPacientes(head.derecha);
			derecha += 1;
		}
		
		float izquierda = 0;
		if(head.izquierda != null) {
			izquierda =  contarPacientes(head.izquierda);
			izquierda += 1;
		}

		float totalPatients = derecha + izquierda;
		
		return totalPatients;
		
	}
	
}


