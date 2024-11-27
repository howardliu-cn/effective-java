package cn.howardliu.tutorials.mapstruct.condition;

import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.Data;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-27
 */
@Data
public class License {
    private UUID id;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    private boolean active;
    private boolean renewalRequired;
    private LicenseType licenseType;

    public enum LicenseType {
        INDIVIDUAL, FAMILY
    }
}
