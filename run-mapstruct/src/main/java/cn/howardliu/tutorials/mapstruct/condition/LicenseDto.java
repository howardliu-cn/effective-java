package cn.howardliu.tutorials.mapstruct.condition;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-27
 */
@Data
public class LicenseDto {
    private UUID id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String licenseType;
}
