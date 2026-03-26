package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Customer(String customer) {

    public static Validated<Customer> validate(String customer) {
        if (!isValid(customer))
            return Validated.invalid(new SalesError.CustomerError("Invalid customer name"));
        return Validated.valid(new Customer(customer));
    }

	public static Customer of(String customer) {
        assert isValid(customer);
		return new Customer(customer);
	}

    public static boolean isValid(String nameString) {
        return nameString.matches("^\\p{L}+(\\s\\p{L}+)*$");
    }

}
