package ru.otus.eshop.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Version;
import org.springframework.hateoas.Identifiable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@ToString
@EqualsAndHashCode
public class BaseEntity implements Identifiable<Long> {
    @Id
    @GeneratedValue
    private Long id;
    @Version
    private long version;
}
