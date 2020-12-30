package com.yc.travel.product.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yc.pics.domain.PicsDomain;
import com.yc.travel.pics.controllers.PicController;

import com.yc.travel.pics.services.AsyncThreadPool;
import com.yc.travel.pics.services.FastefsClient;
import com.yc.travel.product.future.TravelProductFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/travel/product")
public class productController {
    private static Logger logger = LoggerFactory.getLogger(PicController.class.getName());
    @Value("${file.path.head:http://101.37.202.175/}")
    private String pathHead;
    @Autowired
    private TravelProductFuture travelProductFuture;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public CompletableFuture<String> findAll(Integer page, Integer limit) {
        return travelProductFuture.findPage(page, limit);
    }

    @Autowired
    private FastefsClient fastefsClient;

    /**
     * 上传图片
     *
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void uploadPic(@RequestParam("pictureFile") MultipartFile multipartFile,
                          @RequestParam("pname") String pname,
                          @RequestParam("tno") Integer tno,
                          @RequestParam("price") Integer price,
                          @RequestParam("intro") String intro,
                          @RequestParam("balance") Integer balance,
                          @RequestParam("company") String company,
                          HttpServletRequest request, HttpServletResponse response) {
        try {
            String filename = fastefsClient.uplodFile(multipartFile);
            AsyncThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("文件通过nginx访问的路径:" + (pathHead + filename));
                        create(multipartFile, pathHead + filename ,pname, tno, price, intro, balance,
                                company);  //调用数据库操作
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            BufferedImage image = ImageIO.read(multipartFile.getInputStream());
            Map<String, Object> data = new HashMap<String, Object>();
//            data.put("pathInfo", pathHead + filename);
//
//            data.put("width", image.getWidth());
//            data.put("height", image.getHeight());
//
//            ObjectMapper mapper = new ObjectMapper();
//            String ret = mapper.writeValueAsString(data);
//
//            response.setContentType("text/html;charset=utf8");
//            response.getOutputStream().write(ret.getBytes());
//            response.flushBuffer();
              response.getOutputStream().write("添加成功".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public CompletableFuture<String> create(MultipartFile multipartFile, String filename,String pname,Integer tno,Integer price,String intro,Integer balance,
                                            String company) throws Exception  {
        //BufferedImage image = ImageIO.read(multipartFile.getInputStream());
        return travelProductFuture.create(pname, tno, price, intro, balance, company, filename);
    }
//    //数据库操作
//    private CompletableFuture<String> savePic(MultipartFile multipartFile, String filename, int pid) throws Exception {
//        BufferedImage image = ImageIO.read(multipartFile.getInputStream());
//        PicsDomain picsDomain = new PicsDomain();
//        picsDomain.setPid(pid);
//        picsDomain.setPicpath(filename);
//        return travelProductFuture.create().thenApply(info -> {
//
//            return info;
//        });
//    }
    /**
     * 前端中pid,tno不使用需要为0
     * @param pname
     * @param pid
     * @param tno
     * @return
     */
    @RequestMapping(value = "/findBy", method = RequestMethod.POST)
    public CompletableFuture<String> findBy( String pname,Integer pid,Integer tno) {
        return travelProductFuture.findBy(pname,pid,tno);
    }

    @RequestMapping(value = "/{id}")
    public CompletableFuture<String> findById(@PathVariable Integer id) {
        return travelProductFuture.findById(id);
    }



}
