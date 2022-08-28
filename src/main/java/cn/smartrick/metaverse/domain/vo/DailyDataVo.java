package cn.smartrick.metaverse.domain.vo;

import lombok.Data;

/**
 * 日数据
 */
@Data
public class DailyDataVo {
    //所有接口请求数量
    private Integer click;
    //浏览量
    private Integer browse;
    //点赞量
    private Integer like;
    //评论量
    private Integer comment;
    //新增用户数量
    private Integer newUser;
    //新增平台数量
    private Integer newPlatform;
}
