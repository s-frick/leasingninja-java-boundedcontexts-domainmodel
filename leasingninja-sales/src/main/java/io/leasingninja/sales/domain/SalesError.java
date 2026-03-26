package io.leasingninja.sales.domain;

public sealed interface SalesError {
    String message();

    record ContractNumberError(String message) implements SalesError {}
    record CustomerError(String message) implements SalesError {}
    record CarError(String message) implements SalesError {}
    record AmountError(String message) implements SalesError {}
}
