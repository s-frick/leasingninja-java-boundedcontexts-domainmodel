package io.leasingninja.sales.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

public sealed interface Validated<T> {

    record Valid<T>(T value) implements Validated<T> {}
    record Invalid<T>(List<SalesError> errors) implements Validated<T> {}

    static <T> Validated<T> valid(T value) {
        return new Valid<>(value);
    }

    static <T> Validated<T> invalid(SalesError error) {
        return new Invalid<>(List.of(error));
    }

    @FunctionalInterface interface Function3<A, B, C, R> { R apply(A a, B b, C c); }
    @FunctionalInterface interface Function4<A, B, C, D, R> { R apply(A a, B b, C c, D d); }
    @FunctionalInterface interface Function5<A, B, C, D, E, R> { R apply(A a, B b, C c, D d, E e); }
    @FunctionalInterface interface Function6<A, B, C, D, E, F, R> { R apply(A a, B b, C c, D d, E e, F f); }
    @FunctionalInterface interface Function7<A, B, C, D, E, F, G, R> { R apply(A a, B b, C c, D d, E e, F f, G g); }
    @FunctionalInterface interface Function8<A, B, C, D, E, F, G, H, R> { R apply(A a, B b, C c, D d, E e, F f, G g, H h); }

    private static List<SalesError> collectErrors(Validated<?>... validations) {
        var errors = new ArrayList<SalesError>();
        for (var v : validations) {
            if (v instanceof Invalid<?> inv) errors.addAll(inv.errors());
        }
        return Collections.unmodifiableList(errors);
    }

    @SuppressWarnings("unchecked")
    private static <T> T value(Validated<T> v) {
        return ((Valid<T>) v).value();
    }

    static <A, B, R> Validated<R> combine(
            Validated<A> va, Validated<B> vb,
            BiFunction<A, B, R> f) {
        var errors = collectErrors(va, vb);
        if (!errors.isEmpty()) return new Invalid<>(errors);
        return new Valid<>(f.apply(value(va), value(vb)));
    }

    static <A, B, C, R> Validated<R> combine(
            Validated<A> va, Validated<B> vb, Validated<C> vc,
            Function3<A, B, C, R> f) {
        var errors = collectErrors(va, vb, vc);
        if (!errors.isEmpty()) return new Invalid<>(errors);
        return new Valid<>(f.apply(value(va), value(vb), value(vc)));
    }

    static <A, B, C, D, R> Validated<R> combine(
            Validated<A> va, Validated<B> vb, Validated<C> vc, Validated<D> vd,
            Function4<A, B, C, D, R> f) {
        var errors = collectErrors(va, vb, vc, vd);
        if (!errors.isEmpty()) return new Invalid<>(errors);
        return new Valid<>(f.apply(value(va), value(vb), value(vc), value(vd)));
    }

    static <A, B, C, D, E, R> Validated<R> combine(
            Validated<A> va, Validated<B> vb, Validated<C> vc, Validated<D> vd,
            Validated<E> ve,
            Function5<A, B, C, D, E, R> f) {
        var errors = collectErrors(va, vb, vc, vd, ve);
        if (!errors.isEmpty()) return new Invalid<>(errors);
        return new Valid<>(f.apply(value(va), value(vb), value(vc), value(vd), value(ve)));
    }

    static <A, B, C, D, E, F, R> Validated<R> combine(
            Validated<A> va, Validated<B> vb, Validated<C> vc, Validated<D> vd,
            Validated<E> ve, Validated<F> vf,
            Function6<A, B, C, D, E, F, R> f) {
        var errors = collectErrors(va, vb, vc, vd, ve, vf);
        if (!errors.isEmpty()) return new Invalid<>(errors);
        return new Valid<>(f.apply(value(va), value(vb), value(vc), value(vd), value(ve), value(vf)));
    }

    static <A, B, C, D, E, F, G, R> Validated<R> combine(
            Validated<A> va, Validated<B> vb, Validated<C> vc, Validated<D> vd,
            Validated<E> ve, Validated<F> vf, Validated<G> vg,
            Function7<A, B, C, D, E, F, G, R> f) {
        var errors = collectErrors(va, vb, vc, vd, ve, vf, vg);
        if (!errors.isEmpty()) return new Invalid<>(errors);
        return new Valid<>(f.apply(value(va), value(vb), value(vc), value(vd), value(ve), value(vf), value(vg)));
    }

    static <A, B, C, D, E, F, G, H, R> Validated<R> combine(
            Validated<A> va, Validated<B> vb, Validated<C> vc, Validated<D> vd,
            Validated<E> ve, Validated<F> vf, Validated<G> vg, Validated<H> vh,
            Function8<A, B, C, D, E, F, G, H, R> f) {
        var errors = collectErrors(va, vb, vc, vd, ve, vf, vg, vh);
        if (!errors.isEmpty()) return new Invalid<>(errors);
        return new Valid<>(f.apply(value(va), value(vb), value(vc), value(vd), value(ve), value(vf), value(vg), value(vh)));
    }
}
