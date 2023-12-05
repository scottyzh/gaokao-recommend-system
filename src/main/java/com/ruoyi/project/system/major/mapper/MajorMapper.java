package com.ruoyi.project.system.major.mapper;

import java.util.List;
import com.ruoyi.project.system.major.domain.Major;

/**
 * 专业查询Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-27
 */
public interface MajorMapper 
{
    /**
     * 查询专业查询
     * 
     * @param uniName 专业查询主键
     * @return 专业查询
     */
    public Major selectMajorByUniName(String uniName);

    /**
     * 查询专业查询列表
     * 
     * @param major 专业查询
     * @return 专业查询集合
     */
    public List<Major> selectMajorList(Major major);

    /**
     * 新增专业查询
     * 
     * @param major 专业查询
     * @return 结果
     */
    public int insertMajor(Major major);

    /**
     * 修改专业查询
     * 
     * @param major 专业查询
     * @return 结果
     */
    public int updateMajor(Major major);

    /**
     * 删除专业查询
     * 
     * @param uniName 专业查询主键
     * @return 结果
     */
    public int deleteMajorByUniName(String uniName);

    /**
     * 批量删除专业查询
     * 
     * @param uniNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteMajorByUniNames(String[] uniNames);
}
