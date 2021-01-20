package SincronizacionHospital;


public class Principal {

	public static void main(String[] args) {
		int medicoRandom = (int) (Math.random() * 4) + 1;
		int pacienteRandom = (int) (Math.random() * 30) + 20;
		Consulta Consulta = new Consulta();
		System.out.println(pacienteRandom + " Pacientes seran atendidos por " + medicoRandom + " Medicos.");
		
		// Creacion de pacientes
		Paciente[] v = new Paciente[pacienteRandom];
		for (int i = 0; i < pacienteRandom; i++) {
			v[i] = new Paciente(i + 1, Consulta);
			v[i].start();
		}
		
		// Creacion de Medicos
		Medico[] p = new Medico[medicoRandom];
		for (int i = 0; i < medicoRandom; i++) {
			p[i] = new Medico(i + 1, Consulta);
			p[i].start();
		}

		// Se espera a que terminen todos los Medicos
		for (int i = 0; i < medicoRandom; i++) {
			try {
				p[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Se espera a que terminen todos pacientes
		for (int i = 0; i < pacienteRandom; i++) {
			try {
				v[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// Se cierra la Consulta
		System.out.println("Se cierra la Consulta con un tiempo acumulado de " + Consulta.getTiempoTotal());
	}
}
