package com.yonyou.message.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yonyou.message.po.MessageFile;
import com.yonyou.message.service.IErMessageFileService;
import com.yonyou.message.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
/**
 * Created by Administrator on 2017/6/21.
 */
@Controller
@RequestMapping(value = "/message")
public class FileController {
    @Autowired
    private IErMessageFileService erMessageFileService;
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public @ResponseBody void fileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String messageid = request.getParameter("messageid");
        String uploader = request.getParameter("uploader");
        JSONObject json = new JSONObject();
        try {
            if(messageid == null || messageid.toString().equals("")){
                json.put("code", "1");
                json.put("message", "messageid接收失败!");
            } else if (uploader ==null || uploader.toString().equals("")) {
                json.put("code", "1");
                json.put("message", "uploader接收失败!");
            } else {
                CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                        request.getSession().getServletContext());
                // 判断 request 是否有文件上传,即多部分请求
                if (multipartResolver.isMultipart(request)) {
                    // 转换成多部分request
                    MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                    // 取得request中的所有文件名
                    Iterator<String> iter = multiRequest.getFileNames();
                    while (iter.hasNext()) {
                        // 取得上传文件
                        MultipartFile f = multiRequest.getFile(iter.next());
                        if (f != null) {
                            // 取得当前上传文件的文件名称
                            String myFileName = f.getOriginalFilename();
                            if (myFileName.length() > 20) {
                                throw new Exception("文件名超过20位!请重新输入!");
                            }
                            MessageFile file_exist = erMessageFileService.getMessageFileByFileName(myFileName,messageid);
                            if (file_exist != null) {
                                throw new Exception("文件已存在!");
                            } else {
                                // 如果名称不为“”,说明该文件存在，否则说明该文件不存在
                                if (myFileName.trim() != "") {
                                    // 定义上传路径
                                    String path = request.getServletContext().getRealPath("/files/") + "/" + messageid + "/"
                                            + myFileName;
                                    File localFile = new File(path);//判断路径是否存在，如果不存在就创建一个
                                    if (!localFile.getParentFile().exists()) {
                                        localFile.getParentFile().mkdir();
                                    }

                                    MessageFile file = new MessageFile();
                                    file.setFile_name(myFileName);
                                    file.setFile_path(myFileName);
                                    file.setMessageid(messageid);
                                    file.setFile_time(TimeUtils.getCurrentTime());
                                    file.setFile_uploader(uploader);
                                    erMessageFileService.insert(file);

                                    f.transferTo(localFile);
                                    json.put("file","");
                                }
                            }

                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            json.put("message", e.getMessage());
            json.put("code","1");
        }finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
            response.flushBuffer();
        }

    }

    @RequestMapping(value = "/fileDownload", method = RequestMethod.GET)
    public ResponseEntity<byte[]> fileDownload (HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        File file=null;
        HttpHeaders headers=null;
        try {
            String fileid = request.getParameter("fileid");
            if (fileid == null || fileid.toString().equals("")) {
                json.put("code", "1");
                json.put("message", "fileid接收失败!");
            } else {
                MessageFile myfile = erMessageFileService.getMessageFileByFileId(fileid);
                String path = request.getServletContext().getRealPath("/files/");
                file = new File(path + File.separator + myfile.getMessageid() + File.separator + myfile.getFile_name());
                headers = new HttpHeaders();
                //下载显示的文件名，解决中文名称乱码问题
                String downloadFielName = new String(myfile.getFile_name().getBytes("UTF-8"), "iso-8859-1");
                //通知浏览器以attachment（下载方式）打开图片
                headers.setContentDispositionFormData("attachment", downloadFielName);
                //application/octet-stream ： 二进制流数据（最常见的文件下载）。
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/getMessageFile", method = RequestMethod.POST)
    public @ResponseBody void getMessageFile(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JSONObject json = new JSONObject();
        try {
            String messageid = request.getParameter("messageid");
            if (messageid == null || messageid.toString().equals("")) {
                json.put("code", "1");
                json.put("message", "messageid接收失败!");
            } else {
                List<MessageFile> files = erMessageFileService.getMessageFile(messageid);
                String filejson = JSON.toJSONString(files);
                json.put("files", filejson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json.toString());
            response.flushBuffer();
        }
    }
}
