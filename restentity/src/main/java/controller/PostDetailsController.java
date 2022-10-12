package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import output.PostOfficeResponseBean;
import services.IPostService;

@RestController()
@RequestMapping("/postal")
public class PostDetailsController {

    @Autowired
    IPostService postalServiceImpl;

    @RequestMapping(value = "/bycity", method = RequestMethod.GET,
    consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PostOfficeResponseBean getPostalByCity(@RequestParam String city) {
        PostOfficeResponseBean postResponse;
        postResponse = postalServiceImpl.fetchPostOfficeDetailsByCity(city);
        return postResponse;
    }
    
}
