package com.ruoyi.project.system.major.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.major.mapper.MajorMapper;
import com.ruoyi.project.system.major.domain.Major;
import com.ruoyi.project.system.major.service.IMajorService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 专业查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-27
 */
@Service
public class MajorServiceImpl implements IMajorService 
{
    @Autowired
    private MajorMapper majorMapper;

    /**
     * 查询专业查询
     * 
     * @param uniName 专业查询主键
     * @return 专业查询
     */
    @Override
    public Major selectMajorByUniName(String uniName)
    {
        return majorMapper.selectMajorByUniName(uniName);
    }

    /**
     * 查询专业查询列表
     * 
     * @param major 专业查询
     * @return 专业查询
     */
    @Override
    public List<Major> selectMajorList(Major major)
    {
        return majorMapper.selectMajorList(major);
    }

    /**
     * 新增专业查询
     * 
     * @param major 专业查询
     * @return 结果
     */
    @Override
    public int insertMajor(Major major)
    {
        return majorMapper.insertMajor(major);
    }

    /**
     * 修改专业查询
     * 
     * @param major 专业查询
     * @return 结果
     */
    @Override
    public int updateMajor(Major major)
    {
        return majorMapper.updateMajor(major);
    }

    /**
     * 批量删除专业查询
     * 
     * @param uniNames 需要删除的专业查询主键
     * @return 结果
     */
    @Override
    public int deleteMajorByUniNames(String uniNames)
    {
        return majorMapper.deleteMajorByUniNames(Convert.toStrArray(uniNames));
    }

    /**
     * 删除专业查询信息
     * 
     * @param uniName 专业查询主键
     * @return 结果
     */
    @Override
    public int deleteMajorByUniName(String uniName)
    {
        return majorMapper.deleteMajorByUniName(uniName);
    }
}
