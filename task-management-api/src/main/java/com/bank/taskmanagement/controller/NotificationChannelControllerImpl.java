package com.bank.taskmanagement.controller;

import com.bank.taskmanagement.dto.NotificationChannelDTO;
import com.bank.taskmanagement.entity.NotificationChannel;
import com.bank.taskmanagement.mapper.NotificationChannelMapper;
import com.bank.taskmanagement.service.NotificationChannelService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/notification-channel")
//@RestController
public class NotificationChannelControllerImpl {
    private final NotificationChannelService notificationChannelService;
    private final NotificationChannelMapper notificationChannelMapper;

    public NotificationChannelControllerImpl(NotificationChannelService notificationChannelService, NotificationChannelMapper notificationChannelMapper) {
        this.notificationChannelService = notificationChannelService;
        this.notificationChannelMapper = notificationChannelMapper;
    }

    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NotificationChannelDTO save(@RequestBody NotificationChannelDTO notificationChannelDTO) {
        NotificationChannel notificationChannel = notificationChannelMapper.asEntity(notificationChannelDTO);
        return notificationChannelMapper.asDTO(notificationChannelService.save(notificationChannel));
    }

    
    @GetMapping("/{id}")
    public NotificationChannelDTO findById(@PathVariable("id") Integer id) {
        NotificationChannel notificationChannel = notificationChannelService.findById(id).orElse(null);
        return notificationChannelMapper.asDTO(notificationChannel);
    }

    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        notificationChannelService.deleteById(id);
    }

    
    @GetMapping
    public List<NotificationChannelDTO> list() {
        return notificationChannelMapper.asDTOList(notificationChannelService.findAll());
    }

    
    @GetMapping("/page-query")
    public Page<NotificationChannelDTO> pageQuery(Pageable pageable) {
        Page<NotificationChannel> notificationChannelPage = notificationChannelService.findAll(pageable);
        List<NotificationChannelDTO> dtoList = notificationChannelPage
                .stream()
                .map(notificationChannelMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, notificationChannelPage.getTotalElements());
    }

    
    @PutMapping("/{id}")
    public NotificationChannelDTO update(@RequestBody NotificationChannelDTO notificationChannelDTO, @PathVariable("id") Integer id) {
        NotificationChannel notificationChannel = notificationChannelMapper.asEntity(notificationChannelDTO);
        return notificationChannelMapper.asDTO(notificationChannelService.update(notificationChannel, id));
    }
}