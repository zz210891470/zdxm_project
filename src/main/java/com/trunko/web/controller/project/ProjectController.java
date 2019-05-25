package com.trunko.web.controller.project;

import com.jfinal.core.Controller;
import org.omg.PortableInterceptor.Interceptor;

public class ProjectController extends Controller {

    public  void index(){
        System.out.println(Integer.valueOf("sb"));
        renderJson("麻痹");
    }


}
