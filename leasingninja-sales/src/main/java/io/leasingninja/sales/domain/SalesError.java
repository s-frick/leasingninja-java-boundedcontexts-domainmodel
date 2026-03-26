package io.leasingninja.sales.domain;

public sealed interface SalesError {
    String message();

    public record CustomerError(String message) implements SalesError{};

}
