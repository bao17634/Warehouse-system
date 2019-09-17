package com.pro.warehouse.util;

/**
 * 对JdbcTRTemple进行分页处理的工具类
 */
public class PageUtil {

    public static int getTotalPage(int totalsize,int pagesize)
    {
        int total;
        if(totalsize%pagesize==0)
        {
            total=totalsize/pagesize;
        }else {
            total=(totalsize/pagesize)+1;
        }
        return total;
    }
}
