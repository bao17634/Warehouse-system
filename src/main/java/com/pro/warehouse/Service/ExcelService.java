package com.pro.warehouse.Service;

import com.pro.warehouse.myexception.StoreException;
import com.pro.warehouse.util.PoiUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class ExcelService<T> {

    private T t;

    public void ExportEcelService(List<T> excellist, String title, String sheetName, HttpServletResponse response, String filename, Class calzz) throws IllegalAccessException, InstantiationException {
        try {
            PoiUtils.exportExcel(excellist, title, sheetName, calzz, filename, response);
        } catch (StoreException e) {
            e.printStackTrace();
        }

    }
    // PoiUtils.exportExcel(excellist,"花名册","草帽一伙",Person.class,"海贼王.xls",response);

    public List<T> ImportExcelService(MultipartFile file, T t) throws IllegalAccessException, InstantiationException {
        List<T> personList = null;

        try {
            personList = PoiUtils.importExcel(file, 1, 1, t.getClass());
        } catch (StoreException e) {
            e.printStackTrace();
        }

        return personList;
    }

    /**
     * +     * 生成文件到指定文件夹中
     * +     * @param excellist
     * +     * @param title
     * +     * @param sheetName
     * +     * @param filename
     * +     * @param calzz
     * +     * @throws IllegalAccessException
     * +     * @throws InstantiationException
     * +
     */
    public void ExportEcel(List<T> excellist, String title, String sheetName, String filename, Class calzz) throws IllegalAccessException, InstantiationException {
        try {
            PoiUtils.exportExcel(excellist, title, sheetName, calzz, filename);
        } catch (StoreException e) {
            e.printStackTrace();
        }
    }
}


