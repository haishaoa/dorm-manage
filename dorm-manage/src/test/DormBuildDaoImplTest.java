package test;

import dao.impl.DormBuildDaoImpl;
import org.junit.Test;
import pojo.DormBuild;

import static org.junit.Assert.*;

/**
 * @author haishao
 * @create 2020-05-21 23:08
 * @discript :
 */
public class DormBuildDaoImplTest {
    private DormBuildDaoImpl dormBuildDao = new DormBuildDaoImpl();

    @Test
    public void dormBuildadd(){
        DormBuild dormBuild = new DormBuild(1.0,"C6","女寝");
        dormBuildDao.dormBuildadd(dormBuild);
    }

    @Test
    public void dormBuildupdate(){
        DormBuild dormBuild = new DormBuild(1.0,"C6","女寝");
        dormBuildDao.dormBuildupdate(dormBuild);
    }

    @Test
    public void dormBuildShow(){
        System.out.println(dormBuildDao.dormBuildShow(1.0));
    }

    @Test
    public void dormBuildList() {
        System.out.println(dormBuildDao.dormBuildList());
    }
}