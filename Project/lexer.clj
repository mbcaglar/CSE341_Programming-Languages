;################ BEDÝRHAN ÇAÐLAR #############
;################    141044073    #############
;################  18. 11. 2017   #############


;**********************************************
;* Bu projede verilen filedaki stringi coffe  *
;* diline göre tokenlarýna ayýrýr.            *
;* Lexical analys iþlemini gerçekletirerek    *
;* tokenlarýna ayrýlýyorsa token listesini    *
;* ayrýlamayan ifade varsa false return eder. *
;**********************************************


(use 'clojure.java.io)
(use 'clojure.core)
(require '[clojure.string :as str])

(defn id_mi [name]

	(def durum true)
	(def size_name (count name))
	(def deger 0)

	(def p 0)

	(while (< p size_name)
		(def deger (int (get name p)))

		(if (or (and (>= deger 65) (<= deger 90)) (and (>= deger 97) (<= deger 122))) (def durum true) (do (def durum false) (def p (+ p size_name))))
		(def p (+ p 1))
		)

	durum
	)


(defn Number_mi [str]
	 (let [n (read-string str)] (if (number? n) true false))

	)



(defn read-as-list
	"Reads a file containing one word per line and returns a list of words (each word is in turn a list of characters)."
	[filename]

	(def myfile (slurp (str filename ".coffee")))



	(def size (count myfile))
	(def myfile_son "")
	(def coffe_keyword '(	"and", "or", "not" , "equal" , "append" ,"concat" , "set", "deffun","for", "while" ,"if" , "then" , "else" , "true" , "false" ))
	(def coffe_operator '(	"(", ")", "+" , "-" , "*" ,"/" ))
	(def total '() )
	(def total (conj total coffe_keyword ))
	(def total (conj total coffe_operator))

	(loop [x 0]
		(when (< x size )
			(if (or  (= (get myfile x) \() (= (get myfile x) \))) (def myfile_son  (str myfile_son  " " )))
			(def myfile_son  (str myfile_son  (get myfile x) ))
			(if (or  (= (get myfile x) \() (= (get myfile x) \))) (def myfile_son  (str myfile_son  " " )))
			(recur (+ x 1))))

	(def mylist (str/split myfile_son #" +") )

	(def mywords (apply list mylist))

	(def size (count mywords))
	(def size_total (count total))

	(def bayrak false)
	(def flag_int false)
	(def flag false)
	(def sonuc true)
	(def x 0)
	(def token_type [])
	(while (< x size )

		(def i 0)
		(while (< i  size_total)

			(def control (.indexOf (nth total i)  (nth mywords x)))
			(if (>= control 0) (do (def bayrak true) (if (= i 0) (def token_type (conj token_type (str "(" (nth mywords x) ")" ":Operator" )))
																													 (def token_type (conj token_type (str "(" (nth mywords x) ")" ":Keyword" ))) ) (def i (+ i (count total))) ) (def bayrak false) )
			(def i (+ i 1))
			)

		(if (= bayrak false) (do (def flag (Number_mi (nth mywords x)))
														 (if (= flag true) (def flag_int (integer? (read-string (nth mywords x)))) (def flag_int false))
														 ) )

		(if (and (= bayrak false) (= flag_int true)) (def token_type (conj token_type (str "(" (nth mywords x) ")" ":Integer" ))))

		(if (and (= bayrak false) (= flag_int false)) (do (def sonuc (id_mi (nth mywords x))) (if (= sonuc true) (def token_type (conj token_type (str "(" (nth mywords x) ")" ":Identifier" ))))) )

		(if (and (= bayrak false) (= sonuc false)) (do (println "The symbol (" (nth mywords x) ") cant solve as lexical.That is error!!!") (def x (+ x size)) ) )

		(def x (+ x 1))

		)
	(def token_type (apply list token_type))

	(if (= sonuc false) false token_type)

	)


;; Test code...

(defn test_on_test_data
	[]
	(println "Please enter a filename :")
(def filename (read-line))
	(try
	(let [doc (read-as-list filename)]
		(println doc)
		)
	(catch java.io.FileNotFoundException e (println (str "File exception: " (.getMessage e) "\nfalse")))
	(catch Exception e (println (str "Caught exception: " (.getMessage e ) "\nThe symbol cant solve as lexical.That is error!!!\nfalse")))
	)

	)

;; test code...
(test_on_test_data)