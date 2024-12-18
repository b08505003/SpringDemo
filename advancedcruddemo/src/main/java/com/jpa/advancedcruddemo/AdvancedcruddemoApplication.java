package com.jpa.advancedcruddemo;

import com.jpa.advancedcruddemo.dao.AppDAO;
import com.jpa.advancedcruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedcruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedcruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO){
		return runner -> {
			//createInstructor(appDAO);
			//createInstructorWithCourses(appDAO);
			//createCourseAndReviews(appDAO);
			//createCourseAndStudents(appDAO);

			//findInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCourseAndReviews(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);

			//deleteInstructor(appDAO);
			//deleteInstructorDetail(appDAO);
			//deleteCourse(appDAO);
			//deleteCourseAndReviews(appDAO);
			deleteStudent(appDAO);

			//updateInstructor(appDAO);
			//updateCourse(appDAO);

			//addCoursesForStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int id = 1;

		System.out.println("Deleting student id: " + id);
		appDAO.deleteStudentById(id);
		System.out.println("Done!");
	}

	private void addCoursesForStudent(AppDAO appDAO) {
		int id = 2;
		Student student = appDAO.findStudentAndCoursesByStudentId(id);

		Course course1 = new Course("Primary Japanese");
		Course course2 = new Course("Dancing Lesson");

		student.addCourse(course1);
		student.addCourse(course2);

		System.out.println("Updating student: " + student);
		System.out.println("associated courses: " + student.getCourses());
		appDAO.update(student);
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int id = 2;

		Student student = appDAO.findStudentAndCoursesByStudentId(id);
		System.out.println("Student: " + student);
		System.out.println("associated courses: " + student.getCourses());
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int id = 10;

		Course course = appDAO.findCourseAndStudentsByCourseId(id);
		System.out.println("Courses: " + course);
		System.out.println("associated students: " + course.getStudents());
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course course = new Course("Calculus");

		Student student1 = new Student("Gawr", "Gura", "gura@hololive.en");
		Student student2 = new Student("Ninomae", "Ina'nis", "ina@hololive.en");

		course.addStudent(student1);
		course.addStudent(student2);

		System.out.println("Saving course: " + course);
		System.out.println("associated students: " + course.getStudents());
		appDAO.save(course);
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int id = 10;

		System.out.println("Deleting course id: " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void findCourseAndReviews(AppDAO appDAO) {
		int id = 10;

		Course course = appDAO.findCourseAndReviewsByCourseId(id);
		System.out.println(course);
		System.out.println(course.getReviews());
	}


	private void createCourseAndReviews(AppDAO appDAO) {
		Course course = new Course("Three point Jumper");
		course.add(new Review("Great!"));
		course.add(new Review("Can't pass."));
		course.add(new Review("This is bad. Do not choose."));

		System.out.println("Saving the course");
		System.out.println(course);
		System.out.println(course.getReviews());
		appDAO.save(course);
	}

	private void deleteCourse(AppDAO appDAO) {
		int id = 10;

		System.out.println("Deleting course id: " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;

		System.out.println("Finding course id: " + id);
		Course course = appDAO.findCourseById(id);

		System.out.println("Updating course id: " + id);
		course.setTitle("Buzzer Beater");
		appDAO.update(course);
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;

		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Updating instructor id: " + id);
		instructor.setFirstName("Bronny");
		appDAO.update(instructor);
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);

//		Instructor instructor = appDAO.findInstructorById(id);
//		System.out.println("Instructor: " + instructor);

		// exception when the fetch type is lazy
		//System.out.println("Courses: " + instructor.getCourses());
/*
		// a solution by using instructor id to find courses, but not good enough
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		// then associate to instructor
		instructor.setCourses(courses);
		System.out.println("Courses: " + instructor.getCourses());

 */
		// use join fetch (when we want to perform eager loading without changing its fetch type)
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor: " + instructor);
		System.out.println("Associated courses: " + instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("Lebron", "James", "lbj@nba.com");

		InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com", "Basketball");

		instructor.setInstructorDetail(instructorDetail);

		Course course1 = new Course("Lay Up");
		Course course2 = new Course("Dunking");
		instructor.add(course1);
		instructor.add(course2);

		System.out.println("Saving instructor: " + instructor);
		System.out.println("Courses: " + instructor.getCourses());
		appDAO.save(instructor); // also saves courses because of CascadeType.PERSIST
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int id = 4;

		System.out.println("Deleting instructor detail id: " + id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Done!");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		int id = 1;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);

		System.out.println("Finding instructor id: " + id);
		System.out.println("Instructor detail: " + instructorDetail);
		System.out.println("The associated instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;

		System.out.println("Deleting instructor id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Done!");
	}

	private void findInstructor(AppDAO appDAO) {
		int id = 1;
		Instructor instructor = appDAO.findInstructorById(id);

		System.out.println("Finding instructor id: " + id);
		System.out.println("Instructor: " + instructor);
		System.out.println("Details: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		Instructor instructor = new Instructor("Lebron", "James", "lbj@nba.com");

		InstructorDetail instructorDetail = new InstructorDetail("http://www.nba.com/youtube", "Basketball");

		instructor.setInstructorDetail(instructorDetail);

		System.out.println("Saving instructor: " + instructor); // also saves instructor detail because of CascadeType.ALL
		appDAO.save(instructor);

	}

}
