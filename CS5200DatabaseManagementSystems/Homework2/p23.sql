SELECT 
    billingstate                AS customerState,
    billingcountry              AS customerCountry,
    Count(i.invoiceid)          AS numInvoices,
    Printf('$%.2f', Sum(total)) AS revenue
FROM   
    invoice i
WHERE  
    billingstate IS NOT NULL
GROUP  BY 
    billingstate,
    billingcountry
HAVING 
    Sum(total) > 60
ORDER  BY 
    Sum(total) DESC 
