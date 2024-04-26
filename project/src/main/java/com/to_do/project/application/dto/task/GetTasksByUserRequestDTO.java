package com.to_do.project.application.dto.task;

import com.to_do.project.application.dto.common.PaginationDTO;

import java.util.Date;
import java.util.UUID;

public record GetTasksByUserRequestDTO(String content, Date startDate, Date endDate, String priority,
                                       boolean done, String sorting, int page, int size) {
}
