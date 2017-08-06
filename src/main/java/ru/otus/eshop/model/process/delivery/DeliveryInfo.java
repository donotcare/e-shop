package ru.otus.eshop.model.process.delivery;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class DeliveryInfo {
    private @NonNull String customerName;
    private @NonNull Address address;
    @ElementCollection
    private @NonNull List<Phone> phones;
}
