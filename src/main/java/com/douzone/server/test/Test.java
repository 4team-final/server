package com.douzone.server.test;

import com.douzone.server.config.security.handler.DecodeEncodeHandler;
import com.douzone.server.config.utils.ResponseDTO;
import com.douzone.server.entity.Department;
import com.douzone.server.entity.Employee;
import com.douzone.server.entity.Position;
import com.douzone.server.entity.Team;
import com.douzone.server.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class Test {

	private final EmployeeRepository employeeRepository;
	private final DecodeEncodeHandler decodeEncodeHandler;

	@PostMapping("/sign")
	public void sign(@RequestBody Model model) {
		log.info("UserController join method ...");

		Employee employee = join(model);
		System.out.println(employee);
	}

	public Employee join(Model model) {
		log.info("UserService join() ...");
		return employeeRepository.save(model.toEntity(decodeEncodeHandler.passwordEncode(model.getPassword())));
	}

	@PostMapping("/emp/check")
	public ResponseDTO check(@RequestBody Model model) {
		return new ResponseDTO().of(HttpStatus.OK, "hi", model);
	}
}

@Getter
@AllArgsConstructor
class Model {
	private String empNo, password, name, email, tel;

	public Employee toEntity(String pw) {
		return Employee.builder()
				.empNo(empNo)
				.password(pw)
				.name(name)
				.email(email)
				.tel(tel)
				.team(Team.builder().id(1L).department(Department.builder().id(1L).build()).build())
				.position(Position.builder().id(2L).build())
				.build();
	}
}
