package CodeBenchers006.sb.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CovidTrackerAppSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidTrackerAppSpringBootApplication.class, args);
	}

}
