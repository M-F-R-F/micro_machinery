package mfrf.micro_machinery.utils;

import java.util.Objects;
import java.util.function.Supplier;

public class TriFields<A, B, C> {
    public final Supplier<A> a;
    public final Supplier<B> b;
    public final Supplier<C> c;

    public TriFields(Supplier<A> a, Supplier<B> b, Supplier<C> c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static <A, B, C> TriFields<A, B, C> of(Supplier<A> a, Supplier<B> b, Supplier<C> c) {
        return new TriFields<>(a, b, c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TriFields<?, ?, ?> triFields = (TriFields<?, ?, ?>) o;
        return Objects.equals(a, triFields.a) && Objects.equals(b, triFields.b) && Objects.equals(c, triFields.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return "TriFields{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
