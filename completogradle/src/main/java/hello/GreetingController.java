package hello;

//este lo uso para definir una clase coomo controlador
import org.springframework.stereotype.Controller; 

import org.springframework.ui.Model;
//para mapear un request get
import org.springframework.web.bind.annotation.GetMapping;
//para mapear un request post
import org.springframework.web.bind.annotation.PostMapping;
//para definir un parametro como parte del modelo
import org.springframework.web.bind.annotation.ModelAttribute;
//marcando método como una view, y el parámetro es la url accesible
import org.springframework.web.bind.annotation.RequestMapping;
//marcación de parametros de las peticiones http
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
		
	@GetMapping("/greetingget")
	public String greetingForm(Model model){
		model.addAttribute("greeting", new Greeting(1 , "didier"));	
		return "greeting";
	}
	
	@PostMapping("/greetingget")
	public String greetingSubmit(@ModelAttribute Greeting greeting){
		return "result";
	}
	
	/*@RequestMapping("/greetingget")
	public String greeting(@RequestParam(value="name", required=false, defaultValue = "Mundo") String name, Model model ){
		//asociar paraemtro name del request con el parámetro de entrada
		model.addAttribute("name", name);
		return "greeting";
	}*/
	

}
