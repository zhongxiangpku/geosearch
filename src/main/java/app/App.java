package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import domain.Grade;

@SuppressWarnings("restriction")
@Service("app")
@Scope("singleton")
public class App {
	
	@Autowired
    @Qualifier("grade")
	public Grade grade;

    public Grade getGrade() {
    	return grade;
    }

    public void setGrade(Grade grade) {
    	this.grade = grade;
    }

	public static void main(String[] args) {
		
	}
	
	@PostConstruct
    public void init(){
         System.out.println("app 在初始化！");
    }

    @PreDestroy
    public void destory(){           
    System.out.println("app 被销毁！");

 }

}
