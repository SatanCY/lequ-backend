package com.sanwei.lequ.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author：SatanCY
 * @Date：2024/9/17 0:06
 */
@Slf4j
public class ImportExcel {
    public static void main(String[] args) {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = "C:\\Users\\SatanCY\\Desktop\\testExcel.xlsx";
        readByListener(fileName);
    }

    public static void readByListener(String fileName) {
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 这里可以使用默认的PageReadListener，也可以使用自己创建的TableListener
        EasyExcel.read(fileName, XingQiuTableUserInfo.class, new PageReadListener<XingQiuTableUserInfo>(dataList -> {
            for (XingQiuTableUserInfo data : dataList) {
                log.info("解析到一条数据:{}", new Gson().toJson(data));
            }
        })).sheet().doRead();
    }

}
