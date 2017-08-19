package com.example.nc_basic_biz.core;

import com.example.nc_basic_biz.http.EyepetozerHttpInvoker;
import com.example.nc_basic_biz.http.IEyepetozerHttpInvoker;
import com.example.nc_basic_biz.repository.EyepetorzerRepository;
import com.example.nc_basic_biz.repository.IEyepetorzerRepository;
import com.example.nc_basic_biz.utils.MapUtils;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.eyepetizer.hot.HotPageBean;
import com.example.uc_common_bean.vo.HotItemInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;

/**
 * @version : 1.0
 * @Description : 开眼项目首页
 * @autho : dongyiming
 * @data : 2017/7/31 23:14
 */
public class EyepetizerHomePageManager {

    private Logger logger = LoggerFactory.getLogger(MenuInfoManager.class);
    private final IEyepetozerHttpInvoker eyepetozerHttpInvoker;
    private final IEyepetorzerRepository eyepetorzerRepository;

    public EyepetizerHomePageManager() {

        eyepetozerHttpInvoker = new EyepetozerHttpInvoker();
        eyepetorzerRepository = new EyepetorzerRepository();
    }

    public void getHomePageData(final ICommonInvokeResult<HotPageBean, String> commonInvokeResult) {


        ResourceObserver<HotPageBean> resourceObserver = new ResourceObserver<HotPageBean>() {
            @Override
            public void onNext(@NonNull HotPageBean homePageBean) {

                logger.info("success to get all data for homepage . {}", homePageBean);
                commonInvokeResult.onResult(homePageBean);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                logger.error("failed to load homepage infos whth .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {
                commonInvokeResult.onCompleted();
            }
        };

        eyepetozerHttpInvoker.selectTabs(resourceObserver);
    }

    /**
     * 获取分页数据,并且存入本地数据库，播放界面需要获取
     *
     * @param startIndx
     * @param count
     * @param commonInvokeResult
     */
    public void deiscoveryHot(final int startIndx, int count, final ICommonInvokeResult<HashMap<String, List<HotItemInfo>>, String> commonInvokeResult) {
        ResourceObserver<HashMap<String, List<HotItemInfo>>> resourceObserver = new ResourceObserver<HashMap<String, List<HotItemInfo>>>() {
            @Override
            public void onNext(@NonNull HashMap<String, List<HotItemInfo>> hotItemInfos) {

                logger.info("success to get all data for homepage . {}", hotItemInfos);
                List<HotItemInfo> videoBeanForClients = MapUtils.getInfos(hotItemInfos, "VideoBeanForClient");
                List<HotItemInfo> horizontalScrollCards = MapUtils.getInfos(hotItemInfos, "HorizontalScrollCard");
                if (horizontalScrollCards != null) {
                    for (HotItemInfo horizontalScrollCard : horizontalScrollCards) {
                        eyepetorzerRepository.addOrUpdate(horizontalScrollCard);
                    }
                }
                if (videoBeanForClients != null) {
                    for (HotItemInfo videoBeanForClient : videoBeanForClients) {
                        eyepetorzerRepository.addOrUpdate(videoBeanForClient);
                    }
                }
                commonInvokeResult.onResult(hotItemInfos);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                logger.error("failed to load hotItemInfo whth .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {
                commonInvokeResult.onCompleted();
            }
        };

        eyepetozerHttpInvoker.discoveryHot(startIndx, count, resourceObserver);
    }

    /**
     * 获取热门数据并存入数据库
     *
     * @param commonInvokeResult
     */
    public void discoveryHot(final ICommonInvokeResult<HashMap<String, List<HotItemInfo>>, String> commonInvokeResult) {


        ResourceObserver<HashMap<String, List<HotItemInfo>>> resourceObserver = new ResourceObserver<HashMap<String, List<HotItemInfo>>>() {
            @Override
            public void onNext(@NonNull HashMap<String, List<HotItemInfo>> hotItemInfos) {
//                eyepetorzerRepository.deleteAll();
//                eyepetorzerRepository.insertList(hotItemInfos);
                logger.info("success to get all data for homepage . {}", hotItemInfos);
                commonInvokeResult.onResult(hotItemInfos);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                logger.error("failed to load hotItemInfo whth .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {
                commonInvokeResult.onCompleted();
            }
        };

        eyepetozerHttpInvoker.discoveryHot(resourceObserver);
    }

    /*=====================公共方法================================================================================================*/

    /**
     * 获取类型数据
     *
     * @param dataType
     * @return
     */
    public List<HotItemInfo> selectByType(String dataType) {

        return eyepetorzerRepository.selectByType(dataType);
    }

    public void deleteAll() {

        eyepetorzerRepository.deleteAll();
    }

    /**
     * @param id
     * @return
     */
    public HotItemInfo selectById(int id) {

        return eyepetorzerRepository.selectById(id);
    }

    /**
     * 通过dateId查询
     *
     * @param dateId
     * @return
     */
    public HotItemInfo selectByDateId(double dateId) {
        return eyepetorzerRepository.selectByDateId(dateId);
    }
}
