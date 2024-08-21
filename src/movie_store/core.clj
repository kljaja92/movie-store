(ns movie-store.core
  (:require [compojure.core :refer [defroutes GET POST]]
            [compojure.route :as route]
            [ring.adapter.jetty :refer [run-jetty] :as jetty]
            [compojure.handler :as handler]
            [movie-store.Controller.controller :as controller]
            [movie-store.domain.actors :as actors-domain]
            [movie-store.domain.producers :as producers-domain]
            [movie-store.domain.cinemas :as cinemas-domain]
            [ring.util.response :as resp]))

(defroutes public-routes
           (GET "/index" [] (controller/index)) (route/resources "/")
           (GET "/movies" [] (controller/AllMovies)) (route/resources "/")
           (GET "/actor" [] (controller/Actor)) (route/resources "/")
           (GET "/producer" [] (controller/Producer)) (route/resources "/")
           (GET "/cinema" [] (controller/Cinema)) (route/resources "/")
           (GET "/edit" [] (controller/Edit [])) (route/resources "/")
           ;Actors
           (GET "/domain/actors/:id/update" [id] (controller/Edit id))
           (POST "/domain/:id/update" [& params]
             (do (actors-domain/updateActors (:id params) params) (resp/redirect "/actor")))
           (GET "/domain/actors/:id/remove" [id]
             (do (actors-domain/removeActor id)
                 (resp/redirect "/actor")))
           (POST "/domain/actors/insert" [& params]
             (do (actors-domain/insertActor params)
                 (resp/redirect "/actor")))
           ;Producers
           (GET "domain/producers/:id/update" [id]
             (controller/EditProducer id))
           (POST "domain/producers/:id/update" [& params]
             (do (producers-domain/updateProducer (:id params) params)
                 (resp/redirect "/producer")))
           (GET "domain/producers/:id/remove" [id]
             (do (producers-domain/removeProducer id)
                 (resp/redirect "/producer")))
           (POST "domain/producers/insert" [& params]
             (do (producers-domain/insertProducer params)
                 (resp/redirect "/producer")))
           ;Cinemas
           (GET "domain/cinemas/:id/update" [id]
             (controller/EditCinema id))
           (POST "domain/cinemas/:id/update" [& params]
             (do (cinemas-domain/updateCinema (:id params) params)
                 (resp/redirect "/cinema")))
           (GET "domain/cinemas/:id/remove" [id]
             (do (cinemas-domain/removeCinema id)
                 (resp/redirect "/cinema")))
           (POST "domain/cinemas/insert" [& params]
             (do (cinemas-domain/insertCinema params)
                 (resp/redirect "/cinema")))
           )
(defroutes app-routes
           public-routes
           (route/not-found "404. Page not found."))
(def app
  (handler/site app-routes))
(jetty/run-jetty (fn [req] (public-routes req)) {:port 3306 :join? false})