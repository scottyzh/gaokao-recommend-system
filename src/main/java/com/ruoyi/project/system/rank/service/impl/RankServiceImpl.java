package com.ruoyi.project.system.rank.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.rank.domain.Rank;
import com.ruoyi.project.system.rank.mapper.RankMapper;
import com.ruoyi.project.system.rank.service.IRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 排位查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-04-01
 */
@Service
public class RankServiceImpl implements IRankService 
{
    @Autowired
    private RankMapper rankMapper;

    /**
     * 查询排位查询
     * 
     * @param physisScore 排位查询主键
     * @return 排位查询
     */
    @Override
    public Rank selectRankByPhysisScore(Long physisScore)
    {
        return rankMapper.selectRankByPhysisScore(physisScore);
    }

    /**
     * 查询排位查询列表
     * 
     * @param rank 排位查询
     * @return 排位查询
     */
    @Override
    public List<Rank> selectRankList(Rank rank)
    {
        return rankMapper.selectRankList(rank);
    }

    /**
     * 新增排位查询
     * 
     * @param rank 排位查询
     * @return 结果
     */
    @Override
    public int insertRank(Rank rank)
    {
        return rankMapper.insertRank(rank);
    }

    /**
     * 修改排位查询
     * 
     * @param rank 排位查询
     * @return 结果
     */
    @Override
    public int updateRank(Rank rank)
    {
        return rankMapper.updateRank(rank);
    }

    /**
     * 批量删除排位查询
     * 
     * @param physisScores 需要删除的排位查询主键
     * @return 结果
     */
    @Override
    public int deleteRankByPhysisScores(String physisScores)
    {
        return rankMapper.deleteRankByPhysisScores(Convert.toStrArray(physisScores));
    }

    /**
     * 删除排位查询信息
     * 
     * @param physisScore 排位查询主键
     * @return 结果
     */
    @Override
    public int deleteRankByPhysisScore(Long physisScore)
    {
        return rankMapper.deleteRankByPhysisScore(physisScore);
    }

    @Override
    public Rank selectPhysisRankByPhysisScore(Long physisScore) {
        return rankMapper.selectPhysisRankByPhysisScore(physisScore);
    }

    @Override
    public Rank selectHistoryRankByHistoryScore(Long historyScore) {
        return rankMapper.selectHistoryRankByHistoryScore(historyScore);
    }




}
