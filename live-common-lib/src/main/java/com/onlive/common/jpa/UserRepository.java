package com.onlive.common.jpa;

import java.util.Optional;


import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlive.common.entity.SNSUserVo;
import com.onlive.common.vo.CustomUserDetails;
import com.onlive.common.vo.UserVo;

//@RepositoryDefinition(domainClass = SNSUserVo.class, idClass = String.class)
@Repository
public interface UserRepository extends JpaRepository<SNSUserVo, Long>{
    // 소셜 로그인으로 반환되는 id 값을 db 값과 비교한 후 SNSUserVo에 저장
    @Select(value="SELECT * FROM user_info WHERE user_id = #{userId}")
    Optional<SNSUserVo> findByUserId(@Param("userId")String userId);
    
    //테이블 명, 컬럼 명 대소문자 구분해 작성
    // 내가 동적 쿼리를 날리는 경우에는 nativeQuery = true를 넣어 설정
    //닉네임 체크
    @Select(value ="SELECT COUNT(*) FROM user_info WHERE user_nickname = #{userNickname}")
    int countByNickCheck(@Param("userNickname") String nick);
    
    //유저 정보 가져와 UserVo에 저장
    @Select(value="SELECT UI.*,SI.SEL_NAME FROM user_info UI LEFT JOIN seller_info SI ON user_id = sel_id WHERE user_id = #{UserId}")
    UserVo findbyUserInfo(@Param("userId") String userId);
    
    //유저 정보 가져와 CustomUserDetails에 저장
    @Select(value="SELECT * FROM user_info WHERE user_id = #{userId}")
    CustomUserDetails findbyUserLogin(@Param("userId") String userId);
    
    @Modifying 
    @Query(value="INSERT INTO USER_INFO (USER_ID,USER_PW,USER_UNAME,USER_NICKNAME,USER_PLATFORM,USER_ROLE)VALUES(#{userId},#{userPw},#{userUname},#{userNickname},#{userPlatform},#{userRole}",nativeQuery = true)
    int insertUserInfo(@Param("userId")String userId,@Param("userPw")String userPw,@Param("userUname")String userUname
            ,@Param("userNickname")String userNickname,@Param("userPlatform")String userPlatform,@Param("userRole")String userRole);

}
