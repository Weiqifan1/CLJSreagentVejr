(ns cljs-weather.core
    (:require 
              [reagent.core :as reagent :refer [atom]]
              [reagent.dom :as rd]
              [cljs-weather.input-system-data :as input-system-data]
              [cljs-weather.hjaelpemetoder :as hjaelpemetoder]
              ))

(enable-console-print!)

;;loade filer
;;(load-file "input-system-data.cljs")
;;loade filer slut

(println "This text is printed from src/cljs-weather/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"
                          :postal-code ""}))

(defn postal-code []
  [:div {:class-name "postal-code"}
   [:h3 "Enter your postal code"]
   [:input {:type "text"
            :placeholder "Postal Code"
            :value (:postal-code @app-state)
            :on-change #(swap! app-state assoc :postal-code (-> % .-target .-value))}]
   [:button "Go"]])




(defn hello-world []
  [:div
   [:h1 (:text @app-state)]
   [:h3 "her er noget andet dfgdfg igen"]  ;;my-var   [:h3 "Chr"]
   [postal-code]
   [:h4 (:postal-code @app-state)]
   [:h4 input-system-data/my-var]
   [:h4 (.toUpperCase "dfg")];;(hjaelpemetoder/parse-int "34")
   [:h4 (.toString (+ 7 9))];;(.parseInt "5")
   [:h4 (js/parseInt "3")]
   [:h4 (hjaelpemetoder/parse-int "4")]
   ;;2020-07-11 opgave: faa parseInt til at virke
   ;;[:h4 ]
   ;;[:h4 (.parse-int "4")];(hjaelpemetoder/parse-int "34"))]
   [hjaelpemetoder/splitToCodepointVec2 "A腦\uD847\uDCDE\uD862\uDE0FB乃匯C\uD862\uDF4E"]
   ])
;;hjaelpemetoder.splitToCodepointVec

(rd/render [hello-world]
           (. js/document (getElementById "app")))

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)

  ;; 2020-07-11
  ;;


)
