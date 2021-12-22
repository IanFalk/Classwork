This project is designed to act as a rewards system for customers buying drinks from the business.

There are three types of customers:  
&nbsp;&nbsp;&nbsp;&nbsp; Regular Customer: A plain old customer, nothing special.  

&nbsp;&nbsp;&nbsp;&nbsp; Gold Customer: A customer who has spent more than $50 at the business. Receives a discount based on total money spent at the business.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $50-99:   5% discount  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $100-149: 10% discount  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $150-199: 15% discount

&nbsp;&nbsp;&nbsp;&nbsp; Platinum Customer: A customer who has spent more than $200 at the business.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Always receives a 15% discount, and for every $5 spent over $200 EVER spent at the business they receive a "bonus buck".  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "Bonus Buck": A $1 discount that is automatically applied on their next purchase.  
    
Gold and Platinum customers are referred to as "preferred customers" and are stored in different arrays and .dat files than regular customers.
Customer's orders are stored in another .dat file.
preferred.dat, customers.dat, and orders.dat provide some example customers and orders for the program.

Order format:
  Each customer has a 5 letter identifier that is used when placing orders.  
  The only drinks that can be ordered are Soda, Tea, or Punch. They are available in Small (S), Medium (M), or Large (L).  
  The cost per item (CPI) can be determined by the business, and the business says how many items the customer ordered.  
  
  An order is in the format of:  
  \<identifier> \<size> \<drink type> \<cpi> \<num of items>  
  
  For example, If I wanted to order 2 Large sodas and they are selling for $1, and my identifier with the business is "IANFA":  
  IANFA L soda 1 2  
  
  It is all case sensitive. The identifier and drink size should be uppercase, while the drink type should be lowercase. 
