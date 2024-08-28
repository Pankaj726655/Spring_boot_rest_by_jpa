package com.pankaj.springbootrestbyjpa.controller;

import com.pankaj.springbootrestbyjpa.model.JobPost;
import com.pankaj.springbootrestbyjpa.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")     // This tag prevents the conflicts between two diff. platforms. THis line means push the data on the specific front-end link.
public class JobRestController {

    @Autowired
    private JobService service;

    @GetMapping(path = "jobPosts", produces = {"application/json"})     //  This will only return the json format to the client
    @ResponseBody       // The controller by default try to found a view name but by using this tag i tell it to search data not view name
    public List<JobPost> getAllJobs(){
        return service.getAllJobs();
    }

    @RequestMapping("jobPost/{postId}")
    public JobPost getJob(@PathVariable("postId") int postId){          //  To read data from the url and use it as a variable
        return service.getJob(postId);
    }

    @PostMapping("jobPost")
    public JobPost addJob(@RequestBody JobPost jobPost){       // To post data in server using JSON
        service.addJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @DeleteMapping("jobPost/{postId}")
    public String deleteJob(@PathVariable("postId") int postId){
        service.deleteJob(postId);
        return "Deleted!";
    }

    @PutMapping("jobPost")
    public JobPost updateJob(@RequestBody JobPost jobPost){
        service.updateJob(jobPost);
        return service.getJob(jobPost.getPostId());
    }

    @GetMapping("load")
    public String loadData(){
        service.load();
        return "Data loaded successfully";
    }

    @GetMapping("jobPosts/keyword/{keyword}")
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword){
        return service.search(keyword);
    }
}
