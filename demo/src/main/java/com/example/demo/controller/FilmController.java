package com.example.demo.controller;

import com.example.demo.bean.Film;
import com.example.demo.bean.ImageUtil;
import com.example.demo.dao.FilmDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.io.*;
import java.util.UUID;

@RestController
public class FilmController {
    @Autowired
    FilmDao filmDao;
    Film newfilm = new Film();

    //先定义访问地址（只包括协议、ip地址和端口号）
    private  final String URL = "http://localhost:9000/";

    //查询所有电影
    @RequestMapping("/selectAllFilm")
    public List<Film> SelectAllFilm(HttpServletRequest request){
        List<Film> allFilm = filmDao.selectAllFilm();
        System.out.println("allFilm:"+allFilm);
        return allFilm;
    }

    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {
        //获取项目classes/static的地址
        String path = this.getClass().getClassLoader().getResource("static").getFile();
        String fileName = file.getOriginalFilename(); //获取文件名
        //图片访问URI(即除了协议、地址和端口号的URL)
        String url_path = "image"+fileName;
        System.out.println("图片访问uri："+url_path);
        String savePath = path+File.separator+url_path;  //图片保存路径
        System.out.println("图片保存地址："+savePath);
        File saveFile = new File(savePath);

        if (!saveFile.exists()){
            saveFile.mkdirs();
        }
        try {
            file.transferTo(saveFile);  //将临时存储的文件移动到真实存储路径下
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回图片访问地址
        System.out.println("访问URL："+URL+url_path);
        newfilm.setFilm_picture_url(URL+url_path);
        return "OK";
    }

    //增加电影
    @RequestMapping("/addOneFilm")
    public String AddOneFilm(HttpServletRequest request, @RequestBody Film film) throws FileNotFoundException {
        List<Film> addFilm = filmDao.addFilm(film.getFilm_id(),film.getFilm_name(),film.getFilm_score(),
                film.getFilm_rank(),film.getFilm_style(),film.getDirector(),film.getActor(),
                film.getFilm_introduction(),film.getFilm_picture_url());
        return "OK";
    }

    //删除一部电影
    @RequestMapping("/deleteOneFilm")
    public List<Film> DeleteOneFilm(HttpServletRequest request,@RequestBody Film film){
        List<Film> deleteOneFilm = filmDao.deleteOneFilm(film.getFilm_id());
        return deleteOneFilm;
    }

    //查找指定id的电影
    @RequestMapping("/selectOneFilm")
    public List<Film> SelectOneFilm(HttpServletRequest request,@RequestBody Film film){
        List<Film> oneFilm = filmDao.selectOneFilm(film.getFilm_id());
        return oneFilm;
    }

    //编辑指定id的电影
    @RequestMapping("/updateOneFilm")
    public List<Film> UpdateOneFilm(HttpServletRequest request,@RequestBody Film film){
        List<Film> updateFilm = filmDao.updateOneFilm(film.getFilm_id(),film.getFilm_name(),film.getFilm_score(),
                film.getFilm_rank(),film.getFilm_style(),film.getDirector(),film.getActor());
        return updateFilm;
    }
}
