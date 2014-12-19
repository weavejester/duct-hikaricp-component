(ns duct.component.hikaricp-test
  (:require [clojure.test :refer :all]
            [clojure.java.jdbc :as jdbc]
            [com.stuartsierra.component :as component]
            [duct.component.hikaricp :refer :all]))

(deftest test-hikaricp
  (testing "initialized component"
    (let [pool (hikaricp {:uri "jdbc:h2:mem:test_db"})]
      (is (nil? (:spec pool)))))

  (testing "start component"
    (let [pool (component/start (hikaricp {:uri "jdbc:h2:mem:test_db"}))]
      (try
        (is (some? (:spec pool)))
        (is (some? (:datasource (:spec pool))))
        (finally (component/stop pool)))))

  (testing "stop component"
    (let [pool (-> (hikaricp {:uri "jdbc:h2:mem:test_db"})
                 (component/start)
                 (component/stop))]
      (is (nil? (:spec pool)))))

  (testing "JDBC connection"
    (let [pool (component/start (hikaricp {:uri "jdbc:h2:mem:test_db"}))
          spec (:spec pool)]
      (try
        (jdbc/execute! spec ["DROP ALL OBJECTS"])
        (jdbc/execute! spec ["CREATE TABLE testing (name varchar(100))"])
        (jdbc/insert! spec :testing {:name "hello"})
        (is (= (jdbc/query spec ["SELECT * FROM testing"])
               [{:name "hello"}]))
        (finally (component/stop pool))))))
