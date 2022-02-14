package com.example.misc.ctrl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.example.misc.util.proxyutil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLDecoder;

@CrossOrigin
@Component
@Controller
public class Misc {

    @RequestMapping("/")
    public String getMisc() {
        return "h1/index";
    }
    @RequestMapping("/download2")
    public void proxydownload(String url, String method,HttpServletRequest request, HttpServletResponse response)throws Exception {
        HttpRequest treq;
        if ("POST".equals(method)) {
            treq = HttpUtil.createPost(url);
        } else {
            treq = HttpUtil.createGet(url);
        }
        InputStream inputStream = treq.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36").execute().bodyStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        OutputStream outputStream = response.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

        byte[] buffer = new byte[1024];
        int len;
        while ((len = bufferedInputStream.read(buffer)) != -1) {
            bufferedOutputStream.write(buffer, 0, len);
            System.out.println(len);
        }
    }
    @RequestMapping("/download")
    public void download2(@RequestParam("url")String url, String method,HttpServletRequest request, HttpServletResponse response)throws Exception {
        url=URLDecoder.decode(url, "utf-8");
        //获取文件名
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        System.out.println(fileName);
        proxyutil.proxyUrlFile(response, url, null, fileName);
    }


}
