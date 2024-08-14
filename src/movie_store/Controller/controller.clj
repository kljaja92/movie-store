(ns movie-store.Controller.controller
  (:require [clostache.parser :as clostache]
            [movie-store.domain.actors :as actors-domain]
            [movie-store.domain.movies :as movies-domain]
            [movie-store.domain.producers :as producers-domain]
            [movie-store.domain.cinemas :as cinemas-domain]))

(defn read-template [template-name]
  (slurp (clojure.java.io/resource
           (str "pages/" template-name ".mustache"))))

(defn render-template [template-file params]
  (clostache/render (read-template template-file) params))

(defn index []
  (render-template "index" {}))

(defn AllMovies []
  (render-template "movie" {:movies (movies-domain/allMovies)}))

(defn Actor []
  (render-template "actor" {:actors (actors-domain/actor)}))

(defn Edit [id]
  (render-template "editActor" {:actors (actors-domain/get id)}))

(defn Producer []
  (render-template "producer" {:producers (producers-domain/producers)}))

(defn EditProducer [id]
  (render-template "editProducer" {:producers (producers-domain/getProducers id)}))

(defn Cinema []
  (render-template "cinema" {:cinemas (cinemas-domain/cinema)}))

(defn EditCinema [id]
  (render-template "editCinema" {:cinemas (cinemas-domain/getCinema id)}))