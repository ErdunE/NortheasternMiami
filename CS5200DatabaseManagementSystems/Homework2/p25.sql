SELECT 
       e.employeeid                             AS empId,
       e.firstname                              AS empFirst,
       e.lastname                               AS empLast,
       e.title                                  AS empTitle,
       Count(i.invoiceid)                       AS numInvoices,
       Printf('$%.2f', Ifnull(Sum(i.total), 0)) AS invoiceTotal
FROM   
       employee e
       LEFT JOIN customer c ON e.employeeid = c.supportrepid
       LEFT JOIN invoice i ON c.customerid = i.customerid
GROUP  BY 
       e.employeeid
ORDER  BY 
       Ifnull(Sum(i.total) / NULLIF(Count(i.invoiceid), 0), 0) DESC,
       emplast ASC,
       empfirst ASC
