package com.soyomaker.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.Task;

@Service("taskService")
@Transactional
public class TaskService extends AbstractService<Task> {

}