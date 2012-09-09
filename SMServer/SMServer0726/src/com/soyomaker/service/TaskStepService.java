package com.soyomaker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soyomaker.model.TaskStep;

@Service("taskStepService")
@Transactional
public class TaskStepService extends AbstractService<TaskStep> {

	public List<TaskStep> findByTaskId(int taskId) {
		return this.find("from TaskStep ts where ts.taskId=?", taskId);
	}

}
