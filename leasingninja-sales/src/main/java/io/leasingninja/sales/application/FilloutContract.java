package io.leasingninja.sales.application;

import java.util.Optional;

import org.jmolecules.architecture.layered.ApplicationLayer;
import org.jmolecules.ddd.annotation.Service;

import io.leasingninja.sales.domain.Amount;
import io.leasingninja.sales.domain.Car;
import io.leasingninja.sales.domain.Contract;
import io.leasingninja.sales.domain.ContractNumber;
import io.leasingninja.sales.domain.Contracts;
import io.leasingninja.sales.domain.Currency;
import io.leasingninja.sales.domain.Customer;
import io.leasingninja.sales.domain.SalesError;

@ApplicationLayer
@Service
public class FilloutContract {

	private final Contracts contracts;

	public FilloutContract(Contracts contracts) {
		this.contracts = contracts;
	}

	public Optional<SalesError> with(String number, String customer, String car, long amountInCents, Currency currency) {
        var validNumber = ContractNumber.of(number);
        var validCar = Car.of(car);
        var validPrice = Amount.of(amountInCents, currency);

        var validatedCustomer = Customer.tryOf(customer);
        if (validatedCustomer.isFailure()) return Optional.of(validatedCustomer.error());

		contracts.save(new Contract(
				validNumber,
				validatedCustomer.value(),
				validCar,
				validPrice));

        return Optional.<SalesError>empty();
	}

}
