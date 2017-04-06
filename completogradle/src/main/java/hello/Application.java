package hello;

//libreria almacenar log
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public static void main (String[] args)
	{
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer(){
		
		return new WebMvcConfigurerAdapter(){
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/greetingrest").allowedOrigins("http://localhost:9090");
			}
		};
	}
	
	@Override
	public void run(String...strings) throws Exception{
		log.info("Creando tablas");
		jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
		
		jdbcTemplate.execute("CREATE TABLE customers(" +
                "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames = Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name -> log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        log.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?", new Object[] { "Josh" },
                (rs, rowNum) -> new CustomerTo(rs.getLong("id"), rs.getString("first_name"), rs.getString("last_name"))
        ).forEach(customer -> log.info(customer.toString()));
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository repository){
		return (args) ->{
			//guardar customer
			repository.save(new Customer("Didier", "Albarran"));
			repository.save(new Customer("Pedro", "Perez"));
			repository.save(new Customer("Juan", "Rodriguez"));
			repository.save(new Customer("Armando","Casas"));
			repository.save(new Customer("Juliana", "Sanchez"));
			repository.save(new Customer("Natalia", "Giraldo"));
			
			//llevar al log todos los elementos de customer
			log.info("Customer entontrados con findAll():");
			log.info("-----------------------------------");
			//recorrer todos los customer
			for(Customer customer: repository.findAll()){
					log.info(customer.toString());
			}
			log.info("");
			
			//buscar un customer por Id			
			Customer customer = repository.findOne(1L);
			log.info("Buscar un customer con findOne");
			log.info("-----------------------------------");
			log.info(customer.toString());
			log.info("");
			
			//buscar customer por atributo lastname
			log.info("Buscar un customer con findByLastName('Albarran')");
			log.info("-----------------------------------");
			for(Customer albarran: repository.findByLastName("Albarran")){
				log.info(albarran.toString());
			}
			log.info("");
			
		};
	}
}