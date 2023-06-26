package org.mfrf.micro_machienry.interfaces;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {
    R func(A a, B b, C c);
}
