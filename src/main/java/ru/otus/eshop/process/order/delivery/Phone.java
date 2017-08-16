package ru.otus.eshop.process.order.delivery;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Phone {
    private @NonNull String value;

    public static Phone of(String value) {
        return new Phone(value);
    }
}
