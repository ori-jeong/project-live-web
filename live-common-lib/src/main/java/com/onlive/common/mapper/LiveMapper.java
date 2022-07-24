package com.onlive.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.onlive.common.vo.LiveVo;

@Mapper
public interface LiveMapper {
    public List<LiveVo> liveListHome(String nowDateTime);
    public List<LiveVo> trailerListHome(String nowDateTime);
    public List<LiveVo> toptenListHome(String nowDateTime);
    
    public LiveVo getLiveInfo(LiveVo liveVo);
    public LiveVo getReplayInfo(LiveVo replayVo);
    
    public List<LiveVo> hometownLiveList(String location);
    public List<LiveVo> categoryLiveList(String category);
    public List<LiveVo> searchLiveList(String query);
    public String nowDateTime();
    public int updateLiveStatus(String nowDateTime);
    public List<LiveVo.LiveVideoVo> getLiveStreamInfo();
    
    public void updateLiveVideoId(List<LiveVo.LiveVideoVo> live);
    public void setLiveVideoInfo(List<LiveVo.LiveVideoVo> live);
    public void setLiveView(LiveVo live);
    public int getLiveView(LiveVo liveChatvo);
}
