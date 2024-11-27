package cn.howardliu.tutorials.mapstruct.condition;

import static org.mapstruct.ReportingPolicy.IGNORE;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.mapstruct.AfterMapping;
import org.mapstruct.Condition;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-27
 */
@Mapper(unmappedTargetPolicy = IGNORE)
public interface LicenseMapper {
    @Mapping(target = "startDate", expression = "java(mapStartDate(licenseDto))")
    // @Mapping(target = "startDate", expression = "java(licenseDto.getStartDate() != null ? licenseDto.getStartDate().atOffset(java.time.ZoneOffset.UTC) : java.time.OffsetDateTime.now())")
    @Mapping(target = "renewalRequired", conditionExpression = "java(isEndDateInTwoWeeks(licenseDto))", source = ".")
    @Mapping(target = "endDate", ignore = true)
    License toLicense(LicenseDto licenseDto);

    @AfterMapping
    default void afterMapping(LicenseDto licenseDto, @MappingTarget License license) {
        OffsetDateTime endDate = licenseDto.getEndDate() != null ? licenseDto.getEndDate().atOffset(ZoneOffset.UTC)
                                                                 : OffsetDateTime.now().plusYears(1);
        license.setEndDate(endDate);
    }

    default OffsetDateTime mapStartDate(LicenseDto licenseDto) {
        return licenseDto.getStartDate() != null
               ? licenseDto.getStartDate().atOffset(ZoneOffset.UTC) : OffsetDateTime.now();
    }

    default boolean isEndDateInTwoWeeks(LicenseDto licenseDto) {
        return licenseDto.getEndDate() != null
                && Duration.between(licenseDto.getEndDate(), LocalDateTime.now()).toDays() <= 14;
    }

    @Condition
    default boolean mapsToExpectedLicenseType(String licenseType) {
        try {
            if (licenseType == null) {
                return false;
            }
            License.LicenseType.valueOf(licenseType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
