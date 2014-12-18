(ns duct.component.hikaricp
  (:require [com.stuartsierra.component :as component])
  (:import [com.zaxxer.hikari HikariDataSource]))

(defn- make-datasource [{:keys [uri]}]
  (doto (HikariDataSource.)
    (.setJdbcUrl uri)))

(defrecord HikariCP [uri]
  component/Lifecycle
  (start [component]
    (if (:spec component)
      component
      (assoc component :spec {:datasource (make-datasource component)})))
  (stop [component]
    (if-let [spec (:spec component)]
      (do (.close (:datasource component))
          (dissoc component :spec))
      component)))
