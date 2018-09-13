package com.ik.webservice.domain;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ik.webservice.domain.posts.Posts;
import com.ik.webservice.domain.posts.PostsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTest {

	@Autowired
	PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void �Խñ�����_�ҷ�����() {
		//given
		postsRepository.save(Posts.builder()
				.title("�׽�Ʈ �Խñ�")
				.content("�׽�Ʈ ����")
				.author("jojoldu@dddd.com")
				.build());
		//when
		List<Posts> postsList = postsRepository.findAll();
		
		//then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle(), is("�׽�Ʈ �Խñ�"));
		assertThat(posts.getContent(), is("�׽�Ʈ ����"));
	}
	
	@Test
    public void BaseTimeEntity_��� () {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("�׽�Ʈ �Խñ�")
                .content("�׽�Ʈ ����")
                .author("jojoldu@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
	
}
