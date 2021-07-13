package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import mapper.PointMapper;

public class PointServiceImpl implements PointService{
@Autowired
PointMapper pointMapper;

int r; 

@Transactional
public void loginPoint(String mid) {
	
	r = pointMapper.findPointCount(mid);
	
	if(r<1) {
		pointMapper.updatePoint(mid);
	}
	
}

@Transactional
public void earnPoints(String mid) {
	r = pointMapper.findPointCount(mid);
	if(r<3) {
		pointMapper.updatePoint(mid);
	}
}

@Transactional
public int payPoints(String mid) {
	r = pointMapper.payPoints(mid);
	return r;
}

@Scheduled(cron="0 0 0 * * *")
public void resetPintCount() {
	pointMapper.resetPointCount();
}
	
	
}
