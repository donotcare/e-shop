package ru.otus.eshop.process.order.delivery;

import lombok.*;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import java.util.Collections;
import java.util.List;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class DeliveryInfo {
    private @NonNull String customerName;
    private @NonNull Address address;
    @ElementCollection
    private @NonNull List<Phone> phones;

    public static DeliveryInfo of(String customerName, String address, String phone) {
        return  new DeliveryInfo(customerName, Address.of(address), Collections.singletonList(Phone.of(phone)));
    }
}
