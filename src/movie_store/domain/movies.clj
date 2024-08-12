(ns movie-store.domain.movies
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]))

(def mysql-db {
               :host "localhost"
               :dbtype "mysql"
               :dbname "clojure_movie-store"
               :user "root"
               :password ""
               })

(defn allMovies []
  (jdbc/query mysql-db ["select * from movies m"]))

(defn countMovies []
  (let [result (jdbc/query mysql-db ["select count(*) from movies"])]
    (-> result
        first
        vals
        first)))

(defn avgPrice []
  (let [result (jdbc/query mysql-db ["select avg(price) from movies"])]
    (-> result
        first
        vals
        first)))