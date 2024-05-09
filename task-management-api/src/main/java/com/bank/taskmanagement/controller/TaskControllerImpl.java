package com.bank.taskmanagement.controller;

import com.bank.taskmanagement.dao.SpecificationsBuilder;
import com.bank.taskmanagement.dao.TaskRepository;
import com.bank.taskmanagement.dto.TaskDTO;
import com.bank.taskmanagement.entity.Task;
import com.bank.taskmanagement.exception.ResourceNotFoundException;
import com.bank.taskmanagement.mapper.TaskMapper;
import com.bank.taskmanagement.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.bank.taskmanagement.util.SearchOperation.OPERATION_SET;

@Validated
@RequestMapping("/api/task")
@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Task API")
public class TaskControllerImpl {
    private final TaskService taskService;
    private final TaskMapper taskMapper;
    private final TaskRepository dao;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO save(@RequestBody @Valid TaskDTO taskDTO) {
        Task task = taskMapper.asEntity(taskDTO);
        return taskMapper.asDTO(taskService.save(task));
    }

    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable("id") @Min(value = 1, message = "id must be a positive integer") Long id) {
        Task task = taskService.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Requested resource with id:" + id + " not found.");
        });
        return taskMapper.asDTO(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @Min(value = 1, message = "id must be a positive integer") Long id) {
        taskService.deleteById(id);
    }

    @Operation(summary = "Get all tasks")
    @GetMapping
    public Page<TaskDTO> list(@ParameterObject Pageable pageable) {
        return pageQuery(pageable, "");
    }

    @Operation(summary = "search tasks by criteria")
    @GetMapping("/search")
    public Page<TaskDTO> pageQuery(@ParameterObject Pageable pageable, @Parameter(description = "Seach query should have pattern: (\\w+?)(:|!|>|<|~)(\\*?)(.*)(\\*?)\n" +
            "Example: GET /api/task/search?query=title:FirstTask,priority>0,status:TO_DO\n" +
            "\n" +
            "Below are supported operations\n" +
            "\n" +
            "Equality: represented by colon (:)\n" +
            "Negation: represented by Exclamation mark (!)\n" +
            "Greater than: represented by (>)\n" +
            "Less than: represented by (<)\n" +
            "Starts with: represented by (=prefix*)\n" +
            "Ends with: represented by (=*suffix)\n" +
            "Contains: represented by (=*substring*)") @RequestParam String query) {
        Specification<Task> taskSpecification = resolveSpecification(query);
        Page<Task> taskPage = taskService.findAll(taskSpecification, pageable);
        List<TaskDTO> dtoList = taskPage
                .stream()
                .map(taskMapper::asDTO).toList();
        return new PageImpl<>(dtoList, pageable, taskPage.getTotalElements());
    }

    @PutMapping("/{id}")
    public TaskDTO update(@RequestBody @Valid TaskDTO taskDTO, @PathVariable("id") @Min(value = 1, message = "id must be greater than ${value}") Long id) {
        Task task = taskMapper.asEntity(taskDTO);
        return taskMapper.asDTO(taskService.update(task, id));
    }

    public Specification<Task> resolveSpecification(String searchQuery) {

        String operationSetExpr = String.join("|", OPERATION_SET);
        log.info("operationSetExpr ==> {}", operationSetExpr);
        SpecificationsBuilder builder = new SpecificationsBuilder();
        Pattern pattern = Pattern.compile(
                "(\\w+?)(" + operationSetExpr + ")(\\*?)(.*)(\\*?)(and|or)");
        Matcher matcher = pattern.matcher(searchQuery + ",");
        List<MatchResult> matches = matcher.results().toList();
        while (matcher.find()) {
            builder.with(
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(4),
                    matcher.group(3),
                    matcher.group(5));
        }

        return builder.build();
    }
}