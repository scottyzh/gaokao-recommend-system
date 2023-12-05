package com.ruoyi.project.system.university.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.university.domain.University;
import com.ruoyi.project.system.university.mapper.UniversityMapper;
import com.ruoyi.project.system.university.service.IUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 院校查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-03-27
 */
@Service
public class UniversityServiceImpl implements IUniversityService 
{
    @Autowired
    private UniversityMapper universityMapper;

    /**
     * 查询院校查询
     * 
     * @param uniName 院校查询主键
     * @return 院校查询
     */
    @Override
    public University selectUniversityByUniName(String uniName)
    {
        return universityMapper.selectUniversityByUniName(uniName);
    }

    /**
     * 查询院校查询列表
     * 
     * @param university 院校查询
     * @return 院校查询
     */
    @Override
    public List<University> selectUniversityList(University university)
    {
        List<University> universities = universityMapper.selectUniversityList(university);
        return universities;

    }

    /**
     * 新增院校查询
     * 
     * @param university 院校查询
     * @return 结果
     */
    @Override
    public int insertUniversity(University university)
    {
        return universityMapper.insertUniversity(university);
    }

    /**
     * 修改院校查询
     * 
     * @param university 院校查询
     * @return 结果
     */
    @Override
    public int updateUniversity(University university)
    {
        return universityMapper.updateUniversity(university);
    }

    /**
     * 批量删除院校查询
     * 
     * @param uniNames 需要删除的院校查询主键
     * @return 结果
     */
    @Override
    public int deleteUniversityByUniNames(String uniNames)
    {
        return universityMapper.deleteUniversityByUniNames(Convert.toStrArray(uniNames));
    }

    /**
     * 删除院校查询信息
     * 
     * @param uniName 院校查询主键
     * @return 结果
     */
    @Override
    public int deleteUniversityByUniName(String uniName)
    {
        return universityMapper.deleteUniversityByUniName(uniName);
    }
}
