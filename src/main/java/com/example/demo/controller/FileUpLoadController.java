//package com.example.demo.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//@CrossOrigin//跨域
//@RestController
//public class FileUpLoadController {
//
//    @PostMapping("/upload")
//    public String up(String nickname, MultipartFile photo, HttpServletRequest request) throws IOException{
//        System.out.println(nickname);//获取图片的原始名称
//        System.out.println(photo.getOriginalFilename());//获取图片类型
//        System.out.println(photo.getContentType());
//
//        saveFile(photo);
//        return "上传成功";
//    }
//
//    public void saveFile(MultipartFile photo) throws IOException{
//        File dir = new File("H:/vuedownload/");
//        if(!dir.exists()){//目录不存在则创建
//            dir.mkdir();
//        }
//        File file = new File("H:/vuedownload/"+photo.getOriginalFilename());//拼接上文件名称创建文件
//        System.out.println("H:/vuedownload/"+photo.getOriginalFilename());
//        photo.transferTo(file);
//    }
//}
