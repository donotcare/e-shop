package ru.otus.eshop.model.process.delivery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Phone {
    private @NonNull String value;
}
