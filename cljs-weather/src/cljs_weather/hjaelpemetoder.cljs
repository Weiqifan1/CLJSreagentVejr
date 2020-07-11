(ns cljs-weather.hjaelpemetoder)
;;start


(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn parse-int
  "Reads a number from a string. Returns nil if not a number."
  [s]
  (if (re-find #"^\d+$" s);(if (re-find #"^-?\d+\.?\d*$" s)
    (js/parseInt s);;(. parse-int s);;(read-string s)
    ))

(defn filter-by-index [coll idxs]
  (keep-indexed #(when ((set idxs) %1) %2)
                coll))

;this shuldnt be relevant since CLJS only has numbers,
;not ints and Longs
;(defn parse-long [s]
;  (Long. (re-find #"\d+" s)))


(defn splitToCodepointChar
  ;"A腦𡳞𨨏B乃匯C𨭎" [A 腦 𡳞 𨨏 B 乃 匯 C 𨭎]
  [kinstring]
  ((comp
     #(clojure.string/split % #" ")
     #(clojure.string/join "" %)
     (fn [x] (map
               #(if
                  (and (>= (int %) 55296) (<= (int %) 56319))
                  % (str % " ")) x))
     ) kinstring))


(defn splitToSurogatePair
  ;"A腦𡳞𨨏B乃匯C𨭎" ((65) (33126) (55367 56542) (55394 56847) (66) (20035) (21295) (67) (55394 57166))
  [kinstring]
  ((comp
     (fn [x] (map #(map int %) x))
     #(splitToCodepointChar %)
     ) kinstring))

(defn splitToCodepointVec
  ;"A腦𡳞𨨏B乃匯C𨭎" (65 33126 138462 166415 66 20035 21295 67 166734)
  [nested]
  (map
    #(if
       (and (>= (first (map int %)) 55296) (<= (first (map int %)) 56319))
       ;;(. java.lang.Character toCodePoint (first %) (second %))
       999
       (first (map int %)))
    (splitToCodepointChar
      nested)))

(defn splitToCodepointVec2
  ;"A腦𡳞𨨏B乃匯C𨭎" (65 33126 138462 166415 66 20035 21295 67 166734)
  [nested]
  (concat "hello "  nested)
  )

;;2020-07-11 opgave
;; alle funktioner i hjaelpemetoder skal gaas efter.
;; det er meget svaert at faa dem til at
;;virke i javascript, da
;; javascript ikke bruger utf-16

;;slut