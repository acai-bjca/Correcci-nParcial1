package edu.eci.arsw.GuidFinderDesktop;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GuidFinderDesktopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuidFinderDesktopApplication.class, args);
		
		try {
			GuidFinder finder= new GuidFinder();
			System.out.println(finder.countGuids(UUID.fromString("66b6a7b4-c4c9-43e4-b6e9-f05c6f12f7b7")));
			//d0692660-c39a-4d73-9496-d9df0c4ebdf3
			//66b6a7b4-c4c9-43e4-b6e9-f05c6f12f7b7
			//53f52fdd-5c01-4c6a-b6b6-a1c74f6518af
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
