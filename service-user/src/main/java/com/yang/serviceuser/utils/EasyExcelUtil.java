package com.yang.serviceuser.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author rock
 * @detail
 * @date 2020/6/24 11:05
 */
@Slf4j
public class EasyExcelUtil {

    /**
     * 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
     * @param dataList
     * @param dataClass
     * @param fileName
     * @param resp
     * @param <T>
     */
    public static <T> void simpleWrite(List<T> dataList, Class<T> dataClass, String fileName, HttpServletResponse resp){
        try {
            EasyExcel.write(buildResponse(resp, fileName).getOutputStream(), dataClass)
                    .sheet("sheet0")
                    .doWrite(dataList);
        }catch (IOException e) {
            log.info("simpleWrite exception",e);
        }
    }

    public static void moreWrite(Map<Class,List> dataMap,String fileName, HttpServletResponse resp){
        ExcelWriter excelWriter =null;
        try {
//            File file=new File(fileName);
            FileOutputStream fot=new FileOutputStream(fileName);
            excelWriter = EasyExcel.write(fot).build();
            int i=0;
            for(Class c:dataMap.keySet()){
                i++;
                WriteSheet sheet = EasyExcel.writerSheet(i, "SHEET"+i).head(c).build();
                excelWriter.write(dataMap.get(c),sheet);
            }
        }catch (IOException e) {
            log.info("moreWrite exception",e);
        }finally {
            if(excelWriter!=null){
                excelWriter.finish();
            }
        }
    }

    /**
     * 通过流输出Excel
     * @param response
     * @param fileName
     */
    private static HttpServletResponse buildResponse(HttpServletResponse response, String fileName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO8859-1") + ".xlsx");
       return response;
    }
}
