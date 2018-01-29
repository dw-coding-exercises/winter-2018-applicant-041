(ns my-exercise.search-test
  (:require [clojure.test :refer :all]
            [my-exercise.search :refer :all]))

(deftest nocaps-nospaces-test
  (testing "a string should be converted all lowercase and no spaces"
    (is (= "north_hollywood"
           (str (nocaps-nospaces "North Hollywood"))))))