package cn.xuzilin.blog.controller;

import cn.xuzilin.blog.po.UserPo;
import cn.xuzilin.blog.service.ArticleService;
import cn.xuzilin.blog.util.ResponseUtil;
import cn.xuzilin.blog.util.SessionUtil;
import cn.xuzilin.blog.vo.ResponseVo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
public class ArticleController {
    @Resource
    private ArticleService articleService;

    /**
     * 按页数获取文章列表
     * @param page
     * @return
     */
    @GetMapping("/articlelist/{page}")
    public ResponseVo getArticleList(@PathVariable("page") int page){
        JSONArray respData = articleService.getArticleListByPage(page);
        return ResponseUtil.success("success",respData);
    }

    /**
     * 按id获取文章
     * @param id
     * @return
     */
    @GetMapping("/getArticle/{id}")
    public ResponseVo getArticle(@PathVariable("id") long id){
        JSONObject respData = articleService.getArticleById(id);
        return ResponseUtil.success("success",respData);
    }

    /**
     * 上传新文章
     * @param request
     * @param map
     * @return
     */
    @PostMapping("/uploadPassage")
    public ResponseVo uploadPassage(HttpServletRequest request , @RequestBody Map<String,String> map){
        UserPo userPo = SessionUtil.get(request,"userToken", UserPo.class);
        if (userPo == null) return ResponseUtil.error("未登录！");
        String title = map.get("title");
        String text = map.get("text");
        long respData = articleService.saveArticle(title,text,userPo.getUser_id());
        return ResponseUtil.success("success",respData);
    }
    @PostMapping("/uploadPicture")
    public String contentFileUpload(MultipartFile picture) {
        if (picture!=null && picture.getOriginalFilename()!=null && picture.getOriginalFilename().trim().length()>0) {

            /**
             * picture上传路径（+时间文件夹）
             */
//            //真实的上传根路径
//            String realUploadPath = "/usr/apache-tomcat-9.0.5/webapps/StarryBlog/images/uploadPictures";
//            //虚拟的文件访问根路径
//            String fictitiousRoot = "../images/uploadPictures";

            //真实的上传根路径
            String realUploadPath = "/home/ubuntu/blog/upload";
            //虚拟的文件访问根路径
            String fictitiousRoot = "/file";

            //建立以时间命名的文件夹
            SimpleDateFormat sdf=new SimpleDateFormat("/yyyy/MM/dd/");
            String datePath = sdf.format(new Date());
            //最终真实路径
            String realuUploadBrandPath = realUploadPath+"/content"+datePath;
            //最终虚拟访问路径
            String  fictitiousUploadBrandPath =fictitiousRoot +"/content"+datePath;

            // 上传文件原始名称
            String originFileName = picture.getOriginalFilename();
            // 新的文件名称
            String newFileName = UUID.randomUUID()+originFileName.substring(originFileName.lastIndexOf("."));


            //如果路径文件夹不存在就创建
            File dir=new File(realuUploadBrandPath);
            if(!dir.exists()){
                dir.mkdirs();
            }

            // 新文件
            File newFile = new File(realuUploadBrandPath+File.separator+newFileName);

            // 将内存中的文件写入磁盘
            try {
                picture.transferTo(newFile);
            } catch (IllegalStateException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // 文件虚拟地址
            String fictitiousFilePath =  fictitiousUploadBrandPath+newFileName;
            return fictitiousFilePath;
        }

        return "false";

    }
}
