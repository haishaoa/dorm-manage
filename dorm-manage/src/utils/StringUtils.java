package utils;

/**
 * @author haishao
 * @create 2020-05-22 0:23
 * @discript :
 */
public class StringUtils {
    public static boolean isEmpty(String str){
        if("".equals(str)|| str==null){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isNotEmpty(String str){
        if(!"".equals(str)&&str!=null){
            return true;
        }else{
            return false;
        }
    }
}
