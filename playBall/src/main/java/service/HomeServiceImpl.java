package service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import mapper.HomeMapper;
import mapper.Team;
import vo.MatchVo;
import vo.StadiumVo;
import vo.TeamMemberVo;
import vo.TeamVo;
import page.Page;
import page.TeamPage;


@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	HomeMapper HomeMapper;

	@Override
	public List<TeamVo> findTeamRank() {
		System.out.println("홈 서비스 들어옴");
		List<TeamVo> teamRank = HomeMapper.findTeamRank();
	    return teamRank;
	}
	
	@Override
	public List<StadiumVo> recentStadium(){
		List<StadiumVo> stadiumList = HomeMapper.recentStadium();
		return stadiumList;
	}
	
	@Override
	public List<MatchVo> matchingView(){
		List<MatchVo> matchList = HomeMapper.matchingView();
		return matchList;
	}
}
