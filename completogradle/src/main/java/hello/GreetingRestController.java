package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class GreetingRestController{
	
	private static final String template = "Hola, %s!!!";
	private final AtomicLong counter = new AtomicLong();
	
	@CrossOrigin(origins="http://localhost:9000")
	@GetMapping("/greetingrest")	
	public Greeting greeting(@RequestParam(required=false, defaultValue="Mundo") String name)	{
		System.out.println("----------------En greetintrest----------------");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
}

