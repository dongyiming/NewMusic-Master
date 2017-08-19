package com.example.nc_basic_biz.repository;

import android.util.Log;

import com.example.uc_common_bean.enums.MenuType;
import com.example.uc_common_bean.greedao.MenuInfoDao;
import com.example.uc_common_bean.vo.MenuInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @version : 1.0
 * @Description : 歌单数据操作
 * @autho : dongyiming
 * @data : 2017/7/10 21:13
 */
public class MenuRepository extends BaseRepository<MenuInfo> implements IMenuRepository {

    private Logger logger = LoggerFactory.getLogger(MenuRepository.class);
    private MenuInfoDao menuInfoDao;

    public MenuRepository() {
        super();
        menuInfoDao = daoSession.getMenuInfoDao();
    }

    /**
     * 添加
     *
     * @param menuInfo
     */
    @Override
    public void addOrUpdate(MenuInfo menuInfo) {

        MenuInfo menu = selectById(menuInfo.getMenuCode());
        if (menu != null) {
            updateItem(menuInfo);
        } else {
            insertItem(menuInfo);
        }
    }

    @Override
    public void insert(MenuInfo menuInfo) {
        insertItem(menuInfo);
    }

    /**
     * 插入多条数据
     *
     * @param menuInfos
     * @return
     */
    @Override
    public boolean insertList(final List<MenuInfo> menuInfos) {

        boolean flag;
        if (menuInfos == null || menuInfos.isEmpty()) {
            return false;
        }
        try {
            daoSession.getMenuInfoDao().insertInTx(menuInfos);
            flag = true;
        } catch (Exception e) {
            flag = false;
            logger.error("db error : menuinfo insertList failed .{}", e.toString());
        }
        return flag;
    }

    @Override
    public MenuInfo selectById(int menuInfoCode) {

        MenuInfo menuInfo = menuInfoDao.queryBuilder().where(MenuInfoDao.Properties.MenuCode.eq(menuInfoCode)).build().unique();
        return menuInfo;
    }

    @Override
    public List<MenuInfo> selectByType(int menuType, int startIndex, int pageCount) {

        List<MenuInfo> menuInfos = menuInfoDao.queryBuilder().where(MenuInfoDao.Properties.MenuType.eq(menuType)).offset(startIndex).limit(pageCount).list();
        for (MenuInfo menuinfo : menuInfos) {
            Log.e("dongyiming","code = " + menuinfo.getMenuName() + "  ___" + menuinfo.getMenuCode());
        }
        return menuInfos;
    }

    @Override
    public List<MenuInfo> selectByType(int menuType) {

        List<MenuInfo> menuInfos = menuInfoDao.queryBuilder().where(MenuInfoDao.Properties.MenuType.eq(menuType)).list();
        if (menuInfos != null && menuInfos.size() != 0) {
            if (menuType == MenuType.UNP_MUC.getValue()) {
                for (int i = 0; i < menuInfos.size(); i++) {
                    if (i == menuInfos.size() - 1) {
                        menuInfos.get(i).setMenuType(999);
                    }
                }
            }
            return menuInfos;
        }
        return null;
    }

}
