(ns movie-store.domain.actors
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

(defn actor []
  jdbc/query mysql-db ["select * from actors a"])

(defn get [id]
  (first (jdbc/query mysql-db ["select * from actors where id = ?" id])))

(defn updateActors [id actors]
  (jdbc/update! mysql-db :actors actors (sql/where {:id id})))

(defn removeActor [id]
  (jdbc/execute! mysql-db ["delete from actors where id = ?" id]))

(defn insertActor [params]
  (jdbc/insert! mysql-db :actors params))

(defn countActors []
  (let [result (jdbc/query mysql-db ["select count(*) from actors"])]
    (-> result
        first
        vals
        first)))




