package com.example.nc_basic_biz.core;

import android.util.Log;

import com.example.nc_basic_biz.http.IMenuInfoHttpInvoker;
import com.example.nc_basic_biz.http.MenuInfoHttpInvoker;
import com.example.nc_basic_biz.repository.IMenuRepository;
import com.example.nc_basic_biz.repository.MenuRepository;
import com.example.nc_super_abs.interaction.ICommonInvokeResult;
import com.example.uc_common_bean.vo.MenuInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.ResourceObserver;


/**
 * @version : 1.0
 * @Description :
 * @autho : dongyiming
 * @data : 2017/7/14 14:53
 */
public class MenuInfoManager {

    private Logger logger = LoggerFactory.getLogger(MenuInfoManager.class);
    private final IMenuInfoHttpInvoker menuInfoHttpInvoker;
    private final IMenuRepository menuRepository;

    public MenuInfoManager() {

        menuInfoHttpInvoker = new MenuInfoHttpInvoker();
        menuRepository = new MenuRepository();
    }

    /**
     * 获取数据库所有歌单
     *
     * @param commonInvokeResult
     */
    public void getAllMenuInfo(final ICommonInvokeResult<List<MenuInfo>, String> commonInvokeResult) {

        menuInfoHttpInvoker.getAll(new ResourceObserver<List<MenuInfo>>() {

            @Override
            public void onError(Throwable e) {

                logger.error("httpRequest error : menuinfo getAllMenuInfo is failed .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {
                commonInvokeResult.onCompleted();
            }

            @Override
            public void onNext(List<MenuInfo> menuInfos) {
                commonInvokeResult.OnResult(menuInfos);
            }
        });

    }

    /**
     * 获取首页推荐歌单
     *
     * @param commonInvokeResult
     */
    public void getRecMenuInfos(final ICommonInvokeResult<List<MenuInfo>, String> commonInvokeResult) {

        menuInfoHttpInvoker.getRecInfos(new ResourceObserver<List<MenuInfo>>() {
            @Override
            public void onComplete() {
                commonInvokeResult.onCompleted();
            }

            @Override
            public void onError(Throwable e) {

                logger.error("httpRequest error : menuinfo getRecMenuInfos is failed .{}", e.toString());
                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onNext(List<MenuInfo> menuInfos) {

                //复杂的数据请求可以放在asynctask里操作
                menuRepository.insertList(menuInfos);
                commonInvokeResult.OnResult(menuInfos);
            }
        });

    }

    /**
     * 从数据库读取数据
     *
     * @param type
     * @return
     */
    public List<MenuInfo> selectByType(int type) {
        return menuRepository.selectByType(type);
    }

    /**
     * 本地获取分页数据
     *
     * @param type
     * @param startIndex
     * @param pageCount
     * @param commonInvokeResult
     * @return
     */
    public List<MenuInfo> selectPageMenuInfo(int type, int startIndex, int pageCount, final ICommonInvokeResult<List<MenuInfo>, String> commonInvokeResult) {

        try {
            List<MenuInfo> menuInfos = menuRepository.selectByType(type, startIndex, pageCount);
            commonInvokeResult.OnResult(menuInfos);
        } catch (Exception e) {
            commonInvokeResult.onFailure(e.toString());
        }
        return null;
    }

    /**
     * 通过歌单类型得到所有数据
     *
     * @param type
     * @param startIndex
     * @param pageCount
     * @param commonInvokeResult
     */
    public void selectByType(int type, int startIndex, int pageCount, final ICommonInvokeResult<List<MenuInfo>, String> commonInvokeResult) {

        ResourceObserver<List<MenuInfo>> resourceObserver = new ResourceObserver<List<MenuInfo>>() {
            @Override
            public void onNext(@NonNull List<MenuInfo> menuInfos) {

                for (MenuInfo menuInfo : menuInfos) {

                    Log.e("dongyiming", "onNext: " + menuInfo.getMenuName());
                }
                menuRepository.insertList(menuInfos);
                commonInvokeResult.OnResult(menuInfos);
            }

            @Override
            public void onError(@NonNull Throwable e) {

                commonInvokeResult.onFailure(e.toString());
            }

            @Override
            public void onComplete() {

                commonInvokeResult.onCompleted();
            }
        };
        menuInfoHttpInvoker.getMenuByType(type, startIndex, pageCount, resourceObserver);
    }

}
