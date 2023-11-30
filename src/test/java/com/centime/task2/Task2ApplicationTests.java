package com.centime.task2;

import com.centime.task2.entity.Task;
import com.centime.task2.repository.TaskRepository;
import com.centime.task2.service.TaskService;
import com.centime.task2.serviceimpl.TaskServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class Task2ApplicationTests {

	@Autowired
	private TaskServiceImpl service;

	@MockBean
	private TaskRepository repository;

	@Test
	public void getTasksTest() {
		when(repository.findAll()).thenReturn(Stream
				.of(new Task(1L, 0L, "Ravi", "white"), new Task(2L, 1L, "Kiran", "Green")).collect(Collectors.toList()));
		Assert.assertEquals(2, service.getAllTasks().size());
	}



	@Test
	public void saveTaskTest() {
		Task user = new Task(1L, 0L, "Shyam", "Red");
		when(repository.save(user)).thenReturn(user);
		Assert.assertEquals(user,service.saveTask(user));
	}

	@Test
	public void deleteTaskTest() {
		Task user = new Task(1L, 0L, "Shyam", "Red");
		service.deleteTask(user);
		verify(repository, times(1)).delete(user);
	}

}
