(ns movie-store.core-test
  (:require [clojure.test :refer :all]
            [movie-store.core :refer :all]
            [movie-store.domain.actors :refer :all]
            [movie-store.domain.producers :refer :all]
            [movie-store.domain.cinemas :refer :all]
            [midje.sweet :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))