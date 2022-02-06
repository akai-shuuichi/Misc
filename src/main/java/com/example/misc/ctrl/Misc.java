package com.example.misc.ctrl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;

@CrossOrigin
@Component
@Controller
public class Misc {
    @ResponseBody
    @RequestMapping("/")
    public String getMisc() {
        return "Misc";
    }
    @RequestMapping("/download")
    public void proxydownload(String url, HttpServletResponse response)throws Exception{
        URL hy=new URL(url);
        PrintWriter writer = response.getWriter();
        InputStream inputStream = hy.openConnection().getInputStream();
        while(inputStream.available()>0){
            writer.write(inputStream.read());
        }
    }
}
