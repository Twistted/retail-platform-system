package com.laimilife.frame.file.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.laimilife.base.util.ConfigUtil;
import com.laimilife.base.util.DateUtil;
import com.laimilife.base.util.StringUtil;
import com.laimilife.base.vo.Result;
import com.laimilife.frame.account.vo.TbBaseUser;
import com.laimilife.frame.file.model.TbBaseFile;
import com.laimilife.frame.file.service.FileService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileController extends com.laimilife.base.controller.BaseController {
	
	@Autowired
	private FileService fileService;
	

    /**
     * 保存上传的文件
     * @author zhangweiqiang
     * @date 2017年12月20日 20:10:38
     */
    @RequestMapping("/uploadGoodsImg")
    public @ResponseBody Result uploadGoodsImg(@RequestParam("file") CommonsMultipartFile file, HttpSession session) throws IllegalStateException, IOException
    {
        TbBaseUser user = (TbBaseUser) session.getAttribute("user");

        TbBaseFile baseFile = new TbBaseFile();
        baseFile.setId(StringUtil.getUUID32());
        baseFile.setOriginName(file.getOriginalFilename()); // 原文件名字
        String[] fileOrigName = baseFile.getOriginName().split("\\.");
        if(fileOrigName.length <= 1) {
            return setControllerResult("10001", "上传失败，不支持的文件类型!");
        }
        String sufix = fileOrigName[fileOrigName.length-1]; // 文件后缀
        String fileSuf = ConfigUtil.getConfig("uploadImgSuf");
        if(!StringUtil.isNullEmpty(fileSuf)) { // 判断是否支持该类型文件
            String[] sufixs = fileSuf.split(";");
            boolean checkSuf = false;
            for(String suf : sufixs) {
                if(suf.toUpperCase().equals(sufix.toUpperCase())) {
                    checkSuf = true;
                    break;
                }
            }
            if(!checkSuf) {
                return setControllerResult("10002", "上传失败，不支持的文件类型!");
            }
        }
        String configPath = ConfigUtil.getConfig("uploadFilePath");
        if(StringUtil.isNullEmpty(configPath)) {
            return setControllerResult("10001", "上传失败，未配置配文件保存路径!");
        }
        String dirPath = configPath + DateUtil.format(new Date(), "yyyyMMdd") +"/"; 
        makeDir(dirPath); // 创建文件夹
        // 图片基础路径
        String baseFilePath = DateUtil.format(new Date(), "yyyyMMdd") +"/" + baseFile.getId();
        baseFile.setPath(baseFilePath + "." + sufix);
        
        String fileBaseUrl = ConfigUtil.getConfig("fileBaseUrl");
        if(StringUtil.isNullEmpty(fileBaseUrl)) {
            return setControllerResult("10001", "上传失败，未配置配文件下载路径!");
        }
        baseFile.setUrl(fileBaseUrl + baseFile.getPath()); // 前端访问地址
        String realPath = configPath + baseFile.getPath();
        File newFile = new File(realPath);
        newFile.createNewFile();
        
        file.transferTo(newFile);
        fileService.insert(baseFile, user);
        // 压缩图片
        Thumbnails.of(realPath).size(250, 250).toFile(configPath + baseFilePath + "_250_250." + sufix);
        return setControllerResult("0", "上传成功!", baseFile);
    }
    
	/**
     * 保存上传的文件
     * @author zhangweiqiang
     * @date 2017年12月20日 20:10:38
     */
    @RequestMapping("/upload")
    public @ResponseBody Result springUpload(@RequestParam("file") CommonsMultipartFile file, HttpSession session) throws IllegalStateException, IOException
    {
    	TbBaseUser user = (TbBaseUser) session.getAttribute("user");

    	TbBaseFile baseFile = new TbBaseFile();
    	baseFile.setId(StringUtil.getUUID32());
    	baseFile.setOriginName(file.getOriginalFilename()); // 原文件名字
    	String[] fileOrigName = baseFile.getOriginName().split("\\.");
    	if(fileOrigName.length <= 1) {
    	    return setControllerResult("10001", "上传失败，不支持的文件类型!");
    	}
    	String sufix = fileOrigName[fileOrigName.length-1]; // 文件后缀
    	String fileSuf = ConfigUtil.getConfig("uploadFileSuf");
    	if(!StringUtil.isNullEmpty(fileSuf)) { // 判断是否支持该类型文件
    		String[] sufixs = fileSuf.split(";");
    		boolean checkSuf = false;
    		for(String suf : sufixs) {
    			if(suf.toUpperCase().equals(sufix.toUpperCase())) {
    				checkSuf = true;
    				break;
    			}
    		}
    		if(!checkSuf) {
    		    return setControllerResult("10002", "上传失败，不支持的文件类型!");
    		}
    	}
    	String configPath = ConfigUtil.getConfig("uploadFilePath");
    	if(StringUtil.isNullEmpty(configPath)) {
    		return setControllerResult("10001", "上传失败，未配置配文件保存路径!");
    	}
    	String dirPath = configPath + DateUtil.format(new Date(), "yyyyMMdd") +"/"; 
    	makeDir(dirPath); // 创建文件夹
    	baseFile.setPath(DateUtil.format(new Date(), "yyyyMMdd") +"/" + baseFile.getId() + "." + sufix);
    	
        String realPath = configPath + baseFile.getPath();
        File newFile = new File(realPath);
        newFile.createNewFile();
        
        file.transferTo(newFile);
        fileService.insert(baseFile, user);
        return setControllerResult("0", "上传成功!", baseFile);
    }
    

    /**
     * 下载文件
     * @author: zhangweiqiang
     * @date: 2017年12月20日 下午8:09:37
     */
    @RequestMapping("/download")
    public @ResponseBody Result downloadFile(@RequestParam("fileId") String fileId, HttpServletRequest request, HttpServletResponse response) {
    	
    	if (!StringUtil.isNullEmpty(fileId)) {
    		TbBaseFile fileEntity = fileService.selectByPrimaryKey(fileId);
    		String configPath = ConfigUtil.getConfig("uploadFilePath");
    		if(StringUtil.isNullEmpty(configPath)) {
    		    return setControllerResult("10001", "下载失败，未配置配文件路径!");
        	}
            File file = new File(configPath + fileEntity.getPath());
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;filename=" + fileEntity.getOriginName()); // 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * 下载图片文件
     * @author: zhangweiqiang
     * @date: 2017年12月20日 下午8:09:37
     */
    @RequestMapping("/img/**")
    public @ResponseBody Result downloadImg(HttpServletRequest request, HttpServletResponse response) {
    	String uri = request.getRequestURI();
    	String[] path = uri.split("/img/");
    	if(path.length != 2) {
    	    return setControllerResult("10001", "路径不能为空");
    	}
    	String imgPath = path[path.length-1];
    	if (!StringUtil.isNullEmpty(imgPath)) {
    		String configPath = ConfigUtil.getConfig("uploadFilePath");
    		if(StringUtil.isNullEmpty(configPath)) {
                return setControllerResult("10002", "读取文件失败，未配置配文件路径!");
        	}
            File file = new File(configPath + imgPath);
            if (file.exists()) {
                response.addHeader("Content-Disposition", "attachment"); // 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * 创建文件夹
     * @author: zhangweiqiang
     * @date: 2017年12月21日 下午1:45:06
     */
    public void makeDir(String path) {
    	File file = new File(path);
    	if(!file.exists()) {
    		file.mkdir();
    	}
    }
}
