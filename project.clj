(defproject duct/hikaricp-component "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.stuartsierra/component "0.2.2"]
                 [com.zaxxer/HikariCP "2.2.5"]]
  :profiles
  {:dev {:dependencies [[org.clojure/java.jdbc "0.3.6"]
                        [com.h2database/h2 "1.4.183"]]}})
