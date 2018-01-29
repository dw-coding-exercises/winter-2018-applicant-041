(ns my-exercise.search

  (:require [hiccup.page :refer [html5]]
            [clj-http.client :as client]))

(defn header [_]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0, maximum-scale=1.0"}]
   [:title "Find my next election"]
   [:link {:rel "stylesheet" :href "default.css"}]])

;replace spaces with underscore and make lower-cased
(defn nocaps-nospaces [input]
    (str (clojure.string/replace (clojure.string/lower-case input) #" " "_")))

;Decided to only bring in the city and state params for simplicity's sake
(defn search-method [request]
  (let [city (get (:params request) :city)
        state (get (:params request) :state)
        state-ocd-id (str "ocd-division/country:us/state:" (clojure.string/lower-case state))
        place-ocd-id (str state-ocd-id "/place:" (nocaps-nospaces city))
        api-uri (str "https://api.turbovote.org/elections/upcoming?district-divisions=" state-ocd-id "," place-ocd-id)]
    (client/get api-uri)))

(defn search [request]
  (html5
    (header request)
    (search-method request)))
