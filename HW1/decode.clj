; *********************************************
; *  341 Programming Languages                *
; *  Fall 2017                                *
; *  Author: Yakup Genc                       *
; *********************************************

;; utility functions


;**********************************************
;* Yapılan ödevde verilen şifreli metni çözmek*
;* için shuffle kullanılarak 4-5 harfli bir   *
;* alphabet için doğru vermektedir faktöriyel *
;* hesaplandığında diğer alphabet sayısı düşü-*
;* nülürse oldukça uzun zaman alıcaktır.      *
;**********************************************

;################ BEDİRHAN ÇAĞLAR #############
;################    141044073    #############
;################  13. 10. 2017   #############


(load-file "include.clj") ;; "c2i and "i2c"


(use 'clojure.java.io)
(use 'clojure.core)
(require '[clojure.string :as str])

(defn read-as-list
	"Reads a file containing one word per line and returns a list of words (each word is in turn a list of characters)."
	[filename]
	(def myfile (slurp (str filename ".txt")))

	(def mylist (str/split myfile #"\s+") )

	(def mywords (apply list mylist))
	(def size (count mywords))


	(def mycharacters [])
	(def sonlist ())

	(loop [x 0]
		(when (< x size )
			(def mycharacters  (conj mycharacters   (nth mywords x) ))
			(recur (+ x 1))))


	(loop [x (- size 1 ) ]
		(when (>= x 0 )
			(def sonlist  (conj sonlist  (seq (nth mycharacters x) )))
			(recur (- x 1))))


	sonlist

	; Implement this function...
	;'((a b c) (d e f))
	)

;; -----------------------------------------------------
;; HELPERS
;; *** PLACE YOUR HELPER FUNCTIONS BELOW ***



(defn spell-checker-0
	[word]


	(def mydictionary (read-as-list "dictionary1") )
	(def size (count mydictionary))

	(def control false )

	(loop [x (- size 1 ) ]
		(when (and (>= x 0 ) (= control false) )
			(if (=  (apply str (nth mydictionary x)) word) (def control true))
			(recur (- x 1))))


	control

	)


(defn spell-checker-1
	[word]

	(def mydictionary (read-as-list "dictionary1") )

	(def size (count mydictionary))


	(def myvector [])

	(loop [x 0]
		(when (< x size )
			(def myvector (conj myvector (apply str  (nth mydictionary x) )))
			(recur (+ x 1))))

	(def flag (.indexOf myvector word))

	(if (< flag 0) ( def control false) ( def control true))

	control
	;you should implement this function


	)

;Bu fonksiyon verilen kelimeleri döngüyle shuffle kurarak teker teker deniyerek doğru şifrelemeyi bulana kadar kelimeleri
;spell-checker-1 ile şifrelemeden çözülen kelimeyi dictionaryde aratıyor,documandaki kelime adedi kadar true saydığında
;verilen shuffle ile documanı çözmüş oluyor.
(defn myhelper
	[words]

	(def alphabet [\a \e \r \t \c] )



	(def myparagraf (str/split words #"\s+"))

	(def size_p (count myparagraf))


	(def bayrak true)
	(def y 0)


	(def saydir 0)


	(while (= bayrak true)

		(def alphmix (shuffle alphabet))
		(def mymap (zipmap alphabet alphmix))
		(def mychar "")
		(def mychar1 "")
		(def decoder "")
		(while (< y size_p)

			(def size (count (nth myparagraf y )))

			(loop [x 0]
				(when (< x size )
					(def mychar (str mychar (get mymap (nth (nth myparagraf y) x))))
					(recur (+ x 1))))

			(def control (spell-checker-1 mychar))
			(if (= control false)  (def y (+ y size_p) ) (def saydir (+ saydir 1)))

			(def decoder (str decoder  mychar " "))
			(def y (+ y 1) )
			(def mychar "")

			)
		(if (= saydir size_p) (def bayrak false) (do (def bayrak true) (def y 0) (def saydir 0)))

		)

	decoder
	)


;; -----------------------------------------------------
;; DECODE FUNCTIONS



;fonksiyon return eden Gen-Decoder-A fonksiyonu.
(defn Gen-Decoder-A
	[paragraph]

	myhelper

	)

(defn Gen-Decoder-B-0
	[paragraph]
	;you should implement this function
	)

(defn Gen-Decoder-B-1
	[paragraph]
	;you should implement this function
	)


(defn Code-Breaker
	[document decoder]

	(def myparagraph (slurp document ))


(def cozulmus	( (decoder myparagraph)myparagraph))

	cozulmus
	)

;; -----------------------------------------------------
;; Test code...

(defn test_on_test_data
	[]

	(let [doc (read-as-list "document1")]
		(println doc)
		)

	(println)

	(let [control (Code-Breaker "document1.txt" Gen-Decoder-A)]
		(println "cozülmüs metin : " control)
		)

	)



;; test code...
(test_on_test_data)