# Duct-HikariCP-Component

A [component][] for the [HikariCP][] JDBC connection pool, designed to be used
in the [Duct][] framework.

[component]: https://github.com/stuartsierra/component
[hikaricp]:  https://github.com/ring-clojure/ring
[duct]:      https://github.com/weavejester/duct

## Installation

Add the following dependency to your `project.clj`:

    [duct/hikaricp-component "0.1.0-SNAPSHOT"]

## Usage

Require the library, and the Component library:

```clojure
(require '[duct.component.hikaricp :refer [hikaricp]]
         '[com.stuartsierra.component :as component])
```

Create a new HikariCP connection pool component with a JDBC database URI:

```clojure
(def pool (hikaricp {:uri "jdbc:h2:mem:test"}))
```

Then start it to create the pool:

```clojure
(alter-var-root #'pool component/start)
```

Starting the pool will create a `:spec` key that contains a map compatible with
the Clojure [java.jdbc][] library.

As with all components, the return value matters, and you are responsible for
stopping the component when it's done. Ideally this component should be used
within a larger system.

For more information, see the documentation for the [Component][] library.

[java.jdbc]: https://github.com/clojure/java.jdbc

## License

Copyright © 2014 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
