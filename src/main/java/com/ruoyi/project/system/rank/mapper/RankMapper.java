package com.ruoyi.project.system.rank.mapper;

import com.ruoyi.project.system.rank.domain.Rank;

import java.util.List;

/**
 * 排位查询Mapper接口
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
public interface RankMapper 
{
    /**
     * 查询排位查询
     * 
     * @param physisScore 排位查询主键
     * @return 排位查询
     */
    public Rank selectRankByPhysisScore(Long physisScore);

    /**
     * 查询排位查询列表
     * 
     * @param rank 排位查询
     * @return 排位查询集合
     */
    public List<Rank> selectRankList(Rank rank);

    /**
     * 新增排位查询
     * 
     * @param rank 排位查询
     * @return 结果
     */
    public int insertRank(Rank rank);

    /**
     * 修改排位查询
     * 
     * @param rank 排位查询
     * @return 结果
     */
    public int updateRank(Rank rank);

    /**
     * 删除排位查询
     * 
     * @param physisScore 排位查询主键
     * @return 结果
     */
    public int deleteRankByPhysisScore(Long physisScore);

    /**
     * 批量删除排位查询
     * 
     * @param physisScores 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRankByPhysisScores(String[] physisScores);

    public Rank selectPhysisRankByPhysisScore(Long physisScore );
    public Rank selectHistoryRankByHistoryScore(Long historyScore);
}
