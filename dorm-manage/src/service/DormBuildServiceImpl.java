package service;

import dao.impl.DormBuildDaoImpl;
import pojo.DormBuild;

import java.util.List;

/**
 * @author haishao
 * @create 2020-05-21 23:16
 * @discript :
 */
public class DormBuildServiceImpl {
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    //删除指定编号宿舍楼
    public boolean dormBuilddelete(Double dormBuildId){
        return dormBuildDao.dormBuilddelete(dormBuildId);
    }

    //添加宿舍楼信息
    public boolean dormBuildadd(DormBuild dormBuild){
        return dormBuildDao.dormBuildadd(dormBuild);
    }

    //保存修改dormBuild的信息
    public boolean dormBuildupdate(DormBuild dormBuild){
        return dormBuildDao.dormBuildupdate(dormBuild);
    }

    //返回执行dormBuildId的寝室楼信息
    public DormBuild dormBuildShow(Double dormBuildId){
        return dormBuildDao.dormBuildShow(dormBuildId);
    }

    //返回指定姓名的宿舍楼信息
    public List<DormBuild> dormBuildsearch(String dormBuildName){
        return dormBuildDao.dormBuildsearch(dormBuildName);
    }

    //返回全部寝室楼信息
    public List<DormBuild> dormBuildList(){
        return dormBuildDao.dormBuildList();
    }
}















