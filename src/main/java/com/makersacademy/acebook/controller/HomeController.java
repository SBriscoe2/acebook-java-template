package com.makersacademy.acebook.controller;

import com.makersacademy.acebook.model.Post;
import com.makersacademy.acebook.model.PostForm;
import com.makersacademy.acebook.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

	@GetMapping("/post")
	public String post(Model model) {
		model.addAttribute("post", new PostForm("Add your message here: ", "Add the title of your post here: "));
		return "postForm";
	}

	@PostMapping("/post")
	public String post(@ModelAttribute Post post) {
		System.out.println(post);
		postRepository.save(post);
		System.out.println(postRepository.findAll());
		return "redirect:/allposts";
	}

	@GetMapping("/allposts")
	public String allposts(Model model) {
		model.addAttribute("list", postRepository.findAll());
		return "allposts";
	}

	@GetMapping("/updatepost")
	public String updatePost(Model model) {
		model.addAttribute("singlepost", postRepository.findById(2l).get());
		Post post = postRepository.findById(2l).get();
		post.setContent("Please update for the love of all things good!!!");
		postRepository.save(post);
		return "updatepost";
	}

	@GetMapping("/deletepost")
	public String deletepost(Model model){
		model.addAttribute("userid", postRepository.findById(2l).get());
		Post post = postRepository.findById(2l).get();
		postRepository.delete(post);
		return "redirect:/allposts";
	}
}
