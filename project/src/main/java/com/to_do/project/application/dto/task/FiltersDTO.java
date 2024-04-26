package com.to_do.project.application.dto.task;

import java.util.Date;

public record FiltersDTO(String content, Date startDate, Date endDate, String priority, boolean done) {
}
