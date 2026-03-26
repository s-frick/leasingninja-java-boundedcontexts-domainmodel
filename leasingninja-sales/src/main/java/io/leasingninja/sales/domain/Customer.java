package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

import io.leasingninja.sales.domain.SalesError.CustomerError;
import static io.leasingninja.sales.domain.Either.*;


@ValueObject
public record Customer(String customer) {

    public static Either<Customer> tryOf(String customer) {
        if (!isValid(customer)) return failure(new CustomerError("Invalid customer name"));
        return success(of(customer));
    }

	public static Customer of(String customer) {
        assert isValid(customer);
		return new Customer(customer);
	}

    public static boolean isValid(String nameString) {
        return nameString.matches("^\\p{L}+(\\s\\p{L}+)*$");
    }

}
