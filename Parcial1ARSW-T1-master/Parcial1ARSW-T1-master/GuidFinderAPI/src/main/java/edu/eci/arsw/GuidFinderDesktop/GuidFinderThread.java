package edu.eci.arsw.GuidFinderDesktop;

import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class GuidFinderThread extends Thread {
	private UUID[] guids;
	private UUID guidToFind;
	private AtomicInteger count;
	private int inicio;
	private int fin;
	private AtomicInteger hilosFinalziados;
	private boolean suspender;
	private boolean vivo;
	

	public GuidFinderThread(int inicio, int fin, UUID[] guids, UUID guidToFind, AtomicInteger count, AtomicInteger hilosFinalziados) {
		this.inicio = inicio;
		this.fin = fin;
		this.guids = guids;
		this.guidToFind = guidToFind;
		this.count = count;
		this.suspender = false;
		this.vivo = true;
		this.hilosFinalziados = hilosFinalziados;
	}

	@Override
	public void run() {
		while(vivo) {
			/*
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e1) {				
				e1.printStackTrace();
			}*/
			if(suspender) {
				synchronized (this) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			for (int i = inicio; i < fin; i++) {
				// System.out.println(uuid.toString());
				if (guids[i].equals(guidToFind)) {
					System.out.println("Encontro: " + guids[i].toString());
					System.out.println(i);
					count.incrementAndGet();
				}
			}
			hilosFinalziados.incrementAndGet();
			vivo=false;
		}
	}
	public void setSuspender(boolean suspender) {
		this.suspender=suspender;
		if(suspender==false) {
			synchronized (this) {
				this.notify();
			}
		}
	}
	
}
