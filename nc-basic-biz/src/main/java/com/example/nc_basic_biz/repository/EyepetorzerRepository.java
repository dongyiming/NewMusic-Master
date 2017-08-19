package com.example.nc_basic_biz.repository;

import android.util.Log;

import com.example.uc_common_bean.greedao.HotItemInfoDao;
import com.example.uc_common_bean.vo.HotItemInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/8/3 3:05
 */
public class EyepetorzerRepository extends BaseRepository<HotItemInfo> implements IEyepetorzerRepository {

    private Logger logger = LoggerFactory.getLogger(MenuRepository.class);
    private HotItemInfoDao hotItemInfoDao;

    public EyepetorzerRepository() {
        super();
        hotItemInfoDao = daoSession.getHotItemInfoDao();
    }

    /**
     * 插入多条数据
     *
     * @param hotItemInfos
     * @return
     */
    @Override
    public boolean insertList(final List<HotItemInfo> hotItemInfos) {

        boolean flag;
        if (hotItemInfos == null || hotItemInfos.isEmpty()) {
            return false;
        }
        try {
            hotItemInfoDao.insertInTx(hotItemInfos);
            flag = true;
        } catch (Exception e) {
            flag = false;
            logger.error("db error : hotItemInfos insertList failed .{}", e.toString());
        }
        return flag;
    }

    /**
     * update
     * @param hotItemInfo
     */
    @Override
    public void update(HotItemInfo hotItemInfo) {

        hotItemInfoDao.update(hotItemInfo);
    }

    @Override
    public void insert(HotItemInfo hotItemInfo) {

        hotItemInfoDao.insert(hotItemInfo);
    }

    @Override
    public void addOrUpdate(HotItemInfo hotItemInfo) {

        HotItemInfo hotItem = selectByDateId(hotItemInfo.getDateId());
        if (hotItem == null) {
            insert(hotItemInfo);
        }/*else {
            update(hotItemInfo);
        }*/
    }
    /**
     * 通过唯一标识dateId判断
     * @param dataId
     * @return
     */
    @Override
    public HotItemInfo selectByDateId(double dataId) {

        List<HotItemInfo> hotItemInfos = hotItemInfoDao.queryBuilder().where(HotItemInfoDao.Properties.DateId.eq(dataId)).list();
        if (hotItemInfos != null && hotItemInfos.size() != 0) {
            return hotItemInfos.get(0);
        }
        return null;
    }

    @Override
    public List<HotItemInfo> selectByType(String dataType) {

        List<HotItemInfo> hotItemInfos = hotItemInfoDao.queryBuilder().where(HotItemInfoDao.Properties.DataType.eq(dataType)).list();
        if (hotItemInfos != null && hotItemInfos.size() != 0) {
            return hotItemInfos;
        }
        return null;
    }

    @Override
    public void deleteAll() {

        hotItemInfoDao.deleteAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public HotItemInfo selectById(int id) {

        List<HotItemInfo> hotItemInfos = hotItemInfoDao.queryBuilder().where(HotItemInfoDao.Properties.Id.eq(id)).list();
        if (hotItemInfos != null && hotItemInfos.size() != 0) {
            return hotItemInfos.get(0);
        }
        return null;
    }
}
