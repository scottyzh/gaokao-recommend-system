package com.ruoyi.project.system.mainpage.service;

import java.util.List;
import com.ruoyi.project.system.mainpage.domain.Mainpage;

/**
 * 首页Service接口
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
public interface IMainpageService 
{
    /**
     * 查询首页
     * 
     * @param imageId 首页主键
     * @return 首页
     */
    public Mainpage selectMainpageByImageId(Integer imageId);

    /**
     * 查询首页列表
     * 
     * @param mainpage 首页
     * @return 首页集合
     */
    public List<Mainpage> selectMainpageList(Mainpage mainpage);

    /**
     * 新增首页
     * 
     * @param mainpage 首页
     * @return 结果
     */
    public int insertMainpage(Mainpage mainpage);

    /**
     * 修改首页
     * 
     * @param mainpage 首页
     * @return 结果
     */
    public int updateMainpage(Mainpage mainpage);

    /**
     * 批量删除首页
     * 
     * @param imageIds 需要删除的首页主键集合
     * @return 结果
     */
    public int deleteMainpageByImageIds(String imageIds);

    /**
     * 删除首页信息
     * 
     * @param imageId 首页主键
     * @return 结果
     */
    public int deleteMainpageByImageId(Integer imageId);
}
