package com.njust.hsicloud.core.util;

import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URLEncoder;

/**
 * 文件上传下载相关工具类
 */
public class FileUploadTool {



    /**
     * 将上传文件写到本地
     * @param PathName 路径
     * @param part 文件流
     */
    public static   void writeToLocal(String PathName, Part part)  {
        InputStream in = null;
        try {
            in = part.getInputStream();

            OutputStream out = new FileOutputStream(PathName);
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取上传文件的文件名
     * @param part 文件流
     * @return
     */
    public static String getFileName(Part part) {
        String head = part.getHeader("Content-Disposition");
        String fileName = head.substring(head.indexOf("filename=\"")+10, head.lastIndexOf("\""));
        return fileName;
    }


    /**
     * 创建file所指定的文件夹目录 成功返回true(已经存在了也返回true) 失败返回false
     * @return
     */
    public static boolean createDirectory(String filePath) {
        File file=new  File(filePath);
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            if (file.isDirectory()) {
                return true;
            } else {
                return false;
            }
        }
        if (file.mkdirs()) {
            return true;
        }
        return false;
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param filePath 文件的路径
     * @param filename 文件的名称
     * @throws IOException
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, String filePath, String filename) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            // /文件存在，完成下载

            // 下载注意事项1--设置下载文件的mimeType
            String mimeType = request.getServletContext().getMimeType(filename);
            response.setContentType(mimeType);

            String agent = request.getHeader("user-agent").toLowerCase();

            if (agent.contains("msie")) {
                // IE浏览器
                filename = URLEncoder.encode(filename, "utf-8");

            } else if (agent.contains("Firefox")) {
                // 火狐浏览器
                BASE64Encoder base64Encoder = new BASE64Encoder();
                filename = "=?utf-8?B?"
                        + base64Encoder.encode(filename.getBytes("utf-8"))
                        + "?=";
            } else {
                // 其它浏览器
                filename = URLEncoder.encode(filename, "utf-8");
            }

            // 下载注意事项2--永远是下载
            response.setHeader("content-disposition", "attachment;filename="
                    + filename);

            FileInputStream fis = new FileInputStream(file); // 读取要下载文件的内容
            OutputStream os = response.getOutputStream(); // 将要下载的文件内容通过输出流写回到浏览器端.
            int len = -1;
            byte[] b = new byte[1024 * 100];

            while ((len = fis.read(b)) != -1) {
                os.write(b, 0, len);
                os.flush();
            }
            os.close();
            fis.close();

        } else {
            throw new RuntimeException("下载资源不存在.");
        }

    }

    /**
     * 删除文件
     * @param file
     * @return
     */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return false;
        }
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subFile : files) {
                deleteFile(subFile);// 递归删除
            }
        }
        file.delete();
        return true;
    }

}