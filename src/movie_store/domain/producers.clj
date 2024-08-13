(ns movie-store.domain.producers
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]
            [clojure.java.jdbc.sql :as sql]))

(def mysql-db {
               :host "localhost"
               :dbtype "mysql"
               :dbname "clojure_movie-store"
               :user "root"
               :password ""
               })

(defn producers []
  (jdbc/query mysql-db ["select * from producers p"]))

(defn getProducers [id]
  (first (jdbc/query mysql-db ["select * from producers where id = ?" id])))

(defn updateProducer [id params]
  (jdbc/update! mysql-db :producers params (sql/where {:id id})))

(defn removeProducer [id]
  (jdbc/execute! mysql-db ["delete from producers where id = ?" id]))

(defn insertProducer [params]
  (jdbc/insert! mysql-db :producers params))

(defn statisticProducer []
  (let [result (jdbc/query mysql-db ["select count(*) from producers"])]
    (-> result
        first
        vals
        first)))