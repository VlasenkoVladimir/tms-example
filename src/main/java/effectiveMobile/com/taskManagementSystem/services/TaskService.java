package effectiveMobile.com.taskManagementSystem.services;

import effectiveMobile.com.taskManagementSystem.domain.Task;
import effectiveMobile.com.taskManagementSystem.domain.User;
import effectiveMobile.com.taskManagementSystem.domain.enums.Priority;
import effectiveMobile.com.taskManagementSystem.domain.enums.Status;
import effectiveMobile.com.taskManagementSystem.dto.TaskDto;
import effectiveMobile.com.taskManagementSystem.mappers.TaskMapper;
import effectiveMobile.com.taskManagementSystem.repositories.TaskRepository;
import effectiveMobile.com.taskManagementSystem.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * Task service class
 */
@Slf4j
@Service
@Validated
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.taskMapper = TaskMapper.INSTANCE;
        this.userRepository = userRepository;
    }

    /**
     * Creating new task
     *
     * @param newTaskDto DTO with new task data
     * @return TaskDto if new task saved correctly
     */
    public TaskDto create(@Valid final TaskDto newTaskDto) {
        log.info("Call create new task {}", newTaskDto.getTitle());

        return taskMapper.toDto(taskRepository.save(taskMapper.toTask(newTaskDto)));
    }

    public TaskDto getById(@Valid final long id) {
        log.info("Call getById with id is {}", id);

        return taskMapper.toDto(taskRepository.getReferenceById(id));
    }

    /**
     * Get Page with list of tasks
     *
     * @param pageable Pageable
     * @return Page with all TaskDto
     */
    public Page<TaskDto> getAllPaginated(final Pageable pageable) {
        log.info("Call listAllTasksPaginated with pageable {}", pageable);

        Page<Task> tasksPaginated = taskRepository.findAll(pageable);
        List<TaskDto> result = tasksPaginated.getContent().stream().map(taskMapper::toDto).toList();

        return new PageImpl<>(result, pageable, tasksPaginated.getTotalElements());
    }


    /**
     * Get all tasks where author id is authorId
     *
     * @param authorId long author id
     * @return List<TaskDto> of all tasks by this author
     */
    public List<TaskDto> getTasksByAuthorId(@Valid final long authorId) {
        log.info("Call getTasksByAuthor with authorId is {}", authorId);

        return taskRepository.getTasksByAuthorId(authorId).stream().map(taskMapper::toDto).toList();
    }

    /**
     * Get all Tasks for author
     *
     * @param authorId long author id
     * @param pageable Pageable
     * @return Page with all Tasks where author id is authorId
     */
    public Page<TaskDto> getTasksByAuthorIdPaginated(@Valid final long authorId, final Pageable pageable) {
        log.info("Call getTasksByAuthor with authorId is {} and pageable is {}", authorId, pageable);

        Page<Task> taskPage = taskRepository.getTasksByAuthorId(authorId, pageable);
        List<TaskDto> result = taskPage.getContent().stream().map(taskMapper::toDto).toList();

        return new PageImpl<>(result, pageable, taskPage.getTotalElements());
    }

    /**
     * Get all tasks by executor
     *
     * @param executorId long executor id
     * @return List<TaskDto> of all tasks by this executor
     */
    public List<TaskDto> getTasksByExecutorId(@Valid final long executorId) {
        log.info("Call getTasksByExecutor with executorId is {}", executorId);

        return taskRepository.getTasksByExecutorId(executorId).stream().map(taskMapper::toDto).toList();
    }

    /**
     * Get all tasks of executor
     *
     * @param executorId long executor id
     * @param pageable   Pageable
     * @return Page with all Tasks where executor id is executorId
     */
    public Page<TaskDto> getTasksByExecutorIdPaginated(@Valid final long executorId, final Pageable pageable) {
        log.info("Call getTasksByExecutor with executorId is {} and pageable is {}", executorId, pageable);

        Page<Task> taskPage = taskRepository.getTasksByExecutorId(executorId, pageable);
        List<TaskDto> result = taskPage.getContent().stream().map(taskMapper::toDto).toList();

        return new PageImpl<>(result, pageable, taskPage.getTotalElements());
    }

    /**
     * Set Task executor
     *
     * @param taskId     long Task id
     * @param executorId long executor id
     * @return TaskDto if change correctly
     */
    public TaskDto setExecutor(@Valid final Long taskId, @Valid final long executorId) {
        log.info("Call setExecutor with task id is {} and executor id is {}", taskId, executorId);

        final Task taskForSetExecutor = taskRepository.findById(taskId).orElseThrow();
        final User executor = userRepository.findById(executorId).orElseThrow();
        taskForSetExecutor.setExecutor(executor);

        return taskMapper.toDto(taskRepository.save(taskForSetExecutor));
    }

    /**
     * Change task status
     *
     * @param newStatus new Task Status
     * @param taskId    long Task id
     * @return TaskDto if executed correctly
     */
    public TaskDto changeStatus(@Valid final Status newStatus, @Valid final long taskId) {
        log.info("Call changeStatus with Status is {} and task id is {}", newStatus, taskId);

        final Task taskForChangeStatus = taskRepository.findById(taskId).orElseThrow();
        taskForChangeStatus.setStatus(newStatus);

        return taskMapper.toDto(taskRepository.save(taskForChangeStatus));
    }

    /**
     * Change Priority for existed Task
     *
     * @param newPriority new Task Priority
     * @param taskId      long Task id
     * @return TaskDto if executed correctly
     */
    public TaskDto changePriority(@Valid final Priority newPriority, @Valid final long taskId) {
        log.info("Call changePriority with Priority is {} and task id is {}", newPriority, taskId);

        final Task taskForChangePriority = taskRepository.findById(taskId).orElseThrow();
        taskForChangePriority.setPriority(newPriority);


        return taskMapper.toDto(taskRepository.save(taskForChangePriority));
    }

    /**
     * Update existed task
     *
     * @param updatedTask Task for update
     * @return TaskDto of updated Task
     */
    public TaskDto update(@Valid final TaskDto updatedTask) {
        log.info("Call update with TaskDto is {}", updatedTask);

        return taskMapper.toDto(taskRepository.save(taskMapper.toTask(updatedTask)));
    }

    /**
     * Delete Task by id
     *
     * @param id long Task id
     * @return true if correctly executed
     */
    public boolean delete(@Valid final Long id) {
        log.info("Call delete with task id is {}", id);

        taskRepository.deleteById(id);

        return true;
    }
}