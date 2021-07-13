package mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointMapper {
 public int findPointCount(String mid);
 public int updatePoint(String mid);
 public int resetPointCount();
 public int payPoints(String mid);
}
