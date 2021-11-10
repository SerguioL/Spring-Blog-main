package com.example.springblogmain.controllers;

import com.example.springblogmain.models.Post;
import com.example.springblogmain.models.User;
import com.example.springblogmain.repositories.PostRepository;
import com.example.springblogmain.repositories.UserRepository;
import com.example.springblogmain.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    //dependency injection
    private PostRepository postDao;

    private UserRepository userDao;

    private final EmailService emailService;

    public PostController(PostRepository postDAo, UserRepository userDao, EmailService emailService) {
        this.postDao = postDAo;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
//    @ResponseBody
    public String index(Model model) {
        List<Post> posts = postDao.findAll();
        model.addAttribute("posts",posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}/edit")
    public String returnEditView(@PathVariable long id, Model viewModel){
        viewModel.addAttribute("post", postDao.getById(id));
        return "posts/edit";
        }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@PathVariable long id, @ModelAttribute Post post){

        Post editPost = postDao.getById(post.getId());
        editPost.setTitle(post.getTitle());
        editPost.setBody(post.getBody());

        postDao.save(editPost);
        return "redirect:/posts";
    }

    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        postDao.deleteById(id);
        return "redirect:/posts";
    }

    @GetMapping("/posts/create")
    public String create(Model model){
        model.addAttribute("post",new Post());
        return "posts/create";
    }

    @PostMapping("/posts/create")
    public String insert(@ModelAttribute Post post){
//        User user = userDao.getById(3L);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(user);
        postDao.save(post);
        emailService.prepareAndSend(post, "you Created " + post.getTitle() , post.getBody());
        return "redirect:/posts";
    }

//    @PostMapping("/posts/create")
//    public String insert(@RequestParam String title, @RequestParam String body){
//
//        User user = userDao.getOne(1L);
//
//        Post post = new Post();
//        post.setTitle(title);
//        post.setBody(body);
//        post.setUser(user);
//        postDao.save(post);
//        return "redirect:/posts";
//    }

//    @GetMapping("/posts/{id}/show")
//    public String showPost(@PathVariable long id, Model model){
//        Post post = postDao.getById(id);
//
//        model.addAttribute("post",post);
//        return "posts/show";
//    }


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
//    @GetMapping("/posts/1")
//    public String individualPost( Model model){
//        Post post1 = new Post("titel1","body1");
//        model.addAttribute("post",post1);
//        return "posts/show";
//    }

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
