package com.ruoyi.project.system.university.mapper;

import com.ruoyi.project.system.university.domain.University;

import java.util.List;

/**
 * 院校查询Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-27
 */
public interface UniversityMapper 
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
     * 删除院校查询
     * 
     * @param uniName 院校查询主键
     * @return 结果
     */
    public int deleteUniversityByUniName(String uniName);

    /**
     * 批量删除院校查询
     * 
     * @param uniNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUniversityByUniNames(String[] uniNames);
/**

* Description:查询高20分的学校
*/
    List<University> selectUniversityHighRisk(University university);
}
