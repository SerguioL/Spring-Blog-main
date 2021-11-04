package com.example.springblogmain.controllers;

import com.example.springblogmain.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String post (Model model){
        Post post1 = new Post("titel1","body1");
        Post post2 = new Post("titel2","body2");

        List<Post> Posts = new ArrayList<>();
        Posts.add(post1);
        Posts.add(post2);

       model.addAttribute("posts", Posts);

        return "posts/index";
    }
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
