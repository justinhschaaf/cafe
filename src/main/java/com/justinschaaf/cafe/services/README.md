# Services

Java 9's module system introduced the concept of Services. Essentially, you
could have a module with an interface defining how to interact with a service
along with a separate module providing the actual implementation for said
service. This could allow users to pick exactly which implementation of a
service they would like to use while developers can still interact with the
implementations the same way due to the shared service api. You can read more
about Java 9 modules and services on [Jenkov](https://jenkov.com/tutorials/java/modules.html#services).

This class is meant to help with finding a preferred service implementation.
Likely written around 2021.
