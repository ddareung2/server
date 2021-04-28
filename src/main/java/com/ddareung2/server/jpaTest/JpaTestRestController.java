package com.ddareung2.server.jpaTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ddareung2.server.user.Admin;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("JpaTest")
public class JpaTestRestController {
	
	@Autowired
	JpaTestService jpaTestService;
	
	// 모든 회원 조회
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Admin>> getAllMembers() {
		List<Admin> adminItem = jpaTestService.findAll();
		return new ResponseEntity<List<Admin>>(adminItem, HttpStatus.OK);
	}
	
	// 회원번호로 한명의 회원 조회
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Admin> getMember(@PathVariable("id") Long id) {
		Optional<Admin> adminItem = jpaTestService.findById(id);
		return new ResponseEntity<Admin>(adminItem.get(), HttpStatus.OK);
	}
	
	
	// 회원번호로 회원 삭제
	@DeleteMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteMember(@PathVariable("id") Long id) {
		jpaTestService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	// 회원번호로 회원 수정(id로 회원을 찾아 Member 객체의 id, name 수정)
	@PutMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Admin> updateMember(@PathVariable("id") Long id, Admin adminItem) {
		jpaTestService.updateById(id, adminItem);
		return new ResponseEntity<Admin>(adminItem, HttpStatus.OK);
	}
	
	// 회원 입력
	@PostMapping
	public ResponseEntity<Admin> save(Admin adminItem) {
		return new ResponseEntity<Admin>(jpaTestService.save(adminItem), HttpStatus.OK);
	}
	
	// 회원 입력
	@RequestMapping(value="/saveMember", method = RequestMethod.GET)
	public ResponseEntity<Admin> save(HttpServletRequest req, Admin adminItem) {
		return new ResponseEntity<Admin>(jpaTestService.save(adminItem), HttpStatus.OK);
	}

}
