package edu.eci.arsw.GuidFinderAPI;


import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.eci.arsw.GuidFinderDesktop.GuidFinder;

import java.util.Date; 

@Controller
public class GuidFinderController {
	
	private static final String template = "Hello, %s!";
    private int counter;
    private static UUID[] guids;
    private Date fecha;
    private GuidFinderUUID respuesta;

    @GetMapping("/uuid")
    @ResponseBody
    public GuidFinderUUID sayHello(@RequestParam(name="66b6a7b4-c4c9-43e4-b6e9-f05c6f12f7b7", required=false) String name) {
    	try {
			GuidFinder finder= new GuidFinder();
			counter = finder.countGuids(UUID.fromString("66b6a7b4-c4c9-43e4-b6e9-f05c6f12f7b7"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    	respuesta = new GuidFinderUUID((java.sql.Date) new Date(), UUID.fromString("af11ff4d-92a4-4f0b-bc20-342516256cc6"), counter); 
        return respuesta;
    }
    
    @PostMapping("/uuid")
    GuidFinderUUID newGuidFinderUUID(@RequestBody GuidFinderUUID newGuidFinderUUID) {    	 
		return respuesta;
	} 
}
