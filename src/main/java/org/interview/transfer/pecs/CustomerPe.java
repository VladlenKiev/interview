package org.interview.transfer.pecs;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomerPe extends Customer {
    private String type;
    private String iban;
}
