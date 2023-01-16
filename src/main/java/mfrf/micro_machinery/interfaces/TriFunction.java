package mfrf.dbydd.micro_machinery.interfaces;

@FunctionalInterface
public interface TriFunction<A, B, C, R> {
    R func(A a, B b, C c);
}
