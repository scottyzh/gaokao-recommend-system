package com.ruoyi.project.system.score.mapper;

import com.ruoyi.project.system.score.domain.Score;

import java.util.List;

/**
 * 志愿推荐Mapper接口
 * 
 * @author ruoyi
 * @date 2022-03-28
 */
public interface ScoreMapper 
{
    /**
     * 查询志愿推荐
     * 
     * @param uniCode 志愿推荐主键
     * @return 志愿推荐
     */
    public Score selectScoreByUniCode(String uniCode);

    /**
     * 查询志愿推荐列表
     * 
     * @param score 志愿推荐
     * @return 志愿推荐集合
     */
    public List<Score> selectScoreList(Score score);

    /**
     * 新增志愿推荐
     * 
     * @param score 志愿推荐
     * @return 结果
     */
    public int insertScore(Score score);

    /**
     * 修改志愿推荐
     * 
     * @param score 志愿推荐
     * @return 结果
     */
    public int updateScore(Score score);

    /**
     * 删除志愿推荐
     * 
     * @param uniCode 志愿推荐主键
     * @return 结果
     */
    public int deleteScoreByUniCode(String uniCode);

    /**
     * 批量删除志愿推荐
     * 
     * @param uniCodes 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteScoreByUniCodes(String[] uniCodes);
}
