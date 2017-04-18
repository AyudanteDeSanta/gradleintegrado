package hello;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
public class GreetingRestController{
	
	private static final String template = "Hola, %s!!!";
	private final AtomicLong counter = new AtomicLong();
	
	//@CrossOrigin(origins="http://localhost:9000")
	//@GetMapping(value="/greetingrest", produces={MediaType.APPLICATION_JSON_VALUE})	
	@GetMapping("/greetingrest")
	//@ResponseBody
	//public Greeting greeting(@RequestParam(required=false, defaultValue="Mundo") String name)	{
	public List greeting(){
		System.out.println("----------------En greetintrest----------------");
		ArrayList lista = new ArrayList();
		lista.add(new Greeting(counter.incrementAndGet(), String.format(template, "Didier")));
		lista.add(new Greeting(counter.incrementAndGet(), String.format(template, "Didier2")));
		
		return lista;
		//return new Greeting(counter.incrementAndGet(), String.format(template, "Didier"));
	}
}


