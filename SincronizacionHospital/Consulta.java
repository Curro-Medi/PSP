package SincronizacionHospital;

import java.util.PriorityQueue;
import java.util.concurrent.Semaphore;

public class Consulta {
		private Semaphore semaforo;
		private PriorityQueue <Integer> listaPacientes;
		private Integer tiempoTotal;

		public Consulta() {
			semaforo = new Semaphore(1);
			listaPacientes = new PriorityQueue <Integer>();
			tiempoTotal = 0;
		}

		public void nuevoPaciente(Integer numeroPaciente) {
			try {
				semaforo.acquire();
				listaPacientes.add(numeroPaciente);
				semaforo.release();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public int terminarPaciente(Integer tiempoParcial) {
			int paciente=0;
			try {
				if (isPacientesPendientes()) {
					semaforo.acquire();
					paciente = listaPacientes.poll();
					tiempoTotal += tiempoParcial;
					semaforo.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return paciente;
		}

		public boolean isPacientesPendientes() {
			return listaPacientes.size() > 0;
		}
		
		public Integer getTiempoTotal () {
			return tiempoTotal;
		}
		
	}
