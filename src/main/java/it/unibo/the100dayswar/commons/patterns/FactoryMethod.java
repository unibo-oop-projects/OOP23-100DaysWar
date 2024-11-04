package it.unibo.the100dayswar.commons.patterns;

public interface FactoryMethod<P, T> {
    T create(P param);
}
