package domain;
import javax.annotation.*;

import org.springframework.stereotype.Component;

@Component
public class Grade {
	@SuppressWarnings("restriction")
	@Resource(name="student")
	private Student student;
	
	@SuppressWarnings("restriction")
	@Resource(name="course")
	private Course course;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
