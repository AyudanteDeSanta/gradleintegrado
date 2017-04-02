package hello;

//libreria almacenar log
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application{
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main (String[] args)
	{
		SpringApplication.run(Application.class, args);
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