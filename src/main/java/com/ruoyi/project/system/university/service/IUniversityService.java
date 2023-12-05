package com.ruoyi.project.system.university.service;

import com.ruoyi.project.system.university.domain.University;

import java.util.List;

/**
 * 院校查询Service接口
 * 
 * @author ruoyi
 * @date 2022-03-27
 */
public interface IUniversityService 
{
    /**
     * 查询院校查询
     * 
     * @param uniName 院校查询主键
     * @return 院校查询
     */
    public University selectUniversityByUniName(String uniName);

    /**
     * 查询院校查询列表
     * 
     * @param university 院校查询
     * @return 院校查询集合
     */
    public List<University> selectUniversityList(University university);

    /**
     * 新增院校查询
     * 
     * @param university 院校查询
     * @return 结果
     */
    public int insertUniversity(University university);

    /**
     * 修改院校查询
     * 
     * @param university 院校查询
     * @return 结果
     */
    public int updateUniversity(University university);

    /**
     * 批量删除院校查询
     * 
     * @param uniNames 需要删除的院校查询主键集合
     * @return 结果
     */
    public int deleteUniversityByUniNames(String uniNames);

    /**
     * 删除院校查询信息
     * 
     * @param uniName 院校查询主键
     * @return 结果
     */
    public int deleteUniversityByUniName(String uniName);
}
