package pfc.cebem.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;


@ComponentScan(basePackages = { "pfc.cebem.back.*", "pfc.cebem.back.repository.*" })
@EntityScan("pfc.cebem.back.entity.*")
@SpringBootApplication
public class BackApplication {

	@Bean
	public RestTemplate getresttemplate(){
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(BackApplication.class, args);
	}

}
