package Sincronizacion;

import java.io.ObjectInputStream.GetField;
import java.util.concurrent.Semaphore;

public class Variable {

	
	int i = 0;
	
	public static void main(String[] args) {
		
		
		Variable ejemplo = new Variable();
		Semaphore semaforo = new Semaphore(1);
		
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					
					try {

						semaforo.acquire();
						//adquirimos el control de la region critica
						ejemplo.printSomething();
						Thread.sleep(1000);
						
						semaforo.release();
						//dejo el control de la region critica
						
												
					}catch (Exception e){
						System.out.println("Interrupcion");
						
					}
					
				}
				
				
			}
		};
		
		new Thread (r).start();
		new Thread (r).start();
		new Thread (r).start();
		new Thread (r).start();
		
		
	}
	
	public void printSomething() {
		
		i++;
		System.out.println("El valor de i es = " + i + " " + Thread.currentThread().getName());
		
	}
	
}
