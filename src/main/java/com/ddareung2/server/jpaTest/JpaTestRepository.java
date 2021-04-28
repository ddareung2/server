package com.ddareung2.server.jpaTest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ddareung2.server.user.Admin;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaTestRepository extends JpaRepository<Admin, Long>{
	
	// 비어있어도 잘 작동함.
	// long이 아니고 Long 으로 작성. ex) int => Integer 같이 primitive 형식 사용못함
	
	// findBy 뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다
	public Optional<Admin> findById(Long id);

	public List<Admin> findByName(String name);
	
	public List<Admin> findByNameLike(String keyword);
	

}
