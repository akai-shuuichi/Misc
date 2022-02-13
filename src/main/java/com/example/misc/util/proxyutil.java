package com.example.misc.util;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class proxyutil {
    /**
     * url 文件代理中转
     * @param response
     * @param address
     * @param contentType
     * @param fileName
     * @throws IOException
     */
    public static void proxyUrlFile(HttpServletResponse response, String address, String contentType, String fileName) throws IOException {
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(address);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            inputStream = httpURLConnection.getInputStream();

            outputStream = response.getOutputStream();

            if (contentType != null) {
                response.setContentType(contentType);
            } else {
                // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
                response.setContentType("multipart/form-data");
            }
            if (fileName != null) {
                response.setHeader("Content-disposition", "attachment; filename=\""
                        + new String(fileName.getBytes("utf-8"), "ISO8859-1")+"\"");
            }

            // 创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            // 每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            // 使用一个输入流从buffer里把数据读取出来
            while((len = inputStream.read(buffer)) != -1 ){
                // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outputStream.write(buffer, 0, len);
            }

        } catch (Exception e) {
            System.out.println("Proxy URL File error, e = " + e);
            throw e;
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }

}
