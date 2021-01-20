package SincronizacionHospital;

public class Medico extends Thread{

	private int identif;
	private Consulta Consulta;
	private Integer tiempoMedico;

	public Medico(int identif, Consulta Consulta) {
		this.identif = identif;
		this.Consulta = Consulta;
		this.tiempoMedico=0;
	}

	public void run() {
		int retardo;
		int numeroPaciente;
		while (Consulta.isPacientesPendientes()) {
			try {
				retardo = (int) (Math.random() * 90 + 10);
				tiempoMedico +=retardo;
				numeroPaciente=Consulta.terminarPaciente(retardo);
				sleep(retardo);
				sleep(2000);
				System.out.println("El Medico " + identif + " ha revisado el paciente " + numeroPaciente + " en un tiempo de " + retardo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Fin del Medico " + identif + ", que termina con un tiempo parcial de " + tiempoMedico);
	}
	
	
}
