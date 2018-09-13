package coder520;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MamaBuyServiceRegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MamaBuyServiceRegistryApplication.class, args);
	}
}
