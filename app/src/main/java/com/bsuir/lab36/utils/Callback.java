package com.bsuir.lab36.utils;

/**
 * The Callback interface is designed to allow for a common, reusable interface
 * to exist for defining APIs that requires a call back in certain situations.
 * <p>
 * Callback is defined with two generic parameters: the first parameter
 * specifies the type of the object passed in to the <code>call</code> method,
 * with the second parameter specifying the return type of the method.
 *
 * @param <P> The type of the argument provided to the <code>call</code> method.
 * @since JavaFX 2.0
 */
@FunctionalInterface
public interface Callback<P> {
    /**
     * The <code>call</code> method is called when required, and is given a
     * single argument of type P, with a requirement that an object of type R
     * is returned.
     *
     * @param param The single argument upon which the returned value should be
     *              determined.
     *              parameter value.
     */
    public void call(P param);
}