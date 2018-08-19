package com.ipaozha.emall.controller;

import com.ipaozha.emall.model.AbcModel;
import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.util.UUID;

@Controller
@RequestMapping("/home")
public class HomeController {


    public static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/abc")
    public String hehe(HttpServletRequest request, Model model) {
        AbcModel abcModel = new AbcModel();
        abcModel.setA("大家好");
        model.addAttribute("abc", abcModel);
        return "abc";
    }

    //    restful风格请求
    @RequestMapping(value = "/abc2/{abc}", method = RequestMethod.GET)
    public String hehe2(HttpServletRequest request, @PathVariable("abc") String path, Model model) {

        logger.debug(request.getContextPath());
        AbcModel abcModel = new AbcModel();
        abcModel.setA(path);
        model.addAttribute("abc", abcModel);
        return "abc";
    }

    //本方法将处理 /abc3?abc=123 形式的URL
    @RequestMapping("/abc3")
    public String hehe3(HttpServletRequest request) {
        String abc = request.getParameter("abc");

        logger.debug(abc);
        AbcModel abcModel = new AbcModel();
        abcModel.setA(abc);
        request.setAttribute("abc", abcModel);
        return "abc";
    }


    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String upload(HttpServletRequest request) {
        return "upload";

    }

    @ResponseBody
    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUpload(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        if (file != null) {
            //获取上传文件的原始名称
            String originalFilename = file.getOriginalFilename();
            String newFileName = "";
            String pic_path;
            // 上传图片
            if (originalFilename != null && originalFilename.length() > 0) {
                //获取Tomcat服务器所在的路径
                String tomcat_path = System.getProperty("user.dir");
                System.out.println(tomcat_path);
                //获取Tomcat服务器所在路径的最后一个文件目录
                String bin_path = tomcat_path.substring(tomcat_path.lastIndexOf("/") + 1, tomcat_path.length());
                System.out.println(bin_path);
                //若最后一个文件目录为bin目录，则服务器为手动启动
                if (("bin").equals(bin_path)) {//手动启动Tomcat时获取路径为：D:\Software\Tomcat-8.5\bin
                    //获取保存上传图片的文件路径
                    pic_path = tomcat_path.substring(0, System.getProperty("user.dir").lastIndexOf("/")) + "/webapps" + "/pic_file/";
                } else {//服务中自启动Tomcat时获取路径为：D:\Software\Tomcat-8.5
                    pic_path = tomcat_path + "/webapps" + "/pic_file/";
                }
                // 新的图片名称
                newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
                logger.info("上传图片的路径：" + pic_path + newFileName);
                // 新图片
                File newFile = new File(pic_path + newFileName);
                // 将内存中的数据写入磁盘
                file.transferTo(newFile);
            }
            return newFileName;
        } else {
            return null;
        }

    }
}

//    @RequestMapping("img")
//    public ModelAndView GetById(int nid, HttpServletRequest request) throws SocketException {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("jsp/pic_show_test");
//        Notice notice = noticeService.GetById(nid);
//        mv.addObject("person_name",notice.getPerson_name());
//        mv.addObject("title",notice.getNotice_title());
//        mv.addObject("content",notice.getNotice_content());
//        Date notice_time = notice.getNt();
//        mv.addObject("notice_time",new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(notice_time));
        //封装图片显示路径（根据当前访问的客户端请求的地址来封装）
//        String request_path = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
////        String img_name = request_path + "/pic_file/"+notice.getNotice_img();
//        //或者直接用下面的相对路径封装
//        String img_name = "../pic_file/"+"86a0f6d2-adf7-4640-b71f-d3c2453e9da7.png";
////        mv.addObject("img_name",img_name);
////        return mv;
////        return  img_name;
//    }
