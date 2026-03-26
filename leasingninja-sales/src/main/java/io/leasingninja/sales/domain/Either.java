package io.leasingninja.sales.domain;

public record Either<T>(T value, SalesError error) {

      public Either {
          assert (value != null ^ error != null);
      }

      public boolean isSuccess() {
          return value != null;
      }

      public boolean isFailure() {
          return !isSuccess();
      }

      public static <T> Either<T> success(T value) {
          return new Either<>(value, null);
      }

      public static <T> Either<T> failure(SalesError error) {
          return new Either<>(null, error);
      }
  }
