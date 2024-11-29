package cn.howardliu.tutorials.mapstruct.enummap;

import static org.mapstruct.MappingConstants.ANY_REMAINING;
import static org.mapstruct.MappingConstants.ANY_UNMAPPED;
import static org.mapstruct.MappingConstants.CASE_TRANSFORMATION;
import static org.mapstruct.MappingConstants.NULL;
import static org.mapstruct.MappingConstants.PREFIX_TRANSFORMATION;
import static org.mapstruct.MappingConstants.STRIP_PREFIX_TRANSFORMATION;
import static org.mapstruct.MappingConstants.STRIP_SUFFIX_TRANSFORMATION;
import static org.mapstruct.MappingConstants.SUFFIX_TRANSFORMATION;
import static org.mapstruct.MappingConstants.THROW_EXCEPTION;

import org.mapstruct.EnumMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.factory.Mappers;

/**
 * @author 看山 howarldiu.cn <a href="mailto:howardliu1988@163.com">Howard Liu</a>
 * Created on 2024-11-28
 */
@Mapper
public interface TrafficSignalMapper {
    TrafficSignalMapper INSTANCE = Mappers.getMapper(TrafficSignalMapper.class);

    //@ValueMapping(target = "OFF", source = "OFF")
    @ValueMapping(target = "GO", source = "MOVE")
    @ValueMapping(target = "STOP", source = "HALT")
    TrafficSignal toTrafficSignal(RoadSign source);

    @ValueMapping(target = "OFF", source = "off")
    @ValueMapping(target = "GO", source = "move")
    @ValueMapping(target = "STOP", source = "halt")
    TrafficSignal stringToTrafficSignal(String source);

    @ValueMapping(target = "off", source = "OFF")
    @ValueMapping(target = "go", source = "GO")
    @ValueMapping(target = "stop", source = "STOP")
    String trafficSignalToString(TrafficSignal source);

    @EnumMapping(nameTransformationStrategy = SUFFIX_TRANSFORMATION, configuration = "_VALUE")
    TrafficSignalSuffixed applySuffix(TrafficSignal source);

    @EnumMapping(nameTransformationStrategy = PREFIX_TRANSFORMATION, configuration = "VALUE_")
    TrafficSignalPrefixed applyPrefix(TrafficSignal source);

    @EnumMapping(nameTransformationStrategy = STRIP_SUFFIX_TRANSFORMATION, configuration = "_VALUE")
    TrafficSignal stripSuffix(TrafficSignalSuffixed source);

    @EnumMapping(nameTransformationStrategy = STRIP_PREFIX_TRANSFORMATION, configuration = "VALUE_")
    TrafficSignal stripPrefix(TrafficSignalPrefixed source);

    @EnumMapping(nameTransformationStrategy = CASE_TRANSFORMATION, configuration = "lower")
    TrafficSignalLowercase applyLowercase(TrafficSignal source);

    @EnumMapping(nameTransformationStrategy = CASE_TRANSFORMATION, configuration = "upper")
    TrafficSignal applyUppercase(TrafficSignalLowercase source);

    @EnumMapping(nameTransformationStrategy = CASE_TRANSFORMATION, configuration = "capital")
    TrafficSignalCapital lowercaseToCapital(TrafficSignalLowercase source);

    @Mapping(target = "number", source = ".")
    TrafficSignalNumber trafficSignalToTrafficSignalNumber(TrafficSignal source);

    @ValueMapping(target = "OFF", source = "OFF")
    @ValueMapping(target = "OFF", source = "STOP")
    @ValueMapping(target = "ON", source = "GO")
    SimpleTrafficSignal toSimpleTrafficSignal(TrafficSignal source);

    @ValueMapping(target = "ON", source = "GO")
    @ValueMapping(target = "OFF", source = ANY_REMAINING)
    SimpleTrafficSignal toSimpleTrafficSignalWithRemaining(TrafficSignal source);

    @ValueMapping(target = "ON", source = "GO")
    @ValueMapping(target = "OFF", source = ANY_UNMAPPED)
    SimpleTrafficSignal toSimpleTrafficSignalWithUnmapped(TrafficSignal source);

    @ValueMapping(target = "OFF", source = NULL)
    @ValueMapping(target = "ON", source = "GO")
    @ValueMapping(target = NULL, source = ANY_UNMAPPED)
    SimpleTrafficSignal toSimpleTrafficSignalWithNullHandling(TrafficSignal source);

    @ValueMapping(target = "ON", source = "GO")
    @ValueMapping(target = THROW_EXCEPTION, source = ANY_UNMAPPED)
    @ValueMapping(target = THROW_EXCEPTION, source = NULL)
    SimpleTrafficSignal toSimpleTrafficSignalWithExceptionHandling(TrafficSignal source);
}
