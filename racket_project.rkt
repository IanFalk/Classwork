;; Author: Ian Falk
;; Date: 23 February 2022
;; Description:
;; A program consisting of 10+ functions to complete
;; the assigned programming project
;; ==============================================================
#lang Racket
(provide (all-defined-out))

;; Question 1
(define (divisible-by-x? x)
  (lambda (y)
    (= (remainder y x) 0)))                  ;;Returns t or f if the remainder=0.

;; Question 2
(define function-4
  (lambda (x)
  (x 4)))                                    ;;Set x=4

;; Question 3
(define my-map                               ;; Two arguments: the function and the list
  (lambda (func lst)
    (if (empty? lst)
        empty
        (cons (func (first lst))             ;; Inserts new value to front of list
              (my-map func (rest lst))))))   ;; Recursively calls my-map with remaining list elements

;; Question 4
(define pair-up
  (lambda (lst1 lst2)
  (if (or (null? lst1) (null? lst2))                                      ;; If either list is null, return an empty list
   '()
      (cons (list (car lst1) (car lst2))                                  ;; Creates new pair of the first items in each list
            (pair-up (cdr lst1) (cdr lst2))))))                           ;; Recursively calls pair-up with the remaining list elements

;; Question 5
(define classify
  (lambda (func lst)
    (cons (helper-left func lst) (cons (helper-right func lst) '() ))))   ;; Adds right sublist to an empty list, then adds left sublist to same list. Resulting in '( (left) (right) )

;; Question 5 helper function
(define (helper-right func lst)                                           ;; Returns a list of elements evaluated as false
  (if (null? lst)
      '()                                                                 ;; Returns an empty list when recursion reaches the bottom
      (if (func (car lst))
          (helper-right func (cdr lst))                                   ;; Ignores current element (evaluates true) and calls method with next element
          (cons (car lst) (helper-right func (cdr lst))))))               ;; Adds the current element to the list and calls the method with next element

;; Question 5 helper function
(define (helper-left func lst)                                            ;; Returns a list of elements evaluated as true
  (if (null? lst)
      '()                                                                 ;; Returns an empty list when recursion reaches the bottom
      (if (func (car lst))
          (cons (car lst) (helper-left func (cdr lst)))                   ;; Adds the current element to the list and calls the method with next element
          (helper-left func (cdr lst)))))                                 ;; Ignores current element (evaluates false) and calls method with next element

;; Question 6
(define is-member?
  (lambda (expr lst)
    (if (null? lst)                                                       ;; Base case, if it reaches end of list (null) then the element is not in the list and returns false
        #f
        (if (equal? expr (car lst))                                       ;; Check if the first element in the list is equal to what we are searching for
            #t
            (is-member? expr (cdr lst))))))                               ;; If the element is not what we are looking for, recursive call with the remaining elements

;; Question 7
(define is-sorted?
  (lambda (expr lst)
    (if (null? lst)                                                       ;; Ensures list is not empty, and if it is returns true
        #t
        (if (null? (cdr lst))                                             ;; Checks if this is the last element of the list
            #t                                                            ;; If the method has reached last element without faulting, then the list is sorted
            (if (expr (car lst) (cadr lst))                               ;; Compare with <expr> the first and second elements in the list
                (is-sorted? expr (cdr lst))                               ;; If true, recursively call is-sorted? with the next element in the list
                #f)))))                                                   ;; If false, the list is not sorted.

;; Question 8
(define my-flatten
  (lambda (lst)
    (if (null? lst)
        '()                                                               ;; Empty list base case
        (if (pair? (car lst))                                             ;; If the first element is a pair (aka list)
            (append (my-flatten (car lst)) (my-flatten (cdr lst)))        ;; Recursively flatten the list, and add the result to the end of the current list
            (cons (car lst) (my-flatten (cdr lst)))))))                   ;; If the element wasn't a pair, just add the element to the list and recursive call with next element.

;; Question 9
(define upper-threshold
  (lambda (lst thrsh)
    (if (null? lst)
        '()
        (if (< (car lst) thrsh)                                           ;; Checks if the first element in the list is less than threshold
            (cons (car lst) (upper-threshold (cdr lst) thrsh))            ;; If element is less, then add it to new list and recursive call
            (upper-threshold (cdr lst) thrsh)))))                         ;; If element is greater, ignore current element and recursive call

;; Question 10
(define my-list-ref
  (lambda (lst int)
    (if (null? lst)
        (error "ERROR: Index out of bounds.")                             ;; If the provided index is out of bounds, throw an error
        (if (= int 0)                                                     ;; If we are at the specified index, then int should be 0.
            (car lst)                                                     ;; If at index, return the element there. 
            (my-list-ref (cdr lst) (- int 1))))))                         ;; Recursively call on remaining sublist while subtracting 1 from index. 