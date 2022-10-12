package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import output.PostOfficeDetailsBean;
import output.PostOfficeResponseBean;
import services.IPostService;

@Service("postalServiceImpl")
public class PostalServiceImpl implements IPostService  {

    RestTemplate restTemplate;

    @Override
    public PostOfficeResponseBean fetchPostOfficeDetailsByCity(String cityValue) {
        String url = "https://api.postalpincode.in/postoffice/{city}";
        url = url.replace("{city}", cityValue);
        System.out.println("Url is :"+url);

        ResponseEntity<PostOfficeResponseBean[]> postalResponseEntity = 
        restTemplate.getForEntity(url,PostOfficeResponseBean[].class );
       
        System.out.println("Response status code is :"+ postalResponseEntity.getStatusCode());
        PostOfficeResponseBean[] responseBeansArray = postalResponseEntity.getBody();
        
        for (PostOfficeResponseBean responseBean : responseBeansArray) {
            List<PostOfficeDetailsBean> postOfficeListBean = responseBean.getPostOffice();
            for (PostOfficeDetailsBean pob : postOfficeListBean) {
                System.out.println(pob.getName()+"****"+pob.getCountry()+"***"+pob.getPincode());
            }
        }
        return responseBeansArray[0];
    }
    
}
