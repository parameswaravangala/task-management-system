package com.bank.taskmanagement.controller;

import com.bank.taskmanagement.dto.NotificationTemplateDTO;
import com.bank.taskmanagement.entity.NotificationTemplate;
import com.bank.taskmanagement.mapper.NotificationTemplateMapper;
import com.bank.taskmanagement.service.NotificationTemplateService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/notification-template")
//@RestController
public class NotificationTemplateControllerImpl {
    private final NotificationTemplateService notificationTemplateService;
    private final NotificationTemplateMapper notificationTemplateMapper;

    public NotificationTemplateControllerImpl(NotificationTemplateService notificationTemplateService, NotificationTemplateMapper notificationTemplateMapper) {
        this.notificationTemplateService = notificationTemplateService;
        this.notificationTemplateMapper = notificationTemplateMapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationTemplateDTO save(@RequestBody NotificationTemplateDTO notificationTemplateDTO) {
        NotificationTemplate notificationTemplate = notificationTemplateMapper.asEntity(notificationTemplateDTO);
        return notificationTemplateMapper.asDTO(notificationTemplateService.save(notificationTemplate));
    }

    
    @GetMapping("/{id}")
    public NotificationTemplateDTO findById(@PathVariable("id") Long id) {
        NotificationTemplate notificationTemplate = notificationTemplateService.findById(id).orElse(null);
        return notificationTemplateMapper.asDTO(notificationTemplate);
    }

    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        notificationTemplateService.deleteById(id);
    }

    
    @GetMapping
    public List<NotificationTemplateDTO> list() {
        return notificationTemplateMapper.asDTOList(notificationTemplateService.findAll());
    }

    
    @GetMapping("/page-query")
    public Page<NotificationTemplateDTO> pageQuery(Pageable pageable) {
        Page<NotificationTemplate> notificationTemplatePage = notificationTemplateService.findAll(pageable);
        List<NotificationTemplateDTO> dtoList = notificationTemplatePage
                .stream()
                .map(notificationTemplateMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, notificationTemplatePage.getTotalElements());
    }

    
    @PutMapping("/{id}")
    public NotificationTemplateDTO update(@RequestBody NotificationTemplateDTO notificationTemplateDTO, @PathVariable("id") Long id) {
        NotificationTemplate notificationTemplate = notificationTemplateMapper.asEntity(notificationTemplateDTO);
        return notificationTemplateMapper.asDTO(notificationTemplateService.update(notificationTemplate, id));
    }
}