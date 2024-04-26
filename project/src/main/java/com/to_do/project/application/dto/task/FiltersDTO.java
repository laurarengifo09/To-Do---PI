package com.to_do.project.application.dto.task;

import java.util.Date;
import java.util.Optional;

public record FiltersDTO(String content, Date startDate, Date endDate, String priority, Boolean done, Boolean deleted){
}
