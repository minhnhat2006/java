package com.sharpinu.persist.repositories;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sharpinu.spring.context.RepoConfig;



public class RepositoryTesting {
	public static void main(String args[]) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(RepoConfig.class);
		SkillRepo skillRepo = ctx.getBean(SkillRepo.class);
		for (int i = 0; i < 10; i++ ) {
			//System.out.println(userRepo.findByUserEmail("kevin.le.hung@clearpathdevelopment.com").getUserPassword());
			System.out.println(skillRepo.findAll());
		}
	}
}
