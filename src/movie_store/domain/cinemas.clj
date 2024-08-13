(ns movie-store.domain.cinemas
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

(defn cinema []
  (jdbc/query mysql-db ["select * from cinemas c"]))

(defn getCinema [id]
  (first (jdbc/query mysql-db ["select * from cinemas where id = ?" id])))

(defn updateCinema [id params]
  (jdbc/update! mysql-db :cinemas params (sql/where {:id id})))

(defn removeCinema [id]
  (jdbc/execute! mysql-db ["delete from cinemas where id = ?" id]))

(defn insertCinema [params]
  (jdbc/insert! mysql-db :cinemas params))

(defn statisticCinema []
  (let [result (jdbc/query mysql-db ["select count(*) from cinemas"])]
    (-> result
        first
        vals
        first)))