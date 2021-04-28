package com.ddareung2.server.jpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddareung2.server.admin.Admin;

@Service
public class JpaTestService {

	@Autowired
	private JpaTestRepository jpaTestRepository;

	public List<Admin> findAll() {
		List<Admin> jpaTest = new ArrayList<>();
		jpaTestRepository.findAll().forEach(e -> jpaTest.add(e));

		return jpaTest;
	}

	public Optional<Admin> findById(Long id) {
		Optional<Admin> adminItem = jpaTestRepository.findById(id);
		return adminItem;
	}

	public void deleteById(Long id) {
		jpaTestRepository.deleteById(id);
	}

	public Admin save(Admin adminItem) {
		jpaTestRepository.save(adminItem);
		return adminItem;
	}

	public void updateById(Long id, Admin adminItem) {
		Optional<Admin> e = jpaTestRepository.findById(id);

		if (e.isPresent()) {
			e.get().setUsername(adminItem.getUsername());
			e.get().setName(adminItem.getName());

			e.get().setPassword(adminItem.getPassword());
			e.get().setRole(adminItem.getRole());
			e.get().setModifiedAt(adminItem.getModifiedAt());
			e.get().setStatus(adminItem.getStatus());
			jpaTestRepository.save(adminItem);
		}
	}

}
