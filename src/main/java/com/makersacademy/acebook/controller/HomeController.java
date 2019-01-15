package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

	private final PostRepository postRepository;

	@Autowired
	public HomeController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/greeting")
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);

		return "greeting";
	}

	@RequestMapping(value = "/age")
	@GetMapping("/age")
	public String age(@RequestParam(name="age", required=false, defaultValue="21 again!") String age, Model model) {
		model.addAttribute("age", age);

		return "age";
	}

	@GetMapping("/readPosts")
	public String findAll(Model model) {
		model.addAttribute("foo", postRepository.findAll());
		model.addAttribute("count", postRepository.count());
		Post post = new Post("Hello Peeps!");
		model.addAttribute("greeting", post.getContent());
		return "readPosts";

	}

}
// add a get endpoint that renders an html file.
//post class in model