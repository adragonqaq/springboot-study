package com.lzl.restTemplate.controller;


import com.lzl.restTemplate.Bean.ResponseBean;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;

/**
 *
 *
 * 在RestTemplate工具类里面，还有一个exchange通用协议请求方法，
 * 它可以发送GET、POST、DELETE、PUT、OPTIONS、PATCH等等HTTP方法请求
 */
public class ExchangeController {




    private static final String UPLOAD_PATH = "/springboot-frame-example/springboot-example-resttemplate/";

    /**
     * 文件上传
     * @param uploadFile
     * @return
     */
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResponseBean upload(@RequestParam("uploadFile") MultipartFile uploadFile,
                               @RequestParam("userName") String userName) {
        // 在 uploadPath 文件夹中通过用户名对上传的文件归类保存
        File folder = new File(UPLOAD_PATH + userName);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        // 对上传的文件重命名，避免文件重名
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."));

        //定义返回视图
        ResponseBean result = new ResponseBean();
        try {
            // 文件保存
            uploadFile.transferTo(new File(folder, newName));
            result.setCode("200");
            result.setMsg("文件上传成功，方法：upload，文件名：" + newName);
        } catch (IOException e) {
            e.printStackTrace();
            result.setCode("500");
            result.setMsg("文件上传失败，方法：upload，请求文件：" + oldName);
        }
        return result;
    }




    /**
     * 带参的get请求(restful风格)
     * @return
     */
    @RequestMapping(value = "downloadFile/{userName}/{fileName}", method = RequestMethod.GET)
    public void downloadFile(@PathVariable(value = "userName") String userName,
                             @PathVariable(value = "fileName") String fileName,
                             HttpServletRequest request,
                             HttpServletResponse response) throws Exception {

        File file = new File(UPLOAD_PATH + userName + File.separator + fileName);
        if (file.exists()) {
            //获取文件流
            FileInputStream fis = new FileInputStream(file);
            //获取文件后缀（.png）
            String extendFileName = fileName.substring(fileName.lastIndexOf('.'));
            //动态设置响应类型，根据前台传递文件类型设置响应类型
            response.setContentType(request.getSession().getServletContext().getMimeType(extendFileName));
            //设置响应头,attachment表示以附件的形式下载，inline表示在线打开
            response.setHeader("content-disposition","attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));
            //获取输出流对象（用于写文件）
            OutputStream os = response.getOutputStream();
            //下载文件,使用spring框架中的FileCopyUtils工具
            FileCopyUtils.copy(fis,os);
        }
    }



}
