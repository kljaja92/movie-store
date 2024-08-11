(ns movie-store.domain.actors
  (:refer-clojure :exclude [get])
  (:require [clojure.java.jdbc :as jdbc]))

(def mysql-db {
               :host "localhost"
               :dbtype "mysql"
               :dbname "clojure_movie-store"
               :user "root"
               :password ""
               })

(defn actor []
  jdbc/query mysql-db ["select * from actors a"])



