package service;

import dao.impl.DormBuildDaoImpl;
import dao.impl.DormManagerDaoImpl;
import pojo.DormManager;

import java.util.List;

/**
 * @author haishao
 * @create 2020-05-22 16:11
 * @discript :
 */
public class DormManagerServiceImpl {
    private DormManagerDaoImpl dormManagerDao = new DormManagerDaoImpl();
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    //查找数据(姓名)
    public List<DormManager> findname(String name){
        return dormManagerDao.findname(name);
    }

    //删除数据
    public void delete(String dormManagerId){
        dormManagerDao.delete(dormManagerId);
    }

    //数据更新
    public void update(String dormManagerId,String userName,String password,Double dormBuildId,String name,String sex,String tel){
        dormManagerDao.update(dormManagerId,userName,password,dormBuildId,name,sex,tel);
    }

    //通过dormManagerId查询
    public DormManager findmanager(String dormManagerId){
        return dormManagerDao.findmanager(dormManagerId);
    }

    public void save(String dormManagerId,String userName,String password,Double dormBuildId,String name,String sex,String tel){
        String dormBuildName = dormBuildDao.dormBuildShow(dormBuildId).getDormBuildName();

        //1.调用save()
        dormManagerDao.save(dormManagerId,userName,password,dormBuildId,dormBuildName,name,sex,tel);
    }

    public List dormManagerList(){
        return dormManagerDao.dormManagerList();
    }

}


















