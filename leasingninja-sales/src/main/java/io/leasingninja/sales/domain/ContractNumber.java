package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record ContractNumber(String number) {

	public static Validated<ContractNumber> validate(String number) {
		if (number == null || number.isBlank())
			return Validated.invalid(new SalesError.ContractNumberError("Contract number must not be empty"));
		if (number.length() < 4)
			return Validated.invalid(new SalesError.ContractNumberError("Contract number must have at least 4 characters"));
		return Validated.valid(new ContractNumber(number));
	}

	public static ContractNumber of(String number) {
		return new ContractNumber(number);
	}

}
