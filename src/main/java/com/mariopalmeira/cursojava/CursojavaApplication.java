package com.mariopalmeira.cursojava;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursojavaApplication implements CommandLineRunner {
	
//	@Autowired
//	private S3Service s3Service;
	
	public static void main(String[] args) {
		SpringApplication.run(CursojavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		////No java, os caminhos devem ter barras duplas
		//s3Service.enviarArquivo("C:\\Users\\mhpal\\Pictures\\landscape_mountains_sun_140434_3840x2160.jpg");		
	}
}
