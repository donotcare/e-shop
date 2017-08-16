package ru.otus.eshop.process.order.delivery;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Address {
    private @NonNull String value;

    public static Address of(String address) {
        return new Address(address);
    }
}
