package com.ruoyi.project.system.score.service.impl;

import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.system.score.domain.Score;
import com.ruoyi.project.system.score.mapper.ScoreMapper;
import com.ruoyi.project.system.score.service.IScoreService;
import com.ruoyi.project.system.university.domain.University;
import com.ruoyi.project.system.university.mapper.UniversityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 志愿推荐Service业务层处理
 *
 * @author ruoyi
 * @date 2022-03-28
 */
@Service
public class ScoreServiceImpl implements IScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private UniversityMapper universityMapper;

    /**
     * 查询志愿推荐
     *
     * @param uniCode 志愿推荐主键
     * @return 志愿推荐
     */
    @Override
    public Score selectScoreByUniCode(String uniCode) {
        return scoreMapper.selectScoreByUniCode(uniCode);
    }

    /**
     * 查询志愿推荐列表
     *
     * @param score 志愿推荐
     * @return 志愿推荐
     */
    @Override
    public List<Score> selectScoreList(Score score, String majorSubject) {
        University university = new University();
        if (Objects.equals(majorSubject, "物理")) {
            university.setPhysisLowScore(score.getLowestScore());
        } else if (Objects.equals(majorSubject, "历史")) {
            university.setHistoryLowScore(score.getLowestScore());
        } else {
            throw new RuntimeException("请输入正确的科目名字");
        }
        List<University> universities = universityMapper.selectUniversityHighRisk(university);
        List<Score> scores = new ArrayList<Score>();
        for (University university1 : universities) {
            Score score1 = new Score();
            if ("物理".equals(majorSubject)) {
                double index = (double) university1.getPhysisTotal() / (double) score.getLowestRank();
                index = (double) Math.round(index * 100) / 100;
                if (score.getLowestScore() >= 505) {
                    if (index >= 0.60 && index <= 1.00) {
                        score1.setRiskRate("冲");
                    } else if (index > 1.00 && index <= 1.5) {
                        score1.setRiskRate("稳");
                    } else if (index > 1.5) {
                        score1.setRiskRate("保");
                    }
                    score1.setIndex(index);
                    score1.setLowestScore(university1.getPhysisLowScore());
                    score1.setLowestRank(university1.getPhysisLowScoreRank());
                    score1.setMatriculateNumber(university1.getPhysisMatriculateNumber());
                    score1.setFirstRequest("物理");
                    score1.setUniName(university1.getUniName());
                    score1.setUniCode(university1.getUniCode());
                    score1.setUniProvince(university1.getUniProvince());
                    score1.setUniCity(university1.getUniCity());
                    score1.setLevel(university1.getLevel());
                    score1.setUniType(university1.getUniType());
                    score1.setUniDirection(university1.getUniDirection());
                    scores.add(score1);
                    scores.removeIf(item -> (item.getIndex() < 0.60));
                    scores.sort((item1, item2) -> Double.compare(item1.getIndex(), item2.getIndex()));
                } else {
                    if (university1.getPhysisLowScore() - score.getLowestScore() > 0) {
                        score1.setRiskRate("冲");
                    } else if (university1.getPhysisLowScore() - score.getLowestScore() <= 0 && university1.getPhysisLowScore() - score.getLowestScore() >= -15) {
                        score1.setRiskRate("稳");
                    } else {
                        score1.setRiskRate("保");
                    }
                    score1.setIndex(index);
                    score1.setLowestScore(university1.getPhysisLowScore());
                    score1.setLowestRank(university1.getPhysisLowScoreRank());
                    score1.setMatriculateNumber(university1.getPhysisMatriculateNumber());
                    score1.setFirstRequest("物理");
                    score1.setUniName(university1.getUniName());
                    score1.setUniCode(university1.getUniCode());
                    score1.setUniProvince(university1.getUniProvince());
                    score1.setUniCity(university1.getUniCity());
                    score1.setLevel(university1.getLevel());
                    score1.setUniType(university1.getUniType());
                    score1.setUniDirection(university1.getUniDirection());
                    scores.add(score1);
                }
            } else if ("历史".equals(majorSubject)) {
                double index = (double) university1.getHistoryTotal() / (double) score.getLowestRank();
                index = (double) Math.round(index * 100) / 100;
                if (score.getLowestScore() >= 505) {
                    if (index >= 0.60 && index <= 1.00) {
                        score1.setRiskRate("冲");
                    } else if (index > 1.00 && index <= 1.5) {
                        score1.setRiskRate("稳");
                    } else if (index > 1.5) {
                        score1.setRiskRate("保");
                    }
                    score1.setIndex(index);
                    score1.setLowestScore(university1.getHistoryLowScore());
                    score1.setLowestRank(university1.getHistoryLowScoreRank());
                    score1.setMatriculateNumber(university1.getHistoryMatriculateNumber());
                    score1.setFirstRequest("历史");
                    score1.setUniName(university1.getUniName());
                    score1.setUniCode(university1.getUniCode());
                    score1.setUniProvince(university1.getUniProvince());
                    score1.setUniCity(university1.getUniCity());
                    score1.setLevel(university1.getLevel());
                    score1.setUniType(university1.getUniType());
                    score1.setUniDirection(university1.getUniDirection());
                    scores.add(score1);
                    scores.removeIf(item -> (item.getIndex() < 0.60));
                    scores.sort((item1, item2) -> Double.compare(item1.getIndex(), item2.getIndex()));
                } else {
                    if (university1.getHistoryLowScore() - score.getLowestScore() > 0) {
                        score1.setRiskRate("冲");
                    } else if (university1.getHistoryLowScore() - score.getLowestScore() <= 0 && university1.getHistoryLowScore() - score.getLowestScore() >= -15) {
                        score1.setRiskRate("稳");
                    } else {
                        score1.setRiskRate("保");
                    }
                    score1.setIndex(index);
                    score1.setLowestScore(university1.getHistoryLowScore());
                    score1.setLowestRank(university1.getHistoryLowScoreRank());
                    score1.setMatriculateNumber(university1.getHistoryMatriculateNumber());
                    score1.setFirstRequest("历史");
                    score1.setUniName(university1.getUniName());
                    score1.setUniCode(university1.getUniCode());
                    score1.setUniProvince(university1.getUniProvince());
                    score1.setUniCity(university1.getUniCity());
                    score1.setLevel(university1.getLevel());
                    score1.setUniType(university1.getUniType());
                    score1.setUniDirection(university1.getUniDirection());
                    scores.add(score1);
                }
            }
        }
        return scores;
    }

    /**
     * 新增志愿推荐
     *
     * @param score 志愿推荐
     * @return 结果
     */
    @Override
    public int insertScore(Score score) {
        return scoreMapper.insertScore(score);
    }

    /**
     * 修改志愿推荐
     *
     * @param score 志愿推荐
     * @return 结果
     */
    @Override
    public int updateScore(Score score) {
        return scoreMapper.updateScore(score);
    }

    /**
     * 批量删除志愿推荐
     *
     * @param uniCodes 需要删除的志愿推荐主键
     * @return 结果
     */
    @Override
    public int deleteScoreByUniCodes(String uniCodes) {
        return scoreMapper.deleteScoreByUniCodes(Convert.toStrArray(uniCodes));
    }

    /**
     * 删除志愿推荐信息
     *
     * @param uniCode 志愿推荐主键
     * @return 结果
     */
    @Override
    public int deleteScoreByUniCode(String uniCode) {
        return scoreMapper.deleteScoreByUniCode(uniCode);
    }
}
