package com.ruoyi.project.system.mainpage.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mainpage.mapper.MainpageMapper;
import com.ruoyi.project.system.mainpage.domain.Mainpage;
import com.ruoyi.project.system.mainpage.service.IMainpageService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 首页Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-19
 */
@Service
public class MainpageServiceImpl implements IMainpageService 
{
    @Autowired
    private MainpageMapper mainpageMapper;

    /**
     * 查询首页
     * 
     * @param imageId 首页主键
     * @return 首页
     */
    @Override
    public Mainpage selectMainpageByImageId(Integer imageId)
    {
        return mainpageMapper.selectMainpageByImageId(imageId);
    }

    /**
     * 查询首页列表
     * 
     * @param mainpage 首页
     * @return 首页
     */
    @Override
    public List<Mainpage> selectMainpageList(Mainpage mainpage)
    {
        return mainpageMapper.selectMainpageList(mainpage);
    }

    /**
     * 新增首页
     * 
     * @param mainpage 首页
     * @return 结果
     */
    @Override
    public int insertMainpage(Mainpage mainpage)
    {
        return mainpageMapper.insertMainpage(mainpage);
    }

    /**
     * 修改首页
     * 
     * @param mainpage 首页
     * @return 结果
     */
    @Override
    public int updateMainpage(Mainpage mainpage)
    {
        return mainpageMapper.updateMainpage(mainpage);
    }

    /**
     * 批量删除首页
     * 
     * @param imageIds 需要删除的首页主键
     * @return 结果
     */
    @Override
    public int deleteMainpageByImageIds(String imageIds)
    {
        return mainpageMapper.deleteMainpageByImageIds(Convert.toStrArray(imageIds));
    }

    /**
     * 删除首页信息
     * 
     * @param imageId 首页主键
     * @return 结果
     */
    @Override
    public int deleteMainpageByImageId(Integer imageId)
    {
        return mainpageMapper.deleteMainpageByImageId(imageId);
    }
}
