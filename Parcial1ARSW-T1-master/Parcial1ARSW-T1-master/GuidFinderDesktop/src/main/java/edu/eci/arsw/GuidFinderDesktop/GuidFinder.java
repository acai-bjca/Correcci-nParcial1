package edu.eci.arsw.GuidFinderDesktop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class GuidFinder extends Thread {
	private AtomicInteger count;
	private static UUID[] guids;
	private static final int CANTHILOS = 4;
	private AtomicInteger hilosFinalziados;
	private GuidFinderThread[] hilos;
	private Scanner sc;
	private boolean vivo;

	public GuidFinder() throws Exception {
		getGuids();
		this.count = new AtomicInteger(0);
		this.hilos = new GuidFinderThread[4];
		this.sc = new Scanner(System.in);
		this.hilosFinalziados = new AtomicInteger(0);
		this.start();
		this.vivo = true;
	}

	public static UUID[] getGuids() throws Exception {
		if (guids == null) {
			// System.out.println("es nulo");
			FileInputStream fi;
			fi = new FileInputStream(new File("guids.eci"));

			ObjectInputStream oi = new ObjectInputStream(fi);

			guids = (UUID[]) oi.readObject();
			int c = 0;
			for (int i = 0; i < guids.length; i++) {
				// System.out.println(guids[i].toString());
				if (guids[i].equals(UUID.fromString("af11ff4d-92a4-4f0b-bc20-342516256cc6"))) {
					c++;
				}
			}
			System.out.println(c);
			oi.close();
			fi.close();
		}
		return guids;
	}

	@Override
	public void run() {
		while (hilosFinalziados.get() < CANTHILOS - 1) {
			System.out.println("hilosFinalziados: "+hilosFinalziados);
			while (vivo) {
				if (hilosFinalziados.get() == CANTHILOS - 1) {
					vivo = false;
				}
				System.out.println("ESCRIBA ALGO: ");
				if (sc.nextLine() != null) {
					System.out.println("Pausar");
					try {
						System.out.println("suspendidos: ");
						for (int i = 0; i < CANTHILOS; i++) {
							hilos[i].setSuspender(true);
						}
						Thread.sleep(3000);
						System.out.println("arrancaron: ");
						for (int i = 0; i < CANTHILOS; i++) {
							hilos[i].setSuspender(false);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public int countGuids(UUID guidToFind) {
		int guidsXHilo = guids.length / CANTHILOS;
		int segmento = 0;
		for (int i = 0; i < CANTHILOS; i++) {
			if (i == CANTHILOS - 1)
				hilos[i] = new GuidFinderThread(segmento, (guids.length) - 1, guids, guidToFind, count, hilosFinalziados);
			else
				hilos[i] = new GuidFinderThread(segmento, segmento + guidsXHilo, guids, guidToFind, count, hilosFinalziados);
			segmento += guidsXHilo;
		}

		for (int i = 0; i < CANTHILOS; i++) {
			hilos[i].start();
		}

		for (int i = 0; i < CANTHILOS; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return count.get();

	}

}
