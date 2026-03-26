package io.leasingninja.sales.domain;

import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
public record Car(String car) {

	public static Validated<Car> validate(String car) {
		if (car == null || car.isBlank())
			return Validated.invalid(new SalesError.CarError("Car must not be empty"));
		return Validated.valid(new Car(car));
	}

	public static Car of(String car) {
		return new Car(car);
	}

}
