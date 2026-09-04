package me.prabh.journal;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
@EnableMongoAuditing
public class JournalApplication {

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostConstruct
	public void checkDb() {
		System.out.println(">>> ACTUAL MONGO TEMPLATE DB: " + mongoTemplate.getDb().getName());
	}

	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}

}
