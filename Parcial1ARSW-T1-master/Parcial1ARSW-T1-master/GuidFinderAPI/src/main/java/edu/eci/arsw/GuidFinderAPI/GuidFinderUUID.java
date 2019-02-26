package edu.eci.arsw.GuidFinderAPI;

import java.sql.Date;
import java.util.UUID;

public class GuidFinderUUID {
	private Date fecha;
	private UUID guid;
	private int cont;
	
	public GuidFinderUUID(Date fecha, UUID guid, int cont) {
		this.fecha = fecha;
		this.guid = guid;
		this.cont = cont;		
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public UUID getGuid() {
		return guid;
	}

	public void setGuid(UUID guid) {
		this.guid = guid;
	}

	public int getCont() {
		return cont;
	}

	public void setCont(int cont) {
		this.cont = cont;
	}

	
	
	
}
