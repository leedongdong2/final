package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	@RequestMapping(value = "home.main",method = {RequestMethod.GET,RequestMethod.POST}) 
	public ModelAndView mainView(ModelAndView mv) {
		mv.setViewName("board/home");
		
		return mv;
	}
}
