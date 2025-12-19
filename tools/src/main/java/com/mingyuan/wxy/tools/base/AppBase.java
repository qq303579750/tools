package com.mingyuan.wxy.tools.base;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.mingyuan.wxy.tools.exception.CommonException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static cn.hutool.core.bean.BeanUtil.copyProperties;
import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.commons.lang3.StringUtils.joinWith;
import static org.springframework.util.ObjectUtils.isEmpty;

public interface AppBase {


    static <T, S> T toBeanIgnoreId(S source, Class<T> dist) {
        return toBean(source, dist, "id");
    }

    static <T, S> T merge(S source, T dist, String... ignoreProperties) {
        copyProperties(source, dist, CopyOptions.create()
                .setIgnoreProperties(ignoreProperties)
                .ignoreNullValue()
                .ignoreError());
        return dist;
    }

    static <T, S> T toBean(S source, Class<T> dist, String... ignoreProperties) {
        return BeanUtil.toBean(source, dist, CopyOptions.create()
                .setIgnoreProperties(ignoreProperties)
                .ignoreNullValue()
                .ignoreError());
    }

    static void assertNotBlankThrowException(Object target, String message) {
        if (isEmpty(target)) {
            throw new CommonException(message);
        }
    }

    static void assertNotBlank(Object target, String message) {
        if (isEmpty(target)) {
            throw new CommonException(message);
        }
    }

    static <T> T assertExist(Supplier<T> predicate, Object... error) {
        try {
            T target = predicate.get();
            if (isEmpty(target)) {
                String message = Arrays.stream(error)
                        .map(Object::toString)
                        .collect(Collectors.joining());
                throw new CommonException(message);
            }
            return target;
        } catch (Throwable t) {
            throw new CommonException(t, Arrays.stream(error)
                    .map(Object::toString)
                    .collect(Collectors.joining()));
        }
    }

    static <T> T assertExist(T predicate, Object... error) {
        return assertExist(() -> predicate, error);
    }

    static <T> void assertNotExist(Supplier<T> predicate, Object... error) {
        try {
            T target = predicate.get();
            if (isNotEmpty(target)) {
                String message = Arrays.stream(error)
                        .map(Object::toString)
                        .collect(Collectors.joining());
                throw new CommonException(message);
            }
        } catch (Throwable t) {
            throw new CommonException(t, Arrays.stream(error)
                    .map(Object::toString)
                    .collect(Collectors.joining()));
        }
    }

    static <T> void assertNotExist(T target, Object... error) {
        assertNotExist(() -> target, error);
    }

    static void assertTrue(Supplier<Boolean> supplier, Object... error) {
        try {
            if (isFalse(supplier.get())) {
                String message = Arrays.stream(error)
                        .map(Object::toString)
                        .collect(Collectors.joining());
                throw new CommonException(message);
            }
        } catch (Throwable t) {
            throw new CommonException(t, Arrays.stream(error)
                    .map(Object::toString)
                    .collect(Collectors.joining()));
        }
    }

    static void assertTrue(Boolean bool, Object... error) {
        assertTrue(() -> bool, error);
    }

    static void assertFalse(Boolean bool, Object... error) {
        assertTrue(() -> !bool, error);
    }

    static boolean assertNotEmpty(Object target) {
        return isNotEmpty(target);
    }

    static boolean isNotEmpty(@Nullable Object target) {
        return ObjectUtils.isNotEmpty(target);
    }

    static void assertEmptyThrowException(Object target, String message) {
        if (isEmpty(target)) {
            throw new CommonException(message);
        }
    }

    static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !CollectionUtils.isEmpty(map);
    }

    static <T, S> T toBean(S source, Class<T> dist, Map<String, String> fieldMapping) {
        return BeanUtil.toBean(source, dist, CopyOptions.create()
                .setFieldMapping(fieldMapping)
                .ignoreNullValue()
                .ignoreError());
    }

    static <T, S> void toBean(S source, T dist, String... fields) {
        toBean(source, dist,
                Arrays.stream(fields).collect(Collectors.toMap(k -> k, v -> v)));
    }

    static <T, S> void toBean(S source, T dist, Map<String, String> fieldMapping) {
        copyProperties(source, dist, CopyOptions.create()
                .setFieldMapping(fieldMapping)
                .ignoreNullValue()
                .ignoreError());
    }

    static String defaultString(Object... targets) {
        return Arrays.stream(targets)
                .filter(Objects::nonNull)
                .map(Object::toString)
                .findFirst().orElse("");
    }

    static <T, S> List<T> toList(Collection<S> sources, Class<T> dist, String... ignoreProperties) {
        if (isEmpty(sources)) {
            return Collections.emptyList();
        }
        return sources.stream()
                .map(source -> toBean(source, dist, ignoreProperties))
                .collect(Collectors.toList());
    }

    static <T> Predicate<T> distinctBy(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    @SafeVarargs
    static <T, R> R defaultObject(Function<? super T, ? extends R> mapper, T... targets) {
        return Arrays.stream(targets)
                .filter(Objects::nonNull)
                .map(mapper)
                .findFirst().orElse(null);
    }

    static void assertSuccess(BooleanSupplier predicate, Object... error) {
        String message = String.format("校验失败:%s", joinWith(",", error));
        try {
            if (!predicate.getAsBoolean()) {
                throw new CommonException(message);
            }
        } catch (Throwable t) {
            throw new CommonException(t, message);
        }
    }

    interface Upsert {
    }

    interface Update {
    }

    interface Query {
    }

}
