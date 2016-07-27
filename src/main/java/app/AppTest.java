package app;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import domain.Grade;


@Configuration(value = "/applicationContext.xml")
public class AppTest {

	 @Autowired
	 @Qualifier("app")
	 public App app ;

	     public App getApp() {

	          return app;

	     }

	    public void setApp(App app) {

	        this.app = app;

	     }


	    
	public static void main(String[] args) {
		AppTest appTest = new AppTest();
		Grade grade = appTest.app.getGrade();
		
//		BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//		Grade grade = bf.getBean(Grade.class);
		
		//App at = (App) bf.getBean("app");

		System.out.println(grade.getStudent().getName());

		System.out.println(grade.getStudent().getId());
		
		
	}

}
