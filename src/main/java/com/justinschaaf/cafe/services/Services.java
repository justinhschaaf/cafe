/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package com.justinschaaf.cafe.services;

import java.util.ServiceLoader;

/**
 * Java 9's module system introduced the concept of Services. Essentially, you
 * could have a module with an interface defining how to interact with a service
 * along with a separate module providing the actual implementation for said
 * service. This could allow users to pick exactly which implementation of a
 * service they would like to use while developers can still interact with the
 * implementations the same way due to the shared service api. You can read more
 * about Java 9 modules and services on <a href="https://jenkov.com/tutorials/java/modules.html#services">Jenkov</a>.
 * <p>
 * This class is meant to help with finding a preferred service implementation.
 * Likely written around 2021.
 *
 * @author justinhschaaf
 */
// Final because you should never need to extend this
public final class Services {

    /**
     * Finds the first implementation of a given service class and returns it.
     *
     * @param tClass The class of the service to find the implementation of
     * @param <T> The service type
     * @return The first loaded implementation of the given service
     */
    public static <T> T find(Class<T> tClass) {
        return find(tClass, (String) null);
    }

    /**
     * Finds the desired implementation of the given service and returns it.
     * If the desired implementation is not found, returns the first loaded
     * implementation.
     *
     * @param tClass The class of the service to find the implementation of
     * @param preferred The name of the preferred implementation class, e.g.
     *                  "com.example.impl.MyImpl"
     * @param <T> The service type
     * @return The desired implementation if loaded, else the first loaded
     *         implementation of the given service
     */
    public static <T> T find(Class<T> tClass, String preferred) {
        return find(tClass, preferred, Thread.currentThread().getContextClassLoader());
    }

    /**
     * Finds the first implemtation of a given service on the given
     * {@link ClassLoader} and returns it.
     *
     * @param tClass The class of the service to find the implementation of
     * @param cl The {@link ClassLoader} to load the service implementation from
     * @param <T> The service type
     * @return The first implementation of the given service from the given
     *         ClassLoader
     */
    public static <T> T find(Class<T> tClass, ClassLoader cl) {
        return find(tClass, null, cl);
    }

    /**
     * Finds the desired implementation of the given service on the given
     * {@link ClassLoader} and returns it.
     *
     * @param tClass The class of the service to find the implementation of
     * @param preferred The name of the preferred implementation class, e.g.
     *                  "com.example.impl.MyImpl". Will simply return the first
     *                  found implementation if this is null.
     * @param cl The {@link ClassLoader} to load the service implementation from
     * @param <T> The service type
     * @return The found implementation
     */
    public static <T> T find(Class<T> tClass, String preferred, ClassLoader cl) {

        if (tClass == null) throw new NullPointerException("Attempting to find a service for null!");

        ServiceLoader<T> sl = ServiceLoader.load(tClass, cl);

        if (preferred != null && !preferred.isBlank())
            for (T s : sl)
                if (s.getClass().getName().equals(preferred)) return s;

        return sl.findFirst().orElseThrow();

    }

}
