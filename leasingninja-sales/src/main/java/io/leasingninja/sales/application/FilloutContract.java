package io.leasingninja.sales.application;

import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;

import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;
import io.leasingninja.sales.domain.Currency;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.domain.Validated;

@ApplicationLayer
@Service
public class FilloutContract {

	private final Contracts contracts;

	public FilloutContract(Contracts contracts) {
		this.contracts = contracts;
	}

	public Validated<Contract> with(String number, String customer, String car, long amountInCents, Currency currency) {
		var result = Validated.combine(
				ContractNumber.validate(number),
				Customer.validate(customer),
				Car.validate(car),
				Amount.validate(amountInCents, currency),
				Contract::new
		);
		if (result instanceof Validated.Valid<Contract> valid) {
			contracts.save(valid.value());
		}
		return result;
	}

}
