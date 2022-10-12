package services;

import output.PostOfficeResponseBean;

public interface IPostService {
    public PostOfficeResponseBean fetchPostOfficeDetailsByCity(String city);
}
