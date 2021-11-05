package com.example.springblogmain.controllers;

import com.example.springblogmain.models.Post;
import com.example.springblogmain.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    //dependency injection
    private PostRepository postDAo;

    public PostController(PostRepository postDAo) {
        this.postDAo = postDAo;
    }

    @GetMapping("/posts")
//    @ResponseBody
    public String index(Model model) {
        List<Post> posts = postDAo.findAll();
        model.addAttribute("posts",posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}/edit")
    public String returnEditView(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("post", postDAo.getById(id));
        return "posts/edit";
        }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @RequestParam String title, @RequestParam String body){
        Post post = postDAo.getById(id);
        post.setTitle(title);
        post.setBody(body);
        postDAo.save(post);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDAo.deleteById(id);
        return "redirect:/posts";
    }


//    @GetMapping("/posts")
//    public String post (Model model){
//        Post post1 = new Post("titel1","body1");
//        Post post2 = new Post("titel2","body2");
//
//        List<Post> Posts = new ArrayList<>();
//        Posts.add(post1);
//        Posts.add(post2);
//
//       model.addAttribute("posts", Posts);
//
//        return "posts/index";
//    }
    @GetMapping("/posts/1")
    public String individualPost( Model model){
        Post post1 = new Post("titel1","body1");
        model.addAttribute("post",post1);
        return "posts/show";
    }

//    @GetMapping("/posts/{id}")
//    @ResponseBody
//    public String postId(){
//        return "view an individual post";
//    }
//
//    @GetMapping("/posts/create")
//    @ResponseBody
//    public String create(){
//        return "view the form for creating a post";
//    }
//
//    @PostMapping("/posts/create")
//    @ResponseBody
//    public String createPost(){
//        return "create a new post";
//    }



}
